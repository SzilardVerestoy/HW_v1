package main;

import java.awt.Rectangle;

import entity.Entity;

public class CollisionDetect {
    
    GamePanel gp;

    public CollisionDetect(GamePanel gp) {
        this.gp = gp;
    }

    public void checkPlayer(Entity entity) {

        int entityX = entity.worldx + entity.solidArea.x;
        int entityY = entity.worldy + entity.solidArea.y;
        int playerX = gp.player.worldx + gp.player.solidArea.x;
        int playerY =  gp.player.worldy + gp.player.solidArea.y;

        Rectangle entityHitbox = new Rectangle(entityX, entityY, entity.solidArea.width, entity.solidArea.height);
        Rectangle playerHitbox = new Rectangle(playerX, playerY, gp.player.solidArea.width, gp.player.solidArea.height);

        if(entityHitbox.intersects(playerHitbox)) {
            entity.collisionOn = true;
            if(entity.canHit == true){
                gp.player.hp -= entity.damage;
                entity.cd = entity.hitSpeed;
                entity.canHit = false;
            }
            
        }
    }


    public void check2Entity(Entity entity1, Entity entity2){

        if(entity1.collisionOn == false){
            int entity1X = entity1.worldx + entity1.solidArea.x;
            int entity1Y = entity1.worldy + entity1.solidArea.y;
            int entity2X = entity2.worldx + entity2.solidArea.x;
            int entity2Y = entity2.worldy + entity2.solidArea.y;

            Rectangle entity1Hitbox = new Rectangle(entity1X, entity1Y, entity1.solidArea.width, entity1.solidArea.height);
            Rectangle entity2Hitbox = new Rectangle(entity2X, entity2Y, entity2.solidArea.width, entity2.solidArea.height);
            if(entity1Hitbox.intersects(entity2Hitbox)) entity1.collisionOn = true;
        }
        entity1.collisionOn = true;

        
    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldx + entity.solidArea.x;
        int entityRightWorldX = entity.worldx + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldy + entity.solidArea.y;
        int entityBottomWorldY = entity.worldy + entity.solidArea.y + entity.solidArea.height;

        ///hitbox sarkai
        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        ///amiket nezni kell
        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.Map[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.Map[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.Map[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.Map[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.Map[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.Map[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.Map[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.Map[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            default:
                break;
        }
    }
}
