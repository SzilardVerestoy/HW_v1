package entity;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Fireball extends Projectile{
    
    

    public Fireball(GamePanel gp){

        this.gp = gp;

        speed = 7;
        timeActive = 80;
        damage = gp.player.damage;


        worldx = gp.player.worldx;
        worldy = gp.player.worldy + gp.tileSize / 2;
        direction = gp.player.direction;

        solidArea = new Rectangle(0,0,gp.tileSize / 4, gp.tileSize / 4);

        getImage();
        
    }

    public void getImage() {
        try {
            imgL = ImageIO.read(new File("res\\projectile\\fireball_left_1.png"));
            imgR = ImageIO.read(new File("res\\projectile\\fireball_right_1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        
        timeActive--;
        
        if(timeActive <= 0) gp.projectile.remove(this);

        if(collisionOn == false){

            if(timeActive > 0){

            for(int i = 0; i < gp.projectile.size(); i++){

                Entity entity1 = gp.projectile.get(i);

                if(entity1.collisionOn == false){

                    for(int j = 0; j < gp.nmy.size(); j++){
                        gp.cDetect.check2Entity(entity1, gp.nmy.get(j));
                        if(entity1.collisionOn == true) {
                            gp.projectile.remove(entity1);
                            gp.nmy.get(j).hp -= entity1.damage;
                        }
                    }
                }

                
            }

            if(direction != null){
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


        }


    }
}
