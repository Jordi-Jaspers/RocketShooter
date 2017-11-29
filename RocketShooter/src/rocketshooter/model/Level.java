package rocketshooter.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * alles wat in het level gaat gebeuren wordt samengevoegd in deze klasse. zoals
 * het toevoegen en acties van objecten, etc.
 *
 * @author Jordi
 */
public class Level {

    public final int WIDTH = 640;                                               //standaard maten voor onze window.
    public final int HEIGHT = 420;

    private Player playerA;                                                     //spelers toevoegen.
    private Enemy[] enemyA;                                                     //Enemy toevoegen.
    private Enemy[] enemyB;                                                     //Enemy toevoegen.
    private Enemy[] enemyC;                                                     //Enemy toevoegen.
    private Enemy[] enemyD;                                                     //Enemy toevoegen.
    private Life[] life;                                                        //lijst van levens toevoegen.
    private Bullet bullet;                                                      //kogel aanmaken.
    private ArrayList<Bullet> bullets = new ArrayList<>();                      //kogels in een lijst zetten.

    private double playerX;                                                     //x-co van speler.
    private double playerY;                                                     //y-co van speler.

    private final double beginX = 50.0;                                         //Begincoordinaten voor de speler.
    private final double beginY = 202.0;

    private int scoreTeller;                                                    //score van onze game.
    private int aantalEnemy;                                                    //aantal vijhanden.
    private int aantalLevens;                                                   //leven van de speler.

    private boolean gameStarted = false;                                        //game bezig of niet?

    /**
     * Constructor van level die gewoon het spel initialiseert.
     */
    public Level() {
        init();
    }

    /**
     * als het spel herstart moet worden, gewoon opnieuw initialiseren.
     */
    public void restart() {
        init();
    }

    /**
     * deze methode initialiseert effectief het spel door alles in te laden wat
     * er in het level moet bevinden en gebeuren.
     */
    public void init() {

        scoreTeller = 0;
        aantalEnemy = 8;                                                        //aantal enemies per kolom

        aantalLevens = 3;

        life = new Life[aantalLevens];

        for (int i = 0; i < aantalLevens; i++) {
            life[i] = new Life(45 + i * 13, 15);
        }

        playerA = new Player(beginX, beginY, 64, 64, ID.PLAYER);

        enemyA = new Enemy[aantalEnemy];
        enemyB = new Enemy[aantalEnemy];
        enemyC = new Enemy[aantalEnemy];
        enemyD = new Enemy[aantalEnemy];

        for (int j = 0; j < aantalEnemy; j++) {

            enemyA[j] = new Enemy(WIDTH - 100, 50 + j * (HEIGHT - 164) / ((aantalEnemy) - 1), 64, 64, ID.ENEMY);
            enemyB[j] = new Enemy(WIDTH - 150, 50 + j * (HEIGHT - 164) / ((aantalEnemy) - 1), 64, 64, ID.ENEMY);
            enemyC[j] = new Enemy(WIDTH - 200, 50 + j * (HEIGHT - 164) / ((aantalEnemy) - 1), 64, 64, ID.ENEMY);
            enemyD[j] = new Enemy(WIDTH - 250, 50 + j * (HEIGHT - 164) / ((aantalEnemy) - 1), 64, 64, ID.ENEMY);

            //Bovenstaande formule (=Kolomafstand voor de enemies, onze werkruimte verdeeld onder het aantal enemies)
        }
    }

    /**
     * antwoorden op de vraag:"Is het spel gestart?"
     *
     * @return gamestarted
     */
    public boolean getGameStarted() {
        return gameStarted;
    }

    /**
     * een waarde geven voor het Gamestarted. (true = gestart, false =
     * niet-gestart)
     *
     * @param gameStarted
     */
    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    /**
     * Returnwaarde geven van X-co van de speler.
     *
     * @return playerX
     */
    public double getPlayerX() {
        return playerX;
    }

    /**
     * Returnwaarde geven van Y-co van de speler.
     *
     * @return playerY
     */
    public double getPlayerY() {
        return playerY;
    }

    /**
     * Deze methode brengt de speler terug tot leven indien hij nog genoeg
     * levens heeft. als er geen levens meer zijn dan eindigt die het spel.
     */
    public void playerDood() {

        if (playerA.getIsDood() == true && aantalLevens > 0) {

            playerX = beginX;
            playerY = beginY;
            life[aantalLevens] = null;
            aantalLevens--;
            playerA.setIsDood(false);

        } else if (playerA.getIsDood() == true && aantalLevens == 0) {

            gameStarted = false;

        }

    }

    /**
     * Returnwaarde geven van de levens van de speler
     *
     * @return aantalLevens
     */
    public int getAantalLevens() {
        return aantalLevens;
    }

    /**
     * Levens instellen van de speler.
     *
     * @param aantalLevens
     */
    public void setAantalLevens(int aantalLevens) {
        this.aantalLevens = aantalLevens;
    }

    /**
     * @return playerA
     */
    public Player getPlayerA() {
        return playerA;
    }

