/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocketshooter.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



/**
 *
 * @author Jordi Jaspers
 * @author Salim Sadala
 */
public class LevelTest {
       String[] enemyColors = new String[4]; 
       String[] playerColors = new String[3];
       
    public LevelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of init method, of class Level.
     */
    @Test
    public void testInit() {
        System.out.println("init");
        for (int i = 0;i<=10000;i++){
        Level instance = new Level();
        instance.init();
        }
    }

  
    /**
     * Test of removeEnemy method, of class Level.
     */
    @Test
    public void testRemoveEnemy() {
        System.out.println("removeEnemy");
        for(int i = 0; i<= 10000; i++){
            for(int x = 0;x<=10;x++ ){
               for(int y = 0;y<=10;y++ ){
                Enemy enemy = new Enemy(x, y, 64, 64, ID.ENEMY, enemyColors[2]);
                Level instance = new Level();
                instance.removeEnemy(enemy);
                } 
            }
        }
    }
    
    /**
     * Test of enemyDood method, of class Level.
     */
    @Test
    public void testEnemyDood() {
        System.out.println("enemyDood");
        for(int i = 0; i<= 10000; i++){
            for(int x = 0;x<=4;x++ ){
               for(int y = 0;y<=10;y++ ){
                   for(int z = 0 ;z<=3;z++){
                      Enemy enemy = new Enemy(x,y, 64, 64, ID.ENEMY, enemyColors[z]);
                      Level instance = new Level();
                      instance.enemyDood(enemy);
                    }
                }
            }
        }
    }
    
    /**
     * Test of playerDood method, of class Level.
     */
    @Test
    public void testPlayerDood() {
        System.out.println("playerDood");
        for(int i = 0; i<= 10000; i++){
            for(int x = 0;x<=4;x++ ){
               for(int y = 0;y<=10;y++ ){
                   for(int z = 0 ;z<=2;z++){
                      Player player = new Player(x,y, 64, 64, ID.PLAYER, playerColors[z]);
                      Level instance = new Level();
                      instance.playerDood();
                    }
                }
            }
        }
    }
   
    /**
     * Test of beweeg method, of class Level.
     */
    @Test
    public void testBeweeg() {
        System.out.println("beweeg");
        for (int i=0;i<=10000;i++){
             int dx = 0;
            for (int y = 0; y<=100;y++){
                int dy = y;
                Level instance = new Level();
                instance.beweeg(dx, dy);
            }
        }
    }

    /**
     * Test of gaLinks method, of class Level.
     */
    @Test
    public void testGaLinks() {
        System.out.println("gaLinks");
        Level instance = new Level();
        instance.gaLinks();
    }

    /**
     * Test of gaRechts method, of class Level.
     */
    @Test
    public void testGaRechts() {
        System.out.println("gaRechts");
        Level instance = new Level();
        instance.gaRechts();
    }

    /**
     * Test of gaOnder method, of class Level.
     */
    @Test
    public void testGaOnder() {
        System.out.println("gaOnder");
        Level instance = new Level();
        instance.gaOnder();
    }

    /**
     * Test of gaBoven method, of class Level.
     */
    @Test
    public void testGaBoven() {
        System.out.println("gaBoven");
        Level instance = new Level();
        instance.gaBoven();    
    }
    
}
