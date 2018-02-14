package rocketshooter;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;
import javafx.application.Platform;
import rocketshooter.model.Bullet;
import rocketshooter.model.Enemy;
import rocketshooter.model.Level;
import rocketshooter.view.LevelView;

/**
 *
 * De klasse BeweegEnemy is voor een de vijanden een afzonderlijk pad te laten
 * afleggen van de speler.
 *
 * @author Jordi Jaspers
 * @author Salim Sadala
 */
public class BeweegEnemy implements Runnable {

    private Level level;
    private LevelView vLevel;
    private FXMLDocumentController controller;

    private ArrayList<Enemy> enemies;

    /**
     * Dit is de constructer van de Runnable BeweegObject.
     *
     * @param vLevel view van het level meegeven.
     * @param level een level meegeven.
     * @param controller en de main thread meegeven.
     */
    public BeweegEnemy(LevelView vLevel, Level level, FXMLDocumentController controller) {
        this.level = level;
        this.vLevel = vLevel;
        this.controller = controller;
    }

    /**
     * Run methode die wordt uitgevoerd bij het starten van de Thread.
     */
    @Override
    public void run() {
        while (true) {
            enemies = level.getEnemies();
            beweeg();
            wait(1000);
        }
    }

    /**
     * Deze methode bepaald de wachtijd voor een actie om te vermijden dat er
     * constant een try-catch moet uitgevoerd worden.
     *
     * @param time de tijd (in miliseconden)
     */
    private void wait(int time) {

        if (level.getEnemies().isEmpty()) {
            controller.verlorenOfGewonnen();
        } else {

            try {
                Thread.sleep(time);
            } catch (InterruptedException ex) {
                Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

            Platform.runLater(
                    () -> {
                        vLevel.updateEnemyView();
                    }
            );
        }
    }

    /**
     * Deze methode gaat via StringCases (zoals het bewegen van de player)
     *
     * @param enemy gegevens van de GameObject Enemy
     * @param direction string die aangeeft welke kant de enemy uitgaay
     */
    private void ChangePosition(Enemy enemy, String direction) {

        //bepaalt het aantal pixels dat het object mag bewegen.
        int movingSpace = 3;

        switch (direction) {
            case "up":
                enemy.setY(enemy.getY() - movingSpace);
                break;
            case "down":
                enemy.setY(enemy.getY() + movingSpace);
                break;
            case "left":
                enemy.setX(enemy.getX() - movingSpace);
                break;
            case "right":
                enemy.setX(enemy.getX() + movingSpace);
                break;
            default:
                break;
        }
    }

    /**
     * eerst kijken als er nog enemies zijn anders stop spel!
     *
     * Alle enemies worden opgevraagd en gaan door de onderstaande loop in een
     * cirkel bewegen.
     *
     * PaintTimer (= een timer voor te redrawen) SleepTimer(= pauze tussen de
     * bewegingen in) MovesLR(= Het aantal stappen naar links en rechts)
     *
     * tijdens de pauze laat ik een random enemy schieten.
     *
     * @param enemies alle vijanden meegeven
     */
    private void beweeg() {
        int paintTimer = 50;
        int sleepTimer = 500;
        int movesLR = 25;

        do {
            for (Enemy enemy : enemies) {
                ChangePosition(enemy, "up");
            }
            wait(paintTimer);
        } while (enemies.isEmpty() == true || enemies.get(0).getY() > 50);

        checkEnemiesDood();
        shoot();
        wait(sleepTimer);

        for (int k = 0; k < movesLR; k++) {
            for (Enemy enemy : enemies) {
                ChangePosition(enemy, "left");
            }
            wait(paintTimer);
        }

        checkEnemiesDood();
        shoot();
        wait(sleepTimer);

        do {
            for (Enemy enemy : enemies) {
                ChangePosition(enemy, "down");
            }
            wait(paintTimer);
            checkEnemiesDood();
        } while (enemies.isEmpty() == true || enemies.get(enemies.size() - 1).getY() < 420 - 50);

        checkEnemiesDood();
        shoot();
        wait(sleepTimer);

        for (int k = 0; k < movesLR; k++) {
            for (Enemy enemy : enemies) {
                ChangePosition(enemy, "right");
            }
            wait(paintTimer);
        }

        checkEnemiesDood();
        shoot();
        wait(sleepTimer);
    }

    /**
     * checken als alle enemies dood zijn, zo ja dan eindig het spel meteen.
     */
    private void checkEnemiesDood() {
        if (enemies.isEmpty()) {
            Platform.runLater(
                    () -> {
                        level.setGameStarted(false);
                        controller.verlorenOfGewonnen();
                    });
        }
    }

    /**
     * De methode die een Random enemy laat schieten van alle enemies die zich
     * nog op het veld bevinden.
     */
    private void shoot() {
        Random random = new Random();
        Enemy shootingEnemy = enemies.get(random.nextInt(enemies.size()));

        Bullet enemyBullet = level.shootBulletByEnemy(shootingEnemy);

        BeweegBullet beweegBullet = new BeweegBullet(level, vLevel, controller, enemyBullet);

        Thread bulletThread = new Thread(beweegBullet);

        bulletThread.setDaemon(true);
        bulletThread.start();
    }
}
