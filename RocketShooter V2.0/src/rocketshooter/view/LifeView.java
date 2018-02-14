/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocketshooter.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import rocketshooter.model.Life;

/**
 *
 * Voor de ons object leven hoort een view dat eraan geven.
 *
 * @author Jordi Jaspers
 * @author Salim Sadala
 */
public class LifeView extends Region {

    private Life life;
    private Image model;

    /**
     * de view van onze enemy instellen.
     *
     * @param life uit de klasse enemy.
     */
    public LifeView(Life life) {

        this.life = life;

        model = new Image(getClass().getResourceAsStream("/resources/LIFE.png"));              //Een afbeelding uit de directory halen.

        ImageView imgMain = new ImageView(model);                                              //de afbeelding een view geven.
        imgMain.setFitHeight(16);                                                               //methode gevonden via de JAVA website.
        imgMain.setFitWidth(20);

        imgMain.setX(life.getX());
        imgMain.setY(life.getY());

        getChildren().addAll(imgMain);
    }

    /**
     * returnwaarden geven van onze klasse Life.
     *
     * @return model
     */
    public Life getLife() {
        return life;
    }

    /**
     * returnwaarde geven voor onze figuur van de bullet.
     *
     * @return player
     */
    public Image getModel() {
        return model;
    }
}
