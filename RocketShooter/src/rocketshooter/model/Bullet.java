package rocketshooter.model;

/**
 * de eigenschappen en het gedrag die een kogel kan hebben en die deel uitmaakt
 * van gameobject.
 *
 * @author Jordi
 */
public class Bullet extends GameObject implements BewegendObject{
    
    /**
     * kogel aanmaken voor de game.
     *
     * @param x positie op de x-as (>0 = rechts)
     * @param y positie op de y-as (>0 = onder)
     * @param width breeedte van het object.
     * @param height hoogte van het object.
     * @param id van het object (speler, enemy of bullet)
     * 
     */
    public Bullet(double x, double y, double width, double height, ID id) {
        super(x, y, width, height, id);
    }
    
    // voor de interface bewegendObject.
    private boolean running = false;
    public void start() { running = true;}
    public void stop() { running = false;}
    public boolean beweegt() {return running;}
    
}
