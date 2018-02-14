package rocketshooter.model;

/**
 *
 * een klasse die de eigenschappen van de speler geeft en een deel erft van
 * gameobject.
 *
 * @author Jordi Jaspers
 * @author Salim Sadala
 */
public class Player extends GameObject {

    /**
     * Speler aanmaken voor de game.
     *
     * @param x positie op de x-as (>0 = rechts)
     * @param y positie op de y-as (>0 = onder)
     * @param width breeedte van het object.
     * @param height hoogte van het object.
     * @param id van het object (speler, enemy of bullet)
     * @param url Het pad naar de afbeelding van het object.
     *
     */
    public Player(int x, int y, int width, int height, ID id, String url) {
        super(x, y, width, height, id, url);
    }

}
