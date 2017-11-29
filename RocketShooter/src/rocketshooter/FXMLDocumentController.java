package rocketshooter;

import java.io.FileNotFoundException;
import rocketshooter.view.LevelView;
import rocketshooter.model.Level;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FXMLDocumentController {

    private Level level;
    private LevelView vLevel;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane window;

    @FXML
    private ImageView vBackground;

    @FXML
    private Button start;

    @FXML
    private Label title;

    @FXML
    private Label score;

    @FXML
    private Label lifes;

    @FXML
    private Button option;

    @FXML
    private Button stop;

    @FXML
    private ImageView ufo1;

    @FXML
    private ImageView ufo3;

    @FXML
    private ImageView ufo2;

    @FXML
    private ImageView ufo4;

    @FXML
    private Label punt1;

    @FXML
    private Label punt2;

    @FXML
    private Label punt3;

    @FXML
    private Label punt4;

    @FXML
    private Label gameover;

    @FXML
    private Label finalscore;

    @FXML
    void initialize() throws FileNotFoundException {

        start.setOnAction(event -> start());
        start.setFocusTraversable(false);

        stop.setOnAction(event -> exit());
        option.setOnAction(event -> option());

        window.setOnKeyPressed(this::beweeg);

    }

    /**
     * Dit initialiseert de start knop van het spel.
     */
    public void start() {

        //verwijder();
        window.getChildren().clear();

        vLevel.setFocusTraversable(false);
        Platform.runLater(() -> vLevel.requestFocus());         //focus zetten op het juiste deel anders reageert het programma niet.

        updateViews();
        level.setGameStarted(true);

        this.vLevel = new LevelView(level);
        window.getChildren().add(vLevel);
        level.init();

    }

    /**
     * hierbij sluiten we het programma af.
     */
    public void exit() {
        System.exit(0);
    }

    /**
     * options-menu voor keuze uit player-afbeeldingen.
     */
    public void option() {

    }

    /**
     * als je levens op zijn en je bent dood dan stopt het spel.
     */
    public void dood() {
        if (level.getGameStarted() == false) {
            window.getChildren().clear();
            gameover.setText("GAME OVER!");
            finalscore.setText("Final Score: " + level.getScoreTeller() + "");
        }
    }

    /**
     * een methode die constant de view update.
     */
    public void updateViews() {
        vLevel.update();
        score.setText("Score: " + level.getScoreTeller() + "");
        lifes.setText("Lifes: ");

        Image backGround = new Image(getClass().getResourceAsStream("/resources/BACKGROUND_4.png"));         //Een afbeelding uit de directory halen.

        vBackground = new ImageView(backGround);                                                             //de afbeelding een view geven.
        vBackground.setFitHeight(level.HEIGHT+40);                                                              //Methode gevonden op de JAVA-website.
        vBackground.setFitWidth(level.WIDTH+40);

        vBackground.setX(0);
        vBackground.setY(0);

        window.getChildren().addAll(vBackground);
        window.getChildren().add(score);
        window.getChildren().add(lifes);

    }

    /**
     * een model maken van het totale level.
     *
     * @param level
     * @param vLevel
     */
    public void setModel(Level level, LevelView vLevel) {
        this.level = level;
        this.vLevel = vLevel;

        vLevel = new LevelView(level);

        vLevel.setFocusTraversable(true);
        vLevel.requestFocus();
        vLevel.setOnKeyPressed(this::beweeg);
    }

    /**
     * bewegingen die de speler kan uitvoeren.
     * @param e
     */
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
