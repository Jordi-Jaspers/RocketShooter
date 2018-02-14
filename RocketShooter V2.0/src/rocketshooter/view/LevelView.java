package rocketshooter.view;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import rocketshooter.model.Bullet;
import rocketshooter.model.Enemy;
import rocketshooter.model.Player;
import rocketshooter.model.Level;
import rocketshooter.model.Life;

/**
 * Deze klasse gaat alles weergeven wat op het level moet gaan verschijnen
 * wanneer we aan het spelen zijn, zoals enemies, spelers, kogels, etc....
 *
 * @author Jordi Jaspers
 * @author Salim Sadala
 */
public class LevelView extends Region {

    private Level level;

    private Player player;
    private PlayerView vPlayer;

    private ArrayList<EnemyView> vEnemies;
    private Iterator<EnemyView> vEnemiesStorage;

    private Iterator<Enemy> enemyStorage;

    private Iterator<Life> lifeStorage;

    private ArrayList<LifeView> vLifes;
    private Iterator<LifeView> vLifeStorage;

    private Enemy enemyToDelete = null;

    /**
     * De constructor van LevelView die de eigenschappen van het gemaakte Level
     * meeneemt.
     *
     * @param level
     */
    public LevelView(Level level) {
        this.level = level;

        vEnemies = new ArrayList<>();
        vLifes = new ArrayList<>();

        updateStaticContext();
    }

    /**
     * update de view van alle enemies.
     */
    public void updateEnemyView() {
        if (!vEnemies.isEmpty()) {
            vEnemiesStorage = vEnemies.iterator();
            while (vEnemiesStorage.hasNext()) {
                getChildren().remove(vEnemiesStorage.next());
            }
        }

        vEnemies.clear();

        enemyStorage = level.getEnemies().iterator();

        while (enemyStorage.hasNext()) {
            Enemy enemyCheck = enemyStorage.next();
            if (enemyCheck.equals(enemyToDelete)) {
                enemyStorage.remove();
                enemyToDelete = null;
            } else {
                EnemyView enemyView = new EnemyView(enemyCheck);
                vEnemies.add(enemyView);
                getChildren().add(enemyView);
            }
        }
    }

    /**
     * Update de view van de player.
     */
    public void UpdatePlayerPosition() {
        getChildren().remove(vPlayer);

        player = level.getPlayerA();
        vPlayer = new PlayerView(player);
        getChildren().add(vPlayer);
    }

    /**
     * Update statische objecten in het spel.
     */
    public void updateStaticContext() {
        if (!vLifes.isEmpty()) {
            vLifeStorage = vLifes.iterator();
            while (vLifeStorage.hasNext()) {
                getChildren().remove(vLifeStorage.next());
            }

        }
        vLifes.clear();
        lifeStorage = level.getLife().iterator();

        while (lifeStorage.hasNext()) {

            LifeView vLife = new LifeView(lifeStorage.next());
            vLifes.add(vLife);
            getChildren().add(vLife);
        }
    }

    /**
     * Update de view van de kogel. Als de kogel van een Enemy is wordt die 180
     * graden gedraaid.
     *
     * @param bullet geschoten kogel.
     */
    public void updateBulletView(Bullet bullet) {
        deleteBulletView(bullet);

        BulletView tempView = new BulletView(bullet);

        getChildren().add(tempView);
    }

    /**
     * Verwijder de kogel die geschoten wordt.
     *
     * @param bullet geschoten kogel.
     */
    public void deleteBulletView(Bullet bullet) {
        for (Node node : getChildren()) {
            if (node instanceof BulletView) {
                if (bullet.equals(((BulletView) node).getGameObject())) {
                    getChildren().remove(node);
                    break;
                }
            }
        }
    }

    /**
     * verwijder een specifieke enemy uit de enemylijst.
     *
     * @param enemy
     */
    public void deleteEnemy(Enemy enemy) {
        this.enemyToDelete = enemy;
    }
}
