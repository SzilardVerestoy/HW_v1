package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Enemy extends Entity{
    

    GamePanel gp;

    public Enemy(GamePanel gp) {

        this.gp = gp;        
    }

    public void update(){

        collisionOn = false;
       // gp.cDetect.checkTile(this);
        gp.cDetect.checkPlayer(this);
        
        
        for(int i = 0; i < gp.nmy.size(); i++){
            
            gp.cDetect.check2Entity(this, gp.nmy.get(i));
        }

        if(hp <= 0) {
            gp.nmy.remove(this);
            gp.player.kill++;
        }
        else{
            if(collisionOn == false) {

            if(worldx < gp.player.worldx) worldx += speed; 
            if(worldx > gp.player.worldx) worldx -= speed;
            if(worldy < gp.player.worldy) worldy += speed;
            if(worldy > gp.player.worldy) worldy -= speed;    
            }
            if(worldx < gp.player.worldx) direction = "right";
            else direction = "left";

            if(cd > 0){
                cd -=1;
            }
            else{
                canHit = true;
            }
        }
        
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        
            int screenX = worldx - gp.player.worldx + gp.player.screenX;
            int screenY = worldy - gp.player.worldy + gp.player.screenY;

            switch (direction) {
                case "left":
                    image = imgL;
                    break;
                case "right":
                    image = imgR;
                    break;        
                default:
                    break;
            }

            ///Csak akkor draw ha screen-en van + egy kicsi
            if(worldx + gp.tileSize > gp.player.worldx - gp.player.screenX &&
                worldx - gp.tileSize < gp.player.worldx + gp.player.screenX &&
                worldy + gp.tileSize > gp.player.worldy - gp.player.screenY &&
                worldy - gp.tileSize < gp.player.worldy + gp.player.screenY){
                g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
           
    }

    public void setLocation(int X, int Y){

        this.worldx = X;
        this.worldy = Y;
    }
}
