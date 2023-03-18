package fr.naos.armorstandmanager.Utils;

import fr.naos.armorstandmanager.ArmorstandManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

public class AnimManager {



    public static void dab(ArmorStand armorStand) {
        double[][] positionsDroite = {{-1, -1, -1}, {-1, 0, 2}};
        double[][] positionsGauche = {{-1, -1, -1}, {-1, 0, 2}};
        int interval = 7;
        new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                if (i >= positionsDroite.length) {
                    this.cancel();
                    return;
                }
                mouvement(armorStand, interval, positionsDroite[i], positionsGauche[i]);
                i++;
            }
        }.runTaskTimer(ArmorstandManager.getInstance(), 0L, interval);
    }

    public static void floss(ArmorStand armorstand) {
        if (armorstand == null) return;
        double[][] positionsDroite = {{0, 0, 0.2}, {0.2, 0, -0.2}, {0, 0, 0.2}, {-0.4, 0, 0}, {-0.4, 0, -0.4}, {-0.4, 0, -0.2}, {-0.4, 0, -0.4}, {-0.4, 0, 0}};
        double[][] positionsGauche = {{-0.4, 0, 0.4}, {-0.4, 0, 0.2}, {-0.4, 0, 0.4}, {-0.4, 0, 0}, {0, 0, -0.2}, {0.2, 0, 0}, {0, 0, -0.2}, {-0.4, 0, 0}};
        int interval = 5;
        new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                if (i >= positionsDroite.length) {
                    this.cancel();
                    return;
                }
                mouvement(armorstand, interval, positionsDroite[i], positionsGauche[i]);
                i++;
            }
        }.runTaskTimer(ArmorstandManager.getInstance(), 0L, interval);
    }





    public static void mouvement(ArmorStand entity, int fluiditer, double[] positionsDroite, double[] positionsGauche) {
        double[] droitActuel = getRightArm(entity);
        double[] gaucheActuel = getLeftArm(entity);

        double[] deltaDroit = new double[3];
        double[] deltaGauche = new double[3];

        for (int i = 0; i < 3; i++) {
            deltaDroit[i] = (positionsDroite[i] - droitActuel[i]) / fluiditer;
            deltaGauche[i] = (positionsGauche[i] - gaucheActuel[i]) / fluiditer;
        }

        final EulerAngle[] angleDroit = {entity.getRightArmPose()};
        final EulerAngle[] angleGauche = {entity.getLeftArmPose()};

        for (int i = 0; i < fluiditer; i++) {
            int ticks = i + 1;
            Bukkit.getScheduler().runTaskLaterAsynchronously(ArmorstandManager.getInstance(), new Runnable() {
                @Override
                public void run() {
                    angleDroit[0] = new EulerAngle(droitActuel[0] + (deltaDroit[0] * ticks),
                            droitActuel[1] + (deltaDroit[1] * ticks),
                            droitActuel[2] + (deltaDroit[2] * ticks));
                    angleGauche[0] = new EulerAngle(gaucheActuel[0] + (deltaGauche[0] * ticks),
                            gaucheActuel[1] + (deltaGauche[1] * ticks),
                            gaucheActuel[2] + (deltaGauche[2] * ticks));

                    entity.setRightArmPose(angleDroit[0]);
                    entity.setLeftArmPose(angleGauche[0]);
                }
            }, ticks);
        }
    }

    private static double[] getRightArm(ArmorStand entity) {
        EulerAngle angle = entity.getRightArmPose();
        return new double[]{angle.getX(), angle.getY(), angle.getZ()};
    }

    private static double[] getLeftArm(ArmorStand entity) {
        EulerAngle angle = entity.getLeftArmPose();
        return new double[]{angle.getX(), angle.getY(), angle.getZ()};
    }

}
