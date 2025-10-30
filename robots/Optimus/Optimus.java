package Optimus;
import robocode.*;
import java.awt.Color;

/**
 * Optimus - a robot by d4nkali
 */
public class Optimus extends Robot {

    // Método principal do robô
    public void run() {
        // Define as cores
        setColors(Color.red, Color.blue, Color.green); // corpo, arma, radar

        // Loop principal do robô
        while (true) {
            moverEmQuadrado();
            moverAleatoriamente();
        }
    }

    // Movimento em quadrado
    public void moverEmQuadrado() {
        for (int i = 0; i < 4; i++) {
            ahead(200);
            turnRight(90);
        }
    }

    // Movimento aleatório
    public void moverAleatoriamente() {
        ahead(50 + Math.random() * 100);
        if (Math.random() > 0.5) {
            turnRight(Math.random() * 90);
        } else {
            turnLeft(Math.random() * 90);
        }
    }

    // Ao escanear outro robô
    public void onScannedRobot(ScannedRobotEvent e) {
        back(100);
        turnRight(90 + Math.random() * 45);
        fire(1);
    }

    // Ao ser atingido por um tiro
    public void onHitByBullet(HitByBulletEvent e) {
        back(10);
    }

    // Ao bater na parede
    public void onHitWall(HitWallEvent e) {
        back(20);
        turnRight(90);
    }
}
