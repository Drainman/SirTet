package fr.polytech;

import fr.polytech.back.Grid;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TetrisKeyListener implements KeyListener {

    private Grid grid;


    public TetrisKeyListener(Grid p_grid) {
        grid = p_grid;
    }


    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                System.out.println("TOUCHE LEFT");
                Game.game_gride.movePiece("left");
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("TOUCHE RIGHT");
                Game.game_gride.movePiece("right");
                break;
            case KeyEvent.VK_UP:
                System.out.println("TOUCHE UP");
                Game.game_gride.rotateCurrentPiece();
                break;
            case KeyEvent.VK_DOWN :
                System.out.println("TOUCHE DOWN");
                Game.game_gride.movePiece("down");
                break;
        }
        if(!Game.game_gride.isCurrentPieceFallen())
            Game.game_gride.check_if_delete_line();

        Game.panel.refresh_tab(Game.game_gride);


    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}

