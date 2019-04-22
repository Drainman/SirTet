package fr.polytech;

import fr.polytech.back.Grid;
import fr.polytech.back.GridStatus;
import fr.polytech.ihm.MainPannel;

public class Game {

    /* PUBLIC ELEMENTS */
    public static Grid game_gride;      //game use for the game
    public static MainPannel panel;     //ihm use for the game
    public static int deletedLine;      //counter of deleted line

    /* PRIVATE ELEMENTS */
    private static int beginDelay = 500;    //starting delay between two cycles
    private static int minDelay = 100;      //minimum delay between two cycles

    /**
     * Launch the game.
     * @return 0 if all is fine, other if error
     */
    public static int run(){

        Game.game_gride = new Grid();
        return 0;
    }

    /**
     * Run a cycle for the game
     * @return true if the game can continue, else false
     */
    public static boolean cycle(){

        //We have to create a piece
        if(game_gride.gridStatus == GridStatus.piece_creation && !isGameOver()){
            System.out.println("[EVENT] ~ Ask for piece creation.\n");
            game_gride.random_create_piece();
        }

        //We don't have any active piece
        else if(game_gride.gridStatus == GridStatus.inactive_piece){
            System.out.println("[EVENT] ~ No active piece, check line to delete.\n");
            deletedLine += game_gride.check_if_delete_line();

            //Check if it's game over
            if(!isGameOver()) game_gride.random_create_piece();
            else return  false;

        }

        //There isn't piece currently fallen
        else if(!game_gride.isCurrentPieceFallen()){
            System.out.println("[EVENT] ~ No fallen piece, check line to delete.\n");
            deletedLine += game_gride.check_if_delete_line();
            game_gride.gridStatus = GridStatus.piece_creation;

            if(!isGameOver()) game_gride.random_create_piece();
            else return  false;
        }

        //We have a fall piece
        else if(game_gride.gridStatus == GridStatus.active_piece){
            game_gride.movePiece("down");
        }

        else System.out.println("[WARNING] ~ An empty cycle has been detected."); //TEST

        //System.out.println(game_gride);
        return true;
    }


    public static boolean isGameOver(){return game_gride.isLastLineFill();}


    /**
     * Return a delay for the next cycle.
     * More there are lines deleted, more the delay is reduced.
     * @return delay between two cycles
     */
    public static int waitValue(){
        if(beginDelay-(deletedLine*2)<=minDelay) return minDelay;
        else return beginDelay - (deletedLine*2);
    }

    /**
     * Main of the project
     * @param args
     */
    public static void main(String[] args) {

        //INIT GAME
        Game.deletedLine = 0;
        Game.run();
        //INIT IHM
        Game.panel = new MainPannel(Game.game_gride);
        panel.setVisible(true);
        //ADD EVENT LISTENER
        panel.addKeyListener(new TetrisKeyListener(game_gride));

        //LOOP while isn't a game over
        while(cycle()){
            panel.refresh_tab(Game.game_gride);
            try {Thread.sleep(waitValue());}
            catch (InterruptedException e) { e.printStackTrace(); }
        }

        //GAME OVER
        System.out.println("GAME OVER !");
        System.out.println("You have deleted : "+deletedLine+" line(s).");
    }
}
