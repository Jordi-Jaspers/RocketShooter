package rocketshooter.model;

/**
 *
 * alle eigeschappen wat een object kan hebben bevinden zich in deze klasse.
 * omdat in deze klasse alleen eigenschappen/methodes bevinden voor andere
 * klasse om te erven is deze abstract. dit wordt geerfd door: Enemy, Player,
 * Bullet.
 *
 * @author Jordi Jaspers
 * @author Salim Sadala
 */
abstract public class GameObject {

    /**
     * De x-coordinaat van het object, die alleen veranderd kan worden door de
     * geërfde klasse.
     */
    protected int x;

    /**
     * De y-coordinaat van het object, die alleen veranderd kan worden door de
     * geërfde klasse.
     */
    protected int y;

    /**
     * De breedte van het object, die alleen veranderd kan worden door de
     * geërfde klasse.
     */
    protected int width;

    /**
     * De hoogte van het object, die alleen veranderd kan worden door de geërfde
     * klasse.
     */
    protected int height;

    /**
     * Is het object dood of niet? dit kan alleen veranderd worden door de
     * geërfde klasse.
     */
    protected boolean isDood;

    /**
     * Dit bepaald wat voor een soort object het is. (speler of een enemy)
     */
    protected ID id;

    /**
     * Dit bepaald het pad naar de afbeelding van het object.
     */
    protected String url;

    /**
     * Constructor van GameObject. positie van het object bepalen
     *
     * @param x positie op de x-as (>0 = rechts)
     * @param y positie op de y-as (>0 = onder)
     * @param width breeedte van het object.
     * @param height hoogte van het object.
     * @param id van het object (speler, enemy of bullet)
     * @param url Het pad naar de afbeelding van het object.
     *
     */
    public GameObject(int x, int y, int width, int height, ID id, String url) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
        this.url = url;
        isDood = false;
    }

    /**
     * return het pad naar de afbeelding.
     *
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * een ID zetten voor je object.
     *
     * @param id van het gameobject (speler of enemy)
     */
    public void setID(ID id) {
        this.id = id;
    }

    /**
     * Dit geeft een returnwaarde voor het id van het GameObject.
     *
     * @return id van het gameobject (speler of enemy)
     */
    public ID getID() {
        return id;
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

    /**
     * de x-coordinaat van het GameObject.
     *
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * de x-coordinaat van het GameObject.
     *
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * De breedte van het object.
     *
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * De hoogte van het object.
     *
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * beweeg met een relatieve verplaatsing.
     *
     * @param dx verandering op de x-as (>0 = rechts)
     * @param dy verandering op de y-as (>0 = onder)
     */
    public void beweeg(int dx, int dy) {
        x = x + dx;
        y = y + dy;
    }

    /**
     * dx parameter negatief veranderen voor een beweging naar links. dx
     * verandering op de x-as (>0 = rechts)
     */
    public void gaLinks() {
        beweeg(-10, 0);
    }

    /**
     * dx parameter negatief veranderen voor een beweging naar rechts. dx
     * verandering op de x-as (>0 = rechts)
     */
    public void gaRechts() {
        beweeg(10, 0);
    }

    /**
     * dy parameter negatief veranderen voor een beweging naar boven. dy
     * verandering op de y-as (>0 = onder)
     */
    public void gaOnder() {
        beweeg(0, 10);
    }

    /**
     * dy parameter negatief veranderen voor een beweging naar onder. dy
     * verandering op de y-as (>0 = onder)
     */
    public void gaBoven() {
        beweeg(0, -10);
    }

    /**
     * De Status van het object veranderen van levend naar dood. true indien
     * dood, false indien levend.
     *
     * @param dood
     */
    public void setIsDood(boolean dood) {
        isDood = dood;
    }

    /**
     * Returnwaarde voor de vraag:"Is het object dood?"
     *
     * @return isDood status van het object.
     */
    public boolean getIsDood() {
        return isDood;
    }

}
