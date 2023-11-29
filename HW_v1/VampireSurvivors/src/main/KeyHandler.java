package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entity.Fireball;

public class KeyHandler implements KeyListener{

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    GamePanel gp;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode();

        if(gp.gameState == gp.mainState){


            if(code == KeyEvent.VK_W){
                gp.ui.cmd--;
                if(gp.ui.cmd < 0){
                    gp.ui.cmd = gp.ui.maxCmd;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.ui.cmd++;
                if(gp.ui.cmd > gp.ui.maxCmd){
                    gp.ui.cmd = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){

                if(gp.ui.cmd == 0){
                    gp.gameState = gp.playState;
                    gp.stopMusic();
                    gp.playMusic(2);
                }
                if(gp.ui.cmd == 1){
                    System.exit(0);
                }
            }  
        }

        if(gp.gameState == gp.pauseState){

            if(code == KeyEvent.VK_W){
                gp.ui.cmd--;
                if(gp.ui.cmd < 0){
                    gp.ui.cmd = gp.ui.maxCmd;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.ui.cmd++;
                if(gp.ui.cmd > gp.ui.maxCmd){
                    gp.ui.cmd = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){

                if(gp.ui.cmd == 0){
                    gp.gameState = gp.playState;
                    gp.stopMusic();
                    gp.playMusic(2);
                }
                if(gp.ui.cmd == 1){
                    gp.gameState = gp.mainState;
                }
            }  
        }
        
        if(gp.gameState == gp.playState) {

            if(code == KeyEvent.VK_W) {
            upPressed = true;
            }
            if(code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if(code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if(code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if(code == KeyEvent.VK_ESCAPE){
                if(gp.gameState == gp.playState){
                    gp.gameState = gp.pauseState;
                    gp.stopMusic();
                } 
                else if(gp.gameState == gp.pauseState) {
                    gp.gameState = gp.playState;
                    gp.playMusic(2);
                }
            }
            if(code == KeyEvent.VK_SPACE){
                gp.projectile.add(new Fireball(gp));
            }
        }
        
        if(gp.gameState == gp.gameoverState){

            gp.gameState = gp.mainState;
            gp.playMusic(1);
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
    
}
