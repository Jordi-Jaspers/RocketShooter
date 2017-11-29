package rocketshooter.view;

import java.util.ArrayList;
import java.util.Iterator;
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
 * @author Jordi
 */
public class LevelView extends Region {

    private Level level;
    private Player player;
    private PlayerView vPlayer;
    private Enemy enemy;
    private EnemyView vEnemy;
    private Life life;
    private LifeView vLife;
    private ArrayList<BulletView> vBullets;

    public LevelView(Level level) {

        this.level = level;
        update();
    }

    public void update() {

        getChildren().clear();

        for (int i = 0; i < level.getAantalLevens(); i++) { ///niet poer se een model nodig.
            life = level.getLife()[i];
            vLife = new LifeView(life);
            getChildren().add(vLife);
        }

        player = level.getPlayerA();
        vPlayer = new PlayerView(player, "/resources/AIRPLANE_3.png");

        getChildren().add(vPlayer);

        for (int j = 0; j < level.getAantalEnemy(); j++) {
            enemy = level.getEnemyA()[j];
            vEnemy = new EnemyView(enemy, "/resources/UFO_BLUE.png");
            getChildren().add(vEnemy);

            enemy = level.getEnemyB()[j];
            vEnemy = new EnemyView(enemy, "/resources/UFO_YELLOW.png");
            getChildren().add(vEnemy);

            enemy = level.getEnemyC()[j];
            vEnemy = new EnemyView(enemy, "/resources/UFO_GREEN.png");
            getChildren().add(vEnemy);

            enemy = level.getEnemyD()[j];
            vEnemy = new EnemyView(enemy, "/resources/UFO_ORANGE.png");
            getChildren().add(vEnemy);
        }

        if (level.getBullet() != null) {
            vBullets = new ArrayList<>();
            Iterator<Bullet> bullets = level.getBullet();

            while (bullets.hasNext()) {
                Bullet bullet = bullets.next();
                vBullets.add(new BulletView(bullet, "/resources/BULLET.png"));
            }

            getChildren().addAll(vBullets);
        }
    }

}
