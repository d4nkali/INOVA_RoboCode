package Leviathan;
import robocode.*;
import java.awt.Color;

/**
 * Leviathan - v3 by d4nkali
 */
public class Leviathan extends AdvancedRobot {
    private String inimigoAtual = null;

    public void run() {
        setColors(Color.red, Color.black, Color.blue);
        setAdjustRadarForRobotTurn(true); // radar independente
        setAdjustGunForRobotTurn(true);   // canhão independente

        while (true) {
            // Radar gira constantemente procurando inimigos
            turnRadarRight(360);
            moverEmZigueZaguePatrulha();
        }
    }

    // Zigue-zague apenas para patrulhar
    public void moverEmZigueZaguePatrulha() {
        ahead(80);
        turnRight(30);
        ahead(80);
        turnLeft(30);
    }

    // Quando escaneia um robô
    public void onScannedRobot(ScannedRobotEvent e) {
        double bearing = e.getBearing();
        double distance = e.getDistance();
        inimigoAtual = e.getName();

        // Travar radar no inimigo
        double radarTurn = getHeading() - getRadarHeading() + bearing;
        turnRadarRight(normalizeAngle(radarTurn));

        // Girar canhão em direção ao inimigo
        double gunTurn = getHeading() - getGunHeading() + bearing;
        turnGunRight(normalizeAngle(gunTurn));

        // Força de tiro variável
        double power = Math.max(1.5, Math.min(3, 400 / distance));

        // Dispara se o inimigo estiver no alcance do canhão
        if (Math.abs(gunTurn) < 10) {
            fire(power);
        }

        // Movimento lateral (evita ser atingido)
        if (Math.random() > 0.5) {
            setTurnRight(bearing + 90);
        } else {
            setTurnLeft(bearing + 90);
        }
        setAhead(100);
        execute();
    }

    // Se o inimigo morrer
    public void onRobotDeath(RobotDeathEvent e) {
        if (e.getName().equals(inimigoAtual)) {
            inimigoAtual = null;
        }
    }

    // Ao ser atingido
    public void onHitByBullet(HitByBulletEvent e) {
        setBack(50);
        turnRight(45 + Math.random() * 45);
        fire(1.5);
        execute();
    }

    // Ao bater em uma parede
    public void onHitWall(HitWallEvent e) {
        back(100);
        turnRight(90 + Math.random() * 90);
        ahead(100);
    }

    // Normaliza ângulo entre -180 e 180
    private double normalizeAngle(double angle) {
        while (angle > 180) angle -= 360;
        while (angle < -180) angle += 360;
        return angle;
    }
}
