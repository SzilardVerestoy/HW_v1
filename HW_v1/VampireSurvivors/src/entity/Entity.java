package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    
    public int worldx;
    public int worldy;
    public int speed;

    public BufferedImage imgL, imgR;
    public String direction;
    public Rectangle solidArea; ///hitbox
    public boolean collisionOn = false;

    //Stats
    public int maxHealth;
    public int hp;
    public int damage;
    public int hitSpeed;
    public int cd;
    public boolean canHit;

}
