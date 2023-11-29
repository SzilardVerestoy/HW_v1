package entity;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Jawa extends Enemy{

    public Jawa(GamePanel gp) {
        
        super(gp);

        solidArea = new Rectangle(8, 16, 16, 16);


        try {
            imgL = ImageIO.read(new File("res\\enemy\\jawa\\Jawa_L.png"));
            imgR = ImageIO.read(new File("res\\enemy\\jawa\\Jawa_R.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        speed = 1;
        maxHealth = 40;
        hp = maxHealth;
        direction = "left";
        damage = 10;
        hitSpeed = 60;
        cd = 0;
        canHit = true;
    }

    
}
