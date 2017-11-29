package rocketshooter;

import javafx.scene.input.KeyEvent;
import rocketshooter.model.Level;
import rocketshooter.view.LevelView;

/**
 *
 * @author Jordi
 */
public class Controller {

    private Level level;
    private LevelView vLevel;

    public Controller(Level level, LevelView vLevel) {
        this.level = level;
        this.vLevel = vLevel;

        vLevel.setOnMouseMoved(event -> vLevel.requestFocus());
        vLevel.setOnKeyPressed(this::beweeg);
    }

    public void beweeg(KeyEvent e) {
        switch (e.getCode()) {
            case RIGHT:
                level.gaRechts();
                break;
            case LEFT:
                level.gaLinks();
                break;
            case UP:
                level.gaBoven();
                break;
            case DOWN:
                level.gaOnder();
                break;
            case SPACE:
                level.shootBullet();
                break;
            case ESCAPE:
                System.exit(0);
            default:
                break;
        }
        vLevel.update();
    }

}
