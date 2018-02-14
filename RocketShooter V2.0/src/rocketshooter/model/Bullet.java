package rocketshooter.model;

/**
 * de eigenschappen en het gedrag die een kogel kan hebben en die deel uitmaakt
 * van gameobject.
 *
 * @author Jordi Jaspers
 * @author Salim Sadala
 */
public class Bullet extends GameObject {

    /**
     * kogel aanmaken voor de game.
     *
     * @param x positie op de x-as (>0 = rechts)
     * @param y positie op de y-as (>0 = onder)
     * @param width breeedte van het object.
     * @param height hoogte van het object.
     * @param id van het object.
     * @param url Het pad naar de afbeelding van het object.
     *
     */
    public Bullet(int x, int y, int width, int height, ID id, String url) {
        super(x, y, width, height, id, url);
    }
}
