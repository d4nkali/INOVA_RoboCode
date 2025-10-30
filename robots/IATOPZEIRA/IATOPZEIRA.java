package IATOPZEIRA;

import robocode.*;

// Robo feito por Leofhin e d4nkali

public class IATOPZEIRA extends Robot {

    @Override
    public void run() {
        // Deixa o radar girando infinitamente
        while (true) {
            turnRadarRight(360);
        }
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        // Calcula a distância até o inimigo
        double distance = e.getDistance();

        // Gira o corpo do robô em direção ao inimigo
        turnRight(e.getBearing());

        // Se estiver longe, vai pra cima
        if (distance > 150) {
            ahead(distance - 100); // se aproxima, mas não cola
        }

        // Mira e atira constantemente
        fire(3); // intensidade máxima do tiro

        // Mantém o radar girando para continuar rastreando
        scan();
    }

    @Override
    public void onHitByBullet(HitByBulletEvent e) {
        // Faz um pequeno recuo e gira aleatoriamente pra despistar
        back(50);
        turnRight(30);
    }

    @Override
    public void onHitWall(HitWallEvent e) {
        // Recuar e girar para evitar travar na parede
        back(100);
        turnRight(90);
    }
}