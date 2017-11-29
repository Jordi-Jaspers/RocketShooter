
package rocketshooter.model;

/**
 *
 * @author Jordi
 */
public class Life {

    /**
     * De x-coordinaat van het object.
     */
    private double x;

    /**
     * De y-coordinaat van het object.
     */
    private double y;

    /**
     * leven aanmaken voor de speler.
     *
     * @param x positie op de x-as (>0 = rechts)
     * @param y positie op de y-as (>0 = onder)
     */
    public Life(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * de x-coordinaat van het GameObject.
     *
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * de y-coordinaat van het GameObject.
     *
     * @return y
     */
    public double getY() {
        return y;
    }

    /**
     * de x-coordinaat van het GameObject.
     *
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * de y-coordinaat van het GameObject.
     *
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }

}
