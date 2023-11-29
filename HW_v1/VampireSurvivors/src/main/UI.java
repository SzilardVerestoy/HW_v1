package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

public class UI {
    
    Graphics2D g2;
    GamePanel gp;
    Font mainFont;
    Font secFont;
    DecimalFormat dForm = new DecimalFormat("00");
    public int cmd = 0;
    public int maxCmd = 1;

    public UI(GamePanel gp){
        this.gp = gp;
        mainFont = new Font("Arial", Font.BOLD, 69);
        secFont = new Font("Arial", Font.PLAIN, 23);
    }

    public void draw(Graphics2D g2){

        this.g2 = g2;

        g2.setColor(Color.WHITE);
        g2.setFont(mainFont);

        if(gp.gameState == gp.mainState){

            drawMainScreen();
        }

        if(gp.gameState == gp.playState){
            drawTimer();
            drawKillCounter();
        }
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
            drawTimer();
        }

        if(gp.gameState == gp.gameoverState) {
            drawGameoverScreen();
        }
    }

    public void drawMainScreen(){

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,80));

        String text = "Vampire Survivors";
        int x = centerX(text);
        int y = gp.screenHeight / 2 - 2 * gp.tileSize;


        //Shadow
        g2.setColor(Color.GRAY);
        g2.drawString(text, x + 3, y + 3);

        ///Title
        g2.setColor(Color.RED);
        g2.drawString(text, x, y);

        ///Test image

        g2.drawImage(gp.player.imgL, 20, 20, gp);

        ///Menu

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40));
        g2.setColor(Color.WHITE);

        text = "Start Game";
        x = centerX(text);
        y = gp.screenHeight / 2 + gp.tileSize;
        g2.drawString(text, x, y);
        if(cmd == 0) {
            g2.drawString(">", x - gp.tileSize, y);
            g2.drawString("<", x + (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth() + gp.tileSize, y);
        }

        text = "Quit";
        x = centerX(text);
        y += 3 * gp.tileSize;
        g2.drawString(text, x, y);
        if(cmd == 1) {
            g2.drawString(">", x - gp.tileSize, y);
            g2.drawString("<", x + (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth() + gp.tileSize, y);
        }


    }

    public void drawPauseScreen(){

        String text = "PAUSED";
        int x = centerX(text);
        int y = gp.screenHeight / 2 - gp.tileSize * 2;

        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36));

        text = "Resume";
        x = centerX(text);
        y += gp.tileSize * 6;
        g2.drawString(text, x, y);
        if(cmd == 0) {
            g2.drawString(">", x - gp.tileSize, y);
            g2.drawString("<", x + (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth() + gp.tileSize, y);
        }

        text = "Exit";
        x = centerX(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(cmd == 1) {
            g2.drawString(">", x - gp.tileSize, y);
            g2.drawString("<", x + (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth() + gp.tileSize, y);
        }

         
        
    }

    public void drawTimer(){

        String sec = dForm.format(gp.TIME.seconds);
        String min = dForm.format(gp.TIME.minutes);

        String text = min + ":" + sec;

        g2.setColor(Color.WHITE);
        g2.setFont(secFont);

        g2.drawString(text, 20, 40);
    }

    public void drawGameoverScreen() {

        String text = "Game Over";
        int x = centerX(text);
        int y = gp.screenHeight / 2 - gp.tileSize * 2;

        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36));

        text = "Exit";
        x = centerX(text);
        y += gp.tileSize * 6;
        g2.drawString(text, x, y);
            g2.drawString(">", x - gp.tileSize, y);
            g2.drawString("<", x + (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth() + gp.tileSize, y);
    }
   
    public void drawKillCounter() {


        g2.setColor(Color.WHITE);
        g2.setFont(secFont);
        String text = Integer.toString(gp.player.kill);
        g2.drawString(text, gp.tileSize * (gp.maxScreenCol - 1), 20);
    }
   
   
    public int centerX(String text){
        
        int lenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - lenght / 2;
        return x;
    }



}
