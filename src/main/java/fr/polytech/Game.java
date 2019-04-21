package fr.polytech;

import fr.polytech.back.Grid;
import fr.polytech.back.GridStatus;
import fr.polytech.back.Piece;
import fr.polytech.back.PieceMaker;
import fr.polytech.ihm.MainPannel;
import sun.applet.Main;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Game {

    public static boolean runGame;
    public static Grid game_gride;
    public static MainPannel panel;

    /**
     * Launch the game.
     * @return 0 if all is fine, other if error
     */
    public static int run(){

        Game.game_gride = new Grid();
        return 0;
    }

    /**
     * Run a cycle game
     */
    public static void cycle(){


        if(game_gride.gridStatus == GridStatus.piece_creation){
            System.out.println("[EVENT] ~ Ask for piece creation.\n");
            game_gride.random_create_piece();
        }

        else if(!game_gride.isCurrentPieceFallen() || game_gride.gridStatus == GridStatus.inactive_piece){
            System.out.println("[EVENT] ~ No active piece, check line to delete.\n");
            game_gride.check_if_delete_line();
            game_gride.random_create_piece();
        }

        else if(game_gride.gridStatus == GridStatus.active_piece){
            game_gride.movePiece("down");
            game_gride.check_if_delete_line();
        }
    }



    public static void main(String[] args) {
        int limit_cycle =  100000000;

        Game.run();
        Game.panel = new MainPannel(Game.game_gride);
        panel.setVisible(true);

        panel.addKeyListener(new TetrisKeyListener(game_gride));

        for(int i=0;i<limit_cycle;i++){
            Game.cycle();
            panel.refresh_tab(Game.game_gride);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
