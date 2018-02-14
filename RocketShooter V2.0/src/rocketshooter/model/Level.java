package rocketshooter.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * alles wat in het level gaat gebeuren wordt samengevoegd in deze klasse. zoals
 * het toevoegen en acties van objecten, etc.
 *
 * @author Jordi Jaspers
 * @author Salim Sadala
 */
public class Level {

    public final int WIDTH = 640;                                                     //standaard maten voor onze window.
    public final int HEIGHT = 420;

    private final int beginX = 50;                                                      //Begincoordinaten voor de speler.
    private final int beginY = 202;

    private final int enemiesRows = 4;                                               //Aantal enemies per rij.
    private final int enemiesCols = 8;                                             //Aantal enemies per kolom.

    private int scoreTeller;                                                               //score van het game.
    private int aantalLevens;                                                           //leven van de speler.
    private int startingColor;                                                             //Begin Afbeelding van de speler.

    String[] enemyColors = new String[4];                                           //verschillende kleuren voor enemies.
    String[] playerColors = new String[3];                                          //Verschillende afbeeldingen voor player.

    private Player playerA;                                                                 //spelers toevoegen.

    private ArrayList<Enemy> enemies;                                           //2D Enemy arrayList aanmaken.

    private ArrayList<Life> lifes;                                                        //lijst van levens toevoegen.

    private boolean gameStarted = false;                                        //game bezig of niet?

    /**
     * Constructor van level die gewoon het spel initialiseert.
     */
    public Level() {

        enemyColors[0] = "/resources/UFO_YELLOW.png";
        enemyColors[1] = "/resources/UFO_GREEN.png";
        enemyColors[2] = "/resources/UFO_ORANGE.png";
        enemyColors[3] = "/resources/UFO_BLUE.png";

        playerColors[0] = "/resources/AIRPLANE_1.png";
        playerColors[1] = "/resources/AIRPLANE_2.png";
        playerColors[2] = "/resources/AIRPLANE_3.png";

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

        aantalLevens = 2;
        scoreTeller = 0;
        startingColor = getStartingColor();

        lifes = new ArrayList<>();

        //+1 want je eerste leven telt ook mee
        for (int i = 0; i < aantalLevens + 1; i++) {
            lifes.add(new Life(45 + i * 13, 15));
        }

        playerA = new Player(beginX, beginY, 64, 64, ID.PLAYER, playerColors[startingColor]);

        enemies = new ArrayList<>();

        Random random = new Random();

        //Enemy 2D Arraylist met [enemiesCols][enemiesRows];
        for (int i = 0; i < enemiesCols; i++) {
            for (int j = 0; j < enemiesRows; j++) {
                enemies.add(new Enemy(WIDTH - 100 - 64 * j, 50 + i * (HEIGHT - 164) / ((enemiesCols) - 1), 64, 64, ID.ENEMY, enemyColors[random.nextInt(4)]));
            }
        }
    }

    /**
     * getter voor de aangemaakte player in het spel.
     *
     * @return playerA
     */
    public Player getPlayerA() {
        return playerA;
    }

    /**
     * Geeft weer welke afbeelding je gekozen hebt.
     *
     * @return startingColor
     */
    public int getStartingColor() {
        return startingColor;
    }

    /**
     * kiezen welke afbeelding je wilt hebben voor de player.
     *
     * [0] = "/resources/AIRPLANE_1.png"; [1] = "/resources/AIRPLANE_2.png"; [2]
     * = "/resources/AIRPLANE_3.png";
     *
     * @param startingColor kiezen tussen [0, 1, 2]
     */
    public void setStartingColor(int startingColor) {
        if (startingColor > 2) {
            System.out.println("Error 404: Color Not Found!");
        } else {
            this.startingColor = startingColor;
        }
    }

    /**
     * een getter voor de 2D array van de enemies.
     *
     * @return enemies
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Verwijder een enemy van de lijst met enemies.
     *
     * @param enemy
     */
    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
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
     * dit zal een kogel genereren die afgeschoten wordt voor de player.
     *
     * @return bullet (= een kogel die gemaakt is door de speler.)
     */
    public Bullet shootBulletByPlayer() {
        return new Bullet(playerA.getX() + 40, playerA.getY() + 30, 32, 20, ID.PLAYER, "/resources/BULLET1.png");
    }

    /**
     * dit zal een kogel genereren die afgeschoten wordt voor de enemy.
     *
     * @param enemy (= de enemy die de kogel afschiet.)
     * @return bullet (= een kogel die gemaakt is door de enemy.)
     */
    public Bullet shootBulletByEnemy(Enemy enemy) {
        return new Bullet(enemy.getX() + 40, enemy.getY() + 30, 32, 20, ID.ENEMY, "/resources/BULLET2.png");
    }

    /**
     * Als er een vijand doogeschoten is dan gaat de score omhoog naarmate de de
     * kleur van de enemy. erna wordt de statische context geupdate.
     *
     * @param enemy die dood is gegaan.
     */
    public void enemyDood(Enemy enemy) {
        if (enemy.isDood) {
            if (enemy.getUrl().equals(enemyColors[0])) {
                setScoreTeller(10);
            }

            if (enemy.getUrl().equals(enemyColors[1])) {
                setScoreTeller(20);
            }

            if (enemy.getUrl().equals(enemyColors[2])) {
                setScoreTeller(50);
            }

            if (enemy.getUrl().equals(enemyColors[3])) {
                setScoreTeller(100);
            }
        }
    }

    /**
     * Deze methode brengt de speler terug tot leven indien hij nog genoeg
     * levens heeft. als er geen levens meer zijn dan eindigt die het spel.
     */
    public void playerDood() {

        if (playerA.getIsDood() == true && aantalLevens > 0) {

            playerA.setX(beginX);
            playerA.setY(beginY);
            lifes.remove(aantalLevens);
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
     * Geef de lijst met levens terug.
     *
     * @return life
     */
    public ArrayList<Life> getLife() {
        return lifes;
    }

    /**
     * Vraag de integer waarde terug van de score.
     *
     * @return scoreTeller aantal punten gemaakt in het spel.
     */
    public int getScoreTeller() {
        return scoreTeller;
    }

    /**
     * Verhoogt de score met de nodige aantalpunten.
     *
     * @param aantalPunten
     */
    public void setScoreTeller(int aantalPunten) {
        scoreTeller = scoreTeller + aantalPunten;
    }

    /**
     * beweeg met een relatieve verplaatsing.
     *
     * @param dx verandering op de x-as (>0 = rechts)
     * @param dy verandering op de y-as (>0 = onder)
     */
    protected void beweeg(int dx, int dy) {

        int doelX = playerA.getX() + dx;
        int doelY = playerA.getY() + dy;

        if (doelX >= WIDTH) {           //Speler mag niet buiten het veld
            return;
        }
        if (doelX < 0) {                //Speler mag niet buiten het veld
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

}
