package rocketshooter;

import java.awt.Rectangle;
import java.util.Iterator;
import rocketshooter.model.Bullet;
import rocketshooter.model.Enemy;
import rocketshooter.model.Level;
import rocketshooter.view.LevelView;
import java.util.logging.Logger;
import javafx.application.Platform;
import rocketshooter.model.GameObject;
import rocketshooter.model.ID;

/**
 *
 * De klasse BeweegBullet is een extra thread voor de afgeschoten kogel apart te
 * te laten mee bewegen in het spel.
 *
 * @author Jordi Jaspers
 * @author Salim Sadala
 */
public class BeweegBullet implements Runnable {

    private final Level level;
    private final LevelView vLevel;
    private final FXMLDocumentController controller;

    private Bullet bullet;

    private final int bulletSpeed = 25;
    private boolean looping = true;

    private Iterator<Enemy> storage;

    /**
     * Dit is de constructer van de Runnable BeweegObject.
     *
     * @param vLevel view van het level meegeven.
     * @param level een level meegeven.
     * @param bullet de kogel meegeven waar we thread voor maken.
     * @param controller meegeven aan de thread.
     */
    BeweegBullet(Level level, LevelView vLevel, FXMLDocumentController controller, Bullet bullet) {
        this.level = level;
        this.vLevel = vLevel;
        this.controller = controller;
        this.bullet = bullet;
    }

    /**
     * Run methode die wordt uitgevoerd bij het starten van de Thread.
     */
    @Override
    public void run() {
        while (looping) {

            if (bullet.getID() == ID.PLAYER) {

                storage = level.getEnemies().iterator();

                while (storage.hasNext()) {
                    Enemy enemy = storage.next();
                    if (looping) {
                        bulletTracker(enemy);
                    }
                }
            } else {
                bulletTracker(level.getPlayerA());
            }

            if (getOutOfBounds()) {
                looping = false;
                Platform.runLater(() -> {
                    vLevel.deleteBulletView(bullet);
                });
            } else {
                if (bullet.getID() == ID.PLAYER) {
                    bullet.setX(bullet.getX() + bulletSpeed);
                } else {
                    bullet.setX(bullet.getX() - bulletSpeed);
                }
            }

            if (looping) {
                Platform.runLater(() -> {
                    vLevel.updateBulletView(bullet);
                });
            }

            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * De Kogel mag niet buiten het scherm.
     *
     * @return true als de kogel buiten het scherm is.
     */
    private boolean getOutOfBounds() {
        return bullet.getX() > level.WIDTH || bullet.getX() < 0;
    }

    /**
     * Kijken als de het object een enemy of een player is en dan de gepaste
     * code uitvoeren voor het verwijderen/doden van het object.
     *
     * @param gameObject (= Enemy of Player)
     */
    private void bulletTracker(GameObject gameObject) {
        if (isRaakGeschoten(gameObject, bullet)) {
            looping = false;

            gameObject.setIsDood(true);

            if (gameObject.getID() == ID.PLAYER) {
                level.playerDood();

                Platform.runLater(() -> {
                    vLevel.deleteBulletView(bullet);
                    vLevel.UpdatePlayerPosition();
                    vLevel.updateStaticContext();
                    controller.verlorenOfGewonnen();
                });

            } else {
                // Aan een lijst toevoegen want meteen verwijderen geeft een modification error: 
                // geen aanpassingen maken tijdens een loop.
                //enemyToRemove.add( (Enemy) gameObject);

                vLevel.deleteEnemy((Enemy) gameObject);
                level.enemyDood((Enemy) gameObject);

                Platform.runLater(() -> {
                    vLevel.deleteBulletView(bullet);
                    controller.updateScore();
                });
            }
        }
    }

    /**
     *
     * 2 vierkantjes rond het figuur tekenen en kijken als die elkaar raken. de
     * -16 erbij is voor de hitbox een beetje kleiner te maken dan afbeelding
     * zodat het raakpunt realistischer blijft.
     *
     * @param tempObj1 eerste meegegeven gameObject
     * @param tempObj2 tweede meegegeven gameObject
     * @return true als de twee gameObjecten met elkaar in contact komen, anders
     * false.
     */
    public boolean isRaakGeschoten(GameObject tempObj1, GameObject tempObj2) {
        Rectangle hitBox1 = new Rectangle(tempObj1.getX(), tempObj1.getY(), tempObj1.getWidth() - 16, tempObj1.getHeight() - 16);
        Rectangle hitBox2 = new Rectangle(tempObj2.getX(), tempObj2.getY(), tempObj2.getWidth() - 16, tempObj2.getHeight() - 16);

        return hitBox1.intersects(hitBox2);
    }
}
