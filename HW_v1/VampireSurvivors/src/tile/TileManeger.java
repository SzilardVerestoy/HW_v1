package tile;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManeger {
    
    GamePanel gp;
    public Tile[] tile;
    public int Map[][];
    
    public TileManeger(GamePanel gp){

        this.gp = gp;

        tile = new Tile[10];
        Map = new int[gp.maxWorldRow][gp.maxWorldCol];

        getTileImage();
        loadMap();
    }

    public void getTileImage(){

        try{

            tile[0] = new Tile();
            tile[0].image =  ImageIO.read(new File("res\\mapTiles\\grass00.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("res\\mapTiles\\grass01.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("res\\mapTiles\\wall.png"));
            tile[2].collision = true;


        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(){

        for(int i = 0; i < gp.maxWorldRow; i++){
            for(int j = 0; j < gp.maxWorldCol; j++){
                if(i == 0) Map[i][j] = 2;
                else if(i == gp.maxWorldRow - 1) Map[i][j] = 2;
                else if(j == 0) Map[i][j] = 2;
                else if(j == gp.maxWorldCol - 1) Map[i][j] = 2;
                else Map[i][j] = 1;
            }
        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldx + gp.player.screenX;
            int screenY = worldY - gp.player.worldy + gp.player.screenY;

            ///Csak akkor draw ha screen-en van + egy kicsi
            if(worldX + gp.tileSize > gp.player.worldx - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldx + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldy - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldy + gp.player.screenY){

                    g2.drawImage(tile[Map[worldRow][worldCol]].image, screenX, screenY , gp.tileSize, gp.tileSize, null);
                }
            
            worldCol++;

            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
