package rocketshooter.model;

/**
 * een klasse die de eigenschappen van de enemy geeft en een deel erft van
 * gameobject.
 *
 * @author Jordi
 */
public class Enemy extends GameObject implements BewegendObject{

    /**
     * Enemy aanmaken voor de game.
     *
     * @param x positie op de x-as (>0 = rechts)
     * @param y positie op de y-as (>0 = onder)
     * @param width breeedte van het object.
     * @param height hoogte van het object.
     * @param id van het object (speler, enemy of bullet)
     * 
     */
    public Enemy(double x, double y, double width, double height, ID id) {
        super(x, y, width, height, id);
    }
    
    // voor de interface BewegendObject
    private boolean running = false;
    public void start() { running = true;}
    public void stop() { running = false;}
    public boolean beweegt() {return running;}
    
}
