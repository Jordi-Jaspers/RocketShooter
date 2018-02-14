package rocketshooter;

import javafx.scene.shape.Rectangle;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import rocketshooter.model.Bullet;

/**
 * FXMLController beheert het volledige FXML bestand en objecten.
 *
 * @author Jordi Jaspers
 */
public class FXMLDocumentController {

    private Level level;
    private LevelView vLevel;

    private Thread enemyThread;
    private Thread bulletThread;

    private Rectangle selectionRect = new Rectangle();

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
    private Label endLabel;

    @FXML
    private Label finalScore;

    @FXML
    private ImageView vliegtuig1;

    @FXML
    private ImageView vliegtuig2;

    @FXML
    private ImageView vliegtuig3;

    @FXML
    private Label vliegtuigLabel1;

    @FXML
    private Label vliegtuigLabel2;

    @FXML
    private Label vliegtuigLabel3;

    @FXML
    void initialize() {

        start.setOnAction(event -> start());
        stop.setOnAction(event -> exit());
        option.setOnAction(event -> option());

        window.setOnKeyPressed(this::beweeg);
    }

    /**
     * Dit initialiseert de start knop van het spel.
     */
    public void start() {
        //focus zetten op het juiste deel anders reageert het programma niet.
        vLevel.setFocusTraversable(false);
        Platform.runLater(() -> vLevel.requestFocus());
        level.setGameStarted(true);

        window.getChildren().clear();
        setView();

        vLevel = new LevelView(level);
        window.getChildren().add(vLevel);

        level.init();

        //Een nieuwe thread aanmaken voor de beweging van de enemy.
        BeweegEnemy beweegEnemy = new BeweegEnemy(vLevel, level, this);
        enemyThread = new Thread(beweegEnemy);
        enemyThread.setDaemon(true);
        enemyThread.start();

        vLevel.UpdatePlayerPosition();
        vLevel.updateStaticContext();
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

        window.getChildren().clear();

        vliegtuigLabel1.setText("Classic Airplane");
        Image tempImage1 = new Image(getClass().getResourceAsStream("/resources/AIRPLANE_1.png"));
        vliegtuig1.setImage(tempImage1);
        vliegtuig1.setX(16);
        vliegtuig1.setY(36);
        vliegtuig1.setOnMouseClicked(event -> {
            level.setStartingColor(0);
            getGeselecteerd(level.getStartingColor());
        });

        vliegtuigLabel2.setText("Modern AirPlane");
        Image tempImage2 = new Image(getClass().getResourceAsStream("/resources/AIRPLANE_2.png"));
        vliegtuig2.setImage(tempImage2);
        vliegtuig2.setX(16);
        vliegtuig2.setY(136);
        vliegtuig2.setOnMouseClicked(event -> {
            level.setStartingColor(1);
            getGeselecteerd(level.getStartingColor());
        });

        vliegtuigLabel3.setText("Red Helicopter");
        Image tempImage3 = new Image(getClass().getResourceAsStream("/resources/AIRPLANE_3.png"));
        vliegtuig3.setImage(tempImage3);
        vliegtuig3.setX(16);
        vliegtuig3.setY(236);
        vliegtuig3.setOnMouseClicked(event -> {
            level.setStartingColor(2);
            getGeselecteerd(level.getStartingColor());
        });

        Image backGround = new Image(getClass().getResourceAsStream("/resources/BACKGROUND_4.png"));
        vBackground = new ImageView(backGround);
        vBackground.setFitHeight(level.HEIGHT + 40);
        vBackground.setFitWidth(level.WIDTH + 40);

        vBackground.setX(0);
        vBackground.setY(0);

        window.getChildren().addAll(vBackground, vliegtuig1, vliegtuigLabel1, vliegtuig2, vliegtuigLabel2, vliegtuig3, vliegtuigLabel3, start, stop);
    }

    /**
     * Kijken welke afbeelding geselecteerd is.
     *
     * @param getal
     */
    public void getGeselecteerd(int getal) {
        if (level.getStartingColor() == 0) {
            drawBorder(vliegtuig1);
        }
        if (level.getStartingColor() == 1) {
            drawBorder(vliegtuig2);
        }
        if (level.getStartingColor() == 2) {
            drawBorder(vliegtuig3);
        }
    }

    /**
     * Een boord toevoegen aan de imageview van de geselecteerde afbeelding.
     *
     * @param view
     */
    public void drawBorder(ImageView view) {

        window.getChildren().remove(selectionRect);

        Double width = view.getFitWidth();
        Double height = view.getFitHeight();

        selectionRect.setX(view.getX() + 10);
        selectionRect.setY(view.getY() + 10);
        selectionRect.setWidth(width.intValue() - 20);
        selectionRect.setHeight(height.intValue() - 20);

        selectionRect.setFill(Color.TRANSPARENT);
        selectionRect.setStroke(Color.RED);
        selectionRect.setStrokeType(StrokeType.OUTSIDE);
        selectionRect.setStrokeWidth(2);
        window.getChildren().add(selectionRect);
    }

    /**
     * als je levens op zijn en je bent dood dan stopt het spel.
     */
    public void verlorenOfGewonnen() {
        if (level.getGameStarted() == false) {
            window.getChildren().clear();

            Image backGround = new Image(getClass().getResourceAsStream("/resources/BACKGROUND_4.png"));
            vBackground = new ImageView(backGround);
            vBackground.setFitHeight(level.HEIGHT + 40);
            vBackground.setFitWidth(level.WIDTH + 40);

            start.setText("Restart");

            if (level.getPlayerA().getIsDood() == true) {
                endLabel.setText("GAME OVER!");
                endLabel.setTextFill(Color.web("FF0000"));             //Kleurcode gevonden via: https://www.rapidtables.com/web/color/RGB_Color.html
            } else {
                endLabel.setText("WINNER!");
                endLabel.setTextFill(Color.web("00FF00"));             //Kleurcode gevonden via: https://www.rapidtables.com/web/color/RGB_Color.html
            }

            finalScore.setText("Final Score: " + level.getScoreTeller() + "");
            window.getChildren().addAll(vBackground, endLabel, finalScore, stop, start, option);
        }
    }

    /**
     * een methode die statische context update.
     */
    public void setView() {
        updateScore();
        lifes.setText("Lifes: ");

        Image backGround = new Image(getClass().getResourceAsStream("/resources/BACKGROUND_4.png"));         //Een afbeelding uit de directory halen.

        vBackground = new ImageView(backGround);                                                             //de afbeelding een view geven.
        vBackground.setFitHeight(level.HEIGHT + 40);
        vBackground.setFitWidth(level.WIDTH + 40);

        vBackground.setX(0);
        vBackground.setY(0);

        window.getChildren().addAll(vBackground);
        window.getChildren().add(score);
        window.getChildren().add(lifes);
    }

    /**
     * Deze methode is gemaakt voor de score constant te update wanneer er een
     * een punt bijkomt.
     */
    public void updateScore() {
        score.setText("Score: " + level.getScoreTeller() + "");
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

        vLevel.setFocusTraversable(true);
        vLevel.requestFocus();
        vLevel.setOnKeyPressed(this::beweeg);

    }

    /**
     * bewegingen die de speler kan uitvoeren.
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
                BeweegBullet beweegBullet = new BeweegBullet(level, vLevel, this, bullet);
                bulletThread = new Thread(beweegBullet);
                bulletThread.setDaemon(true);
                bulletThread.start();
                break;
            case ESCAPE:
                System.exit(0);
            default:
                break;
        }
    }
}
