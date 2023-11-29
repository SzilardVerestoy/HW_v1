package main;

import javax.swing.JPanel;
import javax.swing.Timer;

import entity.Enemy;
import entity.Fireball;
import entity.Jawa;
import entity.Player;
import entity.Projectile;
import tile.TileManeger;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Color;

public class GamePanel extends JPanel implements Runnable{
    
    ///Screen settings
    final int ogTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = ogTileSize * scale; // 48x48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //  768 p
    public final int screenHeight = tileSize * maxScreenRow; // 576 p


    ///World
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    /// FPS
    int FPS = 60;


    ///Util
    TileManeger tileM = new TileManeger(this);
    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    public Sound sound = new Sound();
    public Timer timer;
    public Time TIME;
    UI ui = new UI(this);
    public CollisionDetect cDetect = new CollisionDetect(this);
    public Player player = new Player(this, keyH);
    public List<Enemy> nmy = new ArrayList<>();
    public List<Projectile> projectile = new ArrayList<>();

    public int gameState;
    public final int mainState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int gameoverState = 3;
    public Random random = new Random();

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); /// performance boost
        this.addKeyListener(keyH);
        this.setFocusable(true);

        gameState = mainState;

        ///nmy.add(new Jawa(this));

        TIME = new Time();
        startClock(this);
        playMusic(1);
        
    }

    public void startGameThread() {
        
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; ///~16.6 s frame time; 60fps
        double nextDrawTime = System.nanoTime() + drawInterval;
        
        while(gameThread != null){
            
            update();

            repaint();

            
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000; /// millisecond to nano

                if(remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long)remainingTime); 

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } 
    }

    public void update() {

        if(gameState == playState) {
            timer.start();
            player.update();

            spawnEnemy();

            for(int i = 0; i < projectile.size(); i++){

                projectile.get(i).update();
            }
            
            for(int i = 0; i < nmy.size(); i++){

                nmy.get(i).update();
            }
        }

        if(gameState == pauseState){
            timer.stop();
            sound.stop();
        }

        
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;


        ///Start screen
        if(gameState == mainState){
            ui.draw(g2);
        }

        ///after
        else{
            tileM.draw(g2);

            player.draw(g2);

            for(int i = 0; i < nmy.size(); i++){

                nmy.get(i).draw(g2);
            }

            for(int i = 0; i < projectile.size(); i++){

                projectile.get(i).draw(g2);
            }

            ui.draw(g2);
        }

        g2.dispose();
    }

    public void spawnEnemy() {

        while (nmy.size() < 10) {
            addNEnemy(1);
        }

        switch (TIME.phase) {
            case 1:
                addNEnemy(12);
                break;
        
            default:
                break;
        }
    }

    public class Time{

        public int seconds;
        public int minutes;
        public int phase;

        public Time(){

            seconds = 0;
            minutes = 0;
            phase = 1;
        }
    }

    public void startClock(GamePanel gp) {

        timer = new Timer(1000, new ActionListener() {
            
            public void actionPerformed(ActionEvent ae){

                TIME.seconds++;
                if(TIME.seconds == 60){
                    TIME.seconds = 0;
                    TIME.minutes++;
                    //TIME.phase++;
                }
            }
        });

        timer.start();
    } 
    
    public void addNEnemy(int n){

        int x = player.worldx;
        int y = player.worldy;

        for(int i = 0; i < n; i++){

            Jawa j = new Jawa(this);
            int dir = random.nextInt(4);
            int offset = random.nextInt(8); ///48 x 5; 5 tile
            switch (dir) {
                ///Fentre
                case 0:
                    if(offset % 2 == 0) x += (offset + 6) * tileSize;
                    else x -= (offset + 6) * tileSize;
                    y -=  tileSize + 1;
                    break;
                ///Lentre
                case 1:
                    if(offset % 2 == 0) x += (offset + 6) * tileSize;
                    else x -= (offset + 6) * tileSize;
                    y +=  tileSize;
                    break;
                ///Bal
                case 2:
                    if(offset % 2 == 0) y += (offset + 6) * tileSize;
                    else y -= (offset + 6) * tileSize;
                    x -=  tileSize;
                    break;
                ///Jobb
                case 3:
                    if(offset % 2 == 0) y += (offset + 6) * tileSize;
                    else y -= (offset + 6) * tileSize;
                    x +=  tileSize;
                default:
                    break;
            }

            j.setLocation(x , y);
            nmy.add(j);
            TIME.phase++;
        }

    }

    public void playMusic(int i){

        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic(){

        sound.stop();
    }





}
