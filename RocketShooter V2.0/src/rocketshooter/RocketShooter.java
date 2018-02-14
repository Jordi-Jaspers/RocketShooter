package rocketshooter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import rocketshooter.model.Level;
import rocketshooter.view.LevelView;

/**
 *
 * De Main-Thread van het spel Rocketshooter
 *
 * @author Jordi Jaspers
 * @author Salim Sadala
 */
public class RocketShooter extends Application {

    private Level level;
    private LevelView vLevel;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();

        level = new Level();
        vLevel = new LevelView(level);

        FXMLDocumentController controller = loader.getController();
        controller.setModel(level, vLevel);

        Scene scene = new Scene(root);

        stage.setTitle("Rocket Shooter");    //titel voor ons scherm.
        stage.setResizable(false);           //hierdoor maken we de window niet meer resizable.
        stage.setScene(scene);
        stage.show();
    }

}
