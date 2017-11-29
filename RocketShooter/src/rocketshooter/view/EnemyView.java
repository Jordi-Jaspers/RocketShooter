package rocketshooter.view;

import rocketshooter.model.Enemy;

/**
 *
 * Dit is een klasse voor de view van de enemy aan te maken (=uiterlijke
 * eigenschappen geven.)
 *
 * @author Jordi
 */
public class EnemyView extends GameObjectView {

    private Enemy enemy;

    /**
     * de view van onze enemy instellen.
     *
     * @param enemy de eigenschappen van onze enemy importeren om te laten zien.
     * @param url van het object, het pad naar de afbeelding.
     *
     */
    public EnemyView(Enemy enemy, String url) {
        super(enemy, url);
    }

    /**
     * returnwaarden geven van onze klasse enemy.
     *
     * @return enemy
     */
    public Enemy getEnemy() {
        return enemy;
    }

}
