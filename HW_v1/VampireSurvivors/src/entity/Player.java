package entity;

import main.KeyHandler;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Player extends Entity{
    
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int kill;
    public int level;


    public Player(GamePanel gp, KeyHandler keyH){

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2); /// kozepre, mert topLeft van
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle( 8, 16, 32, 32);
        
        kill = 0;
        level = 0;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){

        worldx = 10 * gp.tileSize;
        worldy = 20 * gp.tileSize;
        speed = 4;
        direction = "right";
        damage = 100;

        maxHealth = 100;
        hp = maxHealth;
    }

    public void getPlayerImage(){

        try{
            imgL = ImageIO.read(new File("res/player/DarthVader_left.png"));
            imgR = ImageIO.read(new File("res/player/DarthVader_right.png"));
        }catch(IOException e){
            //e.printStackTrace();

            System.out.println("Baj van");
        }
    }

    public void update() {

        if(keyH.upPressed == true || keyH.downPressed == true ||
            keyH.leftPressed == true || keyH.rightPressed == true){

            if(keyH.upPressed == true) {
                direction = "up";
                
            }
            if(keyH.downPressed == true){
                direction = "down";
            
            }
            if(keyH.leftPressed == true){
                direction = "left";
            
            }
            if(keyH.rightPressed == true){
                direction = "right";
                
            }

            ///Collision
            collisionOn = false;
            gp.cDetect.checkTile(this);

            if(collisionOn == false) {
                switch (direction) {
                    case "up": worldy -= speed;
                        break;
                    case "down":  worldy += speed;
                        break;
                    case "left":  worldx -= speed;
                        break;
                    case "right": worldx += speed;
                        break;
                    default:
                        break;
                }
            }
        }

        if(hp <= 0) {
            gp.gameState = gp.gameoverState;
            gp.sound.stop();
        }

    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
                image = imgL;
                break;
            case "down":
                image = imgR;
                break;
            case "left":
                image = imgL;
                break;
            case "right":
                image = imgR;
                break;        
            default:
                break;
        }


        //HP bar

        double unit = (double)gp.tileSize / maxHealth;
        double value = unit * hp;

        g2.setColor(Color.BLACK);
        g2.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 12);
        g2.setColor(Color.RED);
        g2.fillRect(screenX, screenY - 15, (int)value, 10);
        

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

}
