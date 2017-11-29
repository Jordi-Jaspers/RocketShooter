package rocketshooter;

import javafx.application.Platform;
import rocketshooter.model.Level;

/**
 *
 * Een extra runnable die objecten afzonderlijk van elkaar kan laten bewegen.
 *
 * @author jordi
 */
public class BeweegObject implements Runnable {

    private Level level;
    private FXMLDocumentController controller;
    
    public BeweegObject(Level level, FXMLDocumentController controller){
        this.level = level;
        this.controller = controller;
        
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
                level.beweegBullet();
                level.beweegEnemy();
                Platform.runLater(() -> {controller.updateViews();}); 
            } 
            catch (InterruptedException e) {

            }
        }
    }

}
