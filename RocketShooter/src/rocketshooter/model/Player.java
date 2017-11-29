package rocketshooter.model;

/**
 *
 * een klasse die de eigenschappen van de speler geeft en een deel erft van
 * gameobject.
 *
 * @author Jordi
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
     * 
     */
    public Player(double x, double y, double width, double height, ID id) {
        super(x, y, width, height, id);
    }

}
