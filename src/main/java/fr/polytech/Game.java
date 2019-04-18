package fr.polytech;

public class Game {

    private static boolean runGame;
    private static boolean activePiece;
    private static Grid game_gride;

    /**
     * Launch the game.
     * @return 0 if all is fine, other if error
     */
    private static int run(){

        Game.game_gride = new Grid();
        return 0;
    }
    /*

    TESTS --> Indépendants de la gille en tant que telle;
     */

    public static void main(String[] args) {
        Game.run();
    }
}
