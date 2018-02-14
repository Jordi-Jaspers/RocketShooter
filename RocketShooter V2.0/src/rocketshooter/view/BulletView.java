package rocketshooter.view;

import rocketshooter.model.Bullet;

/**
 * Dit is een klasse voor de view de levens van de speler.
 *
 * @author Jordi Jaspers
 * @author Salim Sadala
 */
public class BulletView extends GameObjectView {

    /**
     * de view van onze kogel instellen.
     *
     * @param bullet de eigenschappen van onze kogel importeren om te laten
     * zien.
     *
     */
    public BulletView(Bullet bullet) {
        super(bullet);
    }
}
