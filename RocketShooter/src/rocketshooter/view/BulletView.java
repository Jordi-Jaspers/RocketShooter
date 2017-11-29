package rocketshooter.view;

import rocketshooter.model.Bullet;

/**
 * Dit is een klasse voor de view de levens van de speler.
 *
 * @author Jordi
 */
public class BulletView extends GameObjectView {

    private Bullet bullet;

    /**
     * de view van onze kogel instellen.
     *
     * @param bullet de eigenschappen van onze kogel importeren om te laten
     * zien.
     * @param url van het object, het pad naar de afbeelding.
     *
     */
    public BulletView(Bullet bullet, String url) {
        super(bullet, url);
    }

    /**
     * returnwaarden geven van onze klasse kogel.
     *
     * @return bullet
     */
    public Bullet getBullet() {
        return bullet;
    }

}
