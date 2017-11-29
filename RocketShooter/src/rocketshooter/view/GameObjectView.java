package rocketshooter.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import rocketshooter.model.GameObject;

/**
 *
 * @author jordi
 */
public class GameObjectView extends Region {

    /**
     * De image node voor het bepaalde object.
     */
    private Image model;

    /**
     * De hoogte van het object, die alleen veranderd kan worden door de geërfde
     * klasse.
     */
    protected String url;

    /**
     * een gameobjectview maken instellen.
     *
     * @param gameObject eigenschappen van het gekozen object importeren.
     * @param url het pad naar de afbeelding voor het object.
     */
    public GameObjectView(GameObject gameObject, String url) {

        model = new Image(getClass().getResourceAsStream(url));                 //Een afbeelding uit de directory halen.

        ImageView imgMain = new ImageView(model);                               //de afbeelding een view geven.
        imgMain.setFitHeight(gameObject.getHeight());                           //Methode gevonden uit de cursus.
        imgMain.setFitWidth(gameObject.getWidth());

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
     * returnwaarde geven voor onze figuur van de enemy.
     *
     * @return player
     */
    public Image getModel() {
        return model;
    }

}
