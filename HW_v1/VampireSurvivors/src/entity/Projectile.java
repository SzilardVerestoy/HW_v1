package entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Projectile extends Entity{
    
    GamePanel gp;
    int timeActive;

    public void set() {

    }

    public void update() {
        
    }

    public void draw(Graphics g2){

        BufferedImage image = null;

        
            int screenX = worldx - gp.player.worldx + gp.player.screenX;
            int screenY = worldy - gp.player.worldy + gp.player.screenY;

            g2.drawImage(imgL, screenX, screenY, gp.tileSize / 2, gp.tileSize / 2, null);
    }

    public void dispose(){

        gp.projectile.remove(this);
    }

}
