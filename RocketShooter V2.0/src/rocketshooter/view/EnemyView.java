package rocketshooter.view;

import rocketshooter.model.Enemy;

/**
 *
 * Dit is een klasse voor de view van de enemy aan te maken (=uiterlijke
 * eigenschappen geven.)
 *
 * @author Jordi Jaspers
 * @author Salim Sadala
 */
public class EnemyView extends GameObjectView {

    /**
     * de view van onze enemy instellen.
     *
     * @param enemy de eigenschappen van onze enemy importeren om te laten zien.
     *
     */
    public EnemyView(Enemy enemy) {
        super(enemy);
    }
}
