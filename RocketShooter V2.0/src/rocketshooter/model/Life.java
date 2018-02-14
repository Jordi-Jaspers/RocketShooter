package rocketshooter.model;

/**
 *
 * ModelGevens voor het aantal levens van de speler.
 *
 * @author Jordi Jaspers
 * @author Salim Sadala
 */
public class Life {

    /**
     * De x-coordinaat van het object.
     */
    private int x;

    /**
     * De y-coordinaat van het object.
     */
    private int y;

    /**
     * leven aanmaken voor de speler.
     *
     * @param x positie op de x-as (>0 = rechts)
     * @param y positie op de y-as (>0 = onder)
     */
    public Life(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * de x-coordinaat van het GameObject.
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * de y-coordinaat van het GameObject.
     *
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * de x-coordinaat van het GameObject.
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * de y-coordinaat van het GameObject.
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

}