    /**
     * @return the enemyA
     */
    public Enemy[] getEnemyA() {
        return enemyA;
    }

    /**
     * @return the enemyB
     */
    public Enemy[] getEnemyB() {
        return enemyB;
    }

    /**
     * @return the enemyC
     */
    public Enemy[] getEnemyC() {
        return enemyC;
    }

    /**
     * @return the enemyD
     */
    public Enemy[] getEnemyD() {
        return enemyD;
    }

    /**
     * @return life
     */
    public Life[] getLife() {
        return life;
    }

    /**
     * Ittereert door de lijst voor het object dat je wilt hebben.
     *
     * @return bullets
     */
    public Iterator<Bullet> getBullet() {
        return bullets.iterator();
    }

    /**
     * Returnwaarde geven voor de totale score
     *
     * @return scoreTeller
     */
    public int getScoreTeller() {
        return scoreTeller;
    }

    /**
     * Returnwaarde geven voor het aantal enemy's die er zijn.
     *
     * @return aantalEnemy
     */
    public int getAantalEnemy() {
        return aantalEnemy;
    }

    /**
     * Als er een vijand doogeschoten is dan gaat de score omhoog.
     */
    public void score() {

        for (int i = 1; i < aantalEnemy + 1; i++) {

            if (getEnemyA()[i].isDood == true) {

                scoreTeller++;
                aantalEnemy--;

            }
        }

    }

    /**
     * beweeg met een relatieve verplaatsing.
     *
     * @param dx verandering op de x-as (>0 = rechts)
     * @param dy verandering op de y-as (>0 = onder)
     */
    protected void beweeg(double dx, double dy) {

        double doelX = playerA.getX() + dx;
        double doelY = playerA.getY() + dy;

        if (doelX >= WIDTH) {           //Speler mag niet buiten het veld
            return;
        }
        if (doelX < 0) {
            return;
        }
        if (doelY >= HEIGHT - 50) {     //Vliegtuigen vliegen niet op land
            return;
        }
        if (doelY < 50) {               //onder de informatieve text blijven.
            return;
        }

        // playerA.setX(doelX); mag niet bewegen in de X-richting.
        playerA.setY(doelY);
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
     * dit zal een kogel genereren die afgeschoten wordt door player.
     */
    public void shootBullet() {
        bullet = new Bullet(playerA.getX() + 40, playerA.getY() + 30, 16, 12, ID.BULLET);
        bullets.add(bullet);

    }

    /**
     * is de kogel raak of op het einde van het scherm dan verwijder deze
     * meteen.
     */
    public void beweegBullet() {

        double doelX = bullet.getX() + 5;
        double doelY = bullet.getY();

        for (int i = 0; i < aantalEnemy; i++) {
            if (bullet.getX() == enemyA[i].getX() && bullet.getY() == enemyA[i].getY()) {
                bullets.remove(bullet);
            }
            if (bullet.getX() == enemyB[i].getX() && bullet.getY() == enemyB[i].getY()) {
                bullets.remove(bullet);
            }
            if (bullet.getX() == enemyC[i].getX() && bullet.getY() == enemyC[i].getY()) {
                bullets.remove(bullet);
            }
            if (bullet.getX() == enemyD[i].getX() && bullet.getY() == enemyD[i].getY()) {
                bullets.remove(bullet);
            }
            if (bullet.getX() == WIDTH) {
                bullets.remove(bullet);
            }
        }

    }

    /**
     * Het traject dat de enemy afzonderlijk aflegt.
     */
    public void beweegEnemy() {

        do {
            for (int i = 0; i < aantalEnemy; i++) {
                enemyA[i].setY(enemyA[i].getY() - 5);
                enemyB[i].setY(enemyA[i].getY() - 5);
                enemyC[i].setY(enemyA[i].getY() - 5);
                enemyD[i].setY(enemyA[i].getY() - 5);
            }
        } while (enemyA[0].getY() > 50);

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < aantalEnemy; i++) {
                enemyA[i].setX(enemyA[i].getX() + 5);
                enemyB[i].setX(enemyA[i].getX() + 5);
                enemyC[i].setX(enemyA[i].getX() + 5);
                enemyD[i].setX(enemyA[i].getX() + 5);
            }
        }

        do {
            for (int i = 0; i < aantalEnemy; i++) {
                enemyA[i].setY(enemyA[i].getY() + 5);
                enemyB[i].setY(enemyA[i].getY() + 5);
                enemyC[i].setY(enemyA[i].getY() + 5);
                enemyD[i].setY(enemyA[i].getY() + 5);
            }
        } while (enemyA[aantalEnemy].getY() < HEIGHT - 50);

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < aantalEnemy; i++) {
                enemyA[i].setX(enemyA[i].getX() - 5);
                enemyB[i].setX(enemyA[i].getX() - 5);
                enemyC[i].setX(enemyA[i].getX() - 5);
                enemyD[i].setX(enemyA[i].getX() - 5);
            }
        }
    }
}


