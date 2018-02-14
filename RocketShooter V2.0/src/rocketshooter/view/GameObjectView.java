package rocketshooter.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import rocketshooter.model.GameObject;

/**
 *
 * Een klasse dat de view maakt van een ontworpen gameobject.
 *
 * @author Jordi Jaspers
 * @author Salim Sadala
 */
public class GameObjectView extends Region {

    /**
     * De imageView voor een bepaalde object.
     */
    private ImageView imgMain;

    /**
     * De image node voor een bepaalde object.
     */
    private Image model;

    /**
     * De hoogte van het object, die alleen veranderd kan worden door de
     * geërfde. klasse.
     */
    protected String url;

    /**
     * Gegevens van gameObject meegeven.
     */
    private GameObject gameObject;

    /**
     * een gameobjectview maken instellen.
     *
     * @param gameObject eigenschappen van het gekozen object importeren.
     */
    public GameObjectView(GameObject gameObject) {
        this.gameObject = gameObject;

        model = new Image(getClass().getResourceAsStream(gameObject.getUrl()));                 // Een afbeelding uit de directory halen.

        imgMain = new ImageView(model);                                                         // De afbeelding een view geven.
        imgMain.setFitHeight(gameObject.getHeight());
        imgMain.setFitWidth(gameObject.getWidth());
        imgMain.setPreserveRatio(true);
        imgMain.setX(gameObject.getX());
        imgMain.setY(gameObject.getY());

        getChildren().addAll(imgMain);
    }

    /**
     * De locatie van de afbeelding voor het object.
     *
     * @return url
     */
    public String getURL() {
        return url;
    }

    /**
     * returnwaarde geven voor onze figuur.
     *
     * @return model
     */
    public Image getModel() {
        return model;
    }

    /**
     * returnwaarde geven voor de view van de figuur.
     *
     * @return imgMain
     */
    public ImageView getImgMain() {
        return imgMain;
    }

    /**
     * Volledige GameObject opvragen met alle waardes die dit object bezit.
     *
     * @return GameObject
     */
    public GameObject getGameObject() {
        return gameObject;
    }
}
