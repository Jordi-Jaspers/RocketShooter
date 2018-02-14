package rocketshooter;

import javafx.scene.input.KeyEvent;
import rocketshooter.model.Bullet;
import rocketshooter.model.Level;
import rocketshooter.view.LevelView;

/**
 * Dit is een controller met alle bewegingen en keybinds die gemaakt kunnen
 * worden binnen het spel.
 *
 * @author Jordi Jaspers
 * @author Salim Sadala
 */
public class Controller {

    private final Level level;
    private final LevelView vLevel;
    private final FXMLDocumentController controller;
    private Thread bulletThread;

    /**
     * Een controller voor de bewegingen opbouwen.
     *
     * @param level
     * @param vLevel
     * @param controller
     */
    public Controller(Level level, LevelView vLevel, FXMLDocumentController controller) {
        this.level = level;
        this.vLevel = vLevel;
        this.controller = controller;

        vLevel.setOnMouseMoved(event -> vLevel.requestFocus());
        vLevel.setOnKeyPressed(this::beweeg);
    }

    /**
     * bewegingen die uitgevoerd kunnen worden door de objecten.
     *
     * @param e
     */
    public void beweeg(KeyEvent e) {
        switch (e.getCode()) {
            case RIGHT:
                level.gaRechts();
                vLevel.UpdatePlayerPosition();
                break;
            case LEFT:
                level.gaLinks();
                vLevel.UpdatePlayerPosition();
                break;
            case UP:
                level.gaBoven();
                vLevel.UpdatePlayerPosition();
                break;
            case DOWN:
                level.gaOnder();
                vLevel.UpdatePlayerPosition();
                break;
            case R:
                level.restart();
            case SPACE:
                Bullet bullet = level.shootBulletByPlayer();
                BeweegBullet beweegBullet = new BeweegBullet(level, vLevel, controller, bullet);
                bulletThread = new Thread(beweegBullet);
                bulletThread.start();
                break;
            case ESCAPE:
                System.exit(0);
            default:
                break;
        }
    }

}
