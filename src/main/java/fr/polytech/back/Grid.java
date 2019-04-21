package fr.polytech.back;

import java.util.Arrays;
import java.util.HashMap;

public class Grid {

    //PRIVATE ELEMENTS

    /* GRID PARAMETERS */
    private static final int width = 10;
    private static final int height = 24;
    private static final int origineX = 0;
    private static final int origineY = 5;
    /* PIECE PARAMETERS (MOVE) */
    private static final int pivotX = 1;
    private static final int pivotY = 2;
    private static final int maxSizePiece = 4;
    /* OTHER PRIVATE VARS */
    private boolean flag; //Use to control movable piece
    private Piece activePiece; //Current piece
    private PieceMaker pieceMaker;

    //PUBLIC ELEMENTS
    public GridStatus gridStatus;
    public int [][] grideMatrix = new int[width][height] ; //Matrix of the grid


    /**
     * Constructor
     */
    public Grid(){
        pieceMaker = new PieceMaker();
        //Wait piece creation
        gridStatus = GridStatus.piece_creation;

        //Put the matrix empty
        for(int i=0;i<width;i++)
            Arrays.fill(grideMatrix[i],0);
    }

    /**
     * Get the size of the grid
     * @return Hashmap ~ Key height & width
     */
    public static HashMap<String,Integer> getSize(){
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        map.put("height",height);
        map.put("width",width);
        return map;
    }

    /**
     * Visualize the grid
     * @return String to visualize
     */
    public String toString(){
        String str = "";

        for(int i=0;i<height;i++){
            str += "[";
            for(int j=0;j<width;j++){ str += grideMatrix[j][i] + " "; }
            str += "]\n";
        }

        return str;
    }

    /**
     * Launch flood function
     * @use flood()
     * @param i
     * @param j
     * @param px
     * @param py
     * @param value
     */
    public void floodFill(int i,int j,int px,int py,int value){
        boolean[][] visited = new boolean[maxSizePiece][maxSizePiece];

        for(int m=0;m<maxSizePiece;m++)
            Arrays.fill(visited[m],false);

        flood(i, j, px, py, value, visited);
    }

    public void flood(int i,int j,int px,int py,int value,boolean visited[][]){

        if( px < 0 || px >= maxSizePiece
         || py < 0 || py >= maxSizePiece
         || i < 0
         || visited[px][py]
         || activePiece.mapPiece.get(activePiece.kind+activePiece.getOrientation())[px][py] == 0)
                return;

        visited[px][py] = true;

        grideMatrix[j][i] = value; // On remplit la case de la valeur dans l'aire

        flood(i, j - 1, px, py - 1, value, visited);
        flood(i + 1, j, px + 1, py, value, visited);
        flood(i, j + 1, px, py + 1, value, visited);
        flood(i - 1, j, px - 1, py ,value, visited);
    }

    public void flood(int i, int j, int px, int py, boolean visited[][]) //flag
    {
        if(px < 0 || px >= maxSizePiece
         || py < 0 || py >= maxSizePiece
         || visited[px][py]
         || activePiece.mapPiece.get(activePiece.kind+activePiece.getOrientation())[px][py] == 0)
            return;

        visited[px][py] = true;

        /* Si on dépasse les limites de l'aire de jeu
         * ou si la case sur laquelle on est n'est pas vide
         */
        if(i < 0 || i >= height|| j < 0 || j >= width || grideMatrix[j][i] != 0)
        {
            flag = false; // on met flag à false
            return;
        }

        flood(i, j - 1, px, py - 1, visited);
        flood(i + 1, j, px + 1, py, visited);
        flood(i, j + 1, px, py + 1, visited);
        flood(i - 1, j, px - 1, py, visited);
    }

    public Piece draw_piece(){
        floodFill(activePiece.x, activePiece.y, pivotX, pivotY,activePiece.color);
        return activePiece;
    }

    public Piece create_piece(){
        activePiece = new Piece();
        activePiece.x = origineX;
        activePiece.y = origineY;

        draw_piece();
        gridStatus = GridStatus.active_piece;
        return activePiece;
    }

    public Piece random_create_piece(){

        activePiece = new Piece(PieceMaker.getARandomPiece());
        System.out.println(activePiece);

        activePiece.x = origineX;
        activePiece.y = origineY;

        draw_piece();
        gridStatus = GridStatus.active_piece;
        return activePiece;
    }


    public void clearPiece(){
        floodFill(activePiece.x, activePiece.y, pivotX, pivotY,0);
    }

    public boolean checkIfMovable(int x,int y){
        clearPiece();
        flag = true; // On suppose au départ qu'on peut bouger la pièce

        /* On déclare et initialise le tableau visited pour le flood fill */
        boolean visited[][] = new boolean[maxSizePiece][maxSizePiece];

        for(int m=0;m<maxSizePiece;m++)
            Arrays.fill(visited[m],false);

        /* On fait notre flood fill */
        flood(x, y, pivotX, pivotY, visited);
        draw_piece(); // On redessine notre pièce
        return flag; // On renvoie le résultat
    }

    /* Déplace la pièce d'une case vers le bas */
    public void movePiece(String direction){
        switch (direction){
            case "down" :
                if(checkIfMovable(activePiece.x+1,activePiece.y)){
                    clearPiece();
                    activePiece.x++;
                    draw_piece();
                }
                break;

            case "left" :
                if(checkIfMovable(activePiece.x,activePiece.y-1)){
                    clearPiece();
                    activePiece.y--;
                    draw_piece();
                }
                break;

            case "right" :
                if(checkIfMovable(activePiece.x,activePiece.y+1)){
                    clearPiece();
                    activePiece.y++;
                    draw_piece();
                }
                break;
        }
    }

    /* Peut on affecter l'orientation o à la pièce courante ? */
   private boolean isCurrentPieceRotable()
    {
        clearPiece();

        flag = true;

        boolean visited[][] = new boolean[maxSizePiece][maxSizePiece];

        for(int m=0;m<maxSizePiece;m++)
            Arrays.fill(visited[m],false);

        flood(activePiece.x, activePiece.y, pivotX, pivotX, visited);

        draw_piece();

        return flag;
    }

    public void rotateCurrentPieceLeft()
    {
        int o = activePiece.getOrientation(); // On récupère l'orientation courante

        if(o > 1) // Si on n'est pas sur la 1ère orientation
            o--; // On peut décrémenter o
        else // Si non
            o = activePiece.mapPiece.size() ; // On passe à la dernière orientation

        if(isCurrentPieceRotable()) // Si on peut tourner la pièce
        {
            clearPiece(); // On efface la pièce courante

            activePiece.setOrientation(o); // On change son orientation
            draw_piece(); // On la redessine avec la nouvelle orientation
        }
    }

    public void deleteLine(int y)
    {
        clearPiece(); // On efface d'abord la pièce courante
        gridStatus = GridStatus.inactive_piece;

        /* On déplace toutes les lignes à partir de y vers le haut
         * d'une case vers le bas
         */
        for(int j = y; j > 0; --j)
        {
            for(int i = 0; i < width; ++i)
                grideMatrix[i][j] = grideMatrix[i][j-1];
        }

        draw_piece(); // On la redessine
    }

    /* Renvoie le nombre de lignes supprimées */
    public int check_if_delete_line()
    {
        int nbLinesDeleted = 0;

        for(int j = 0; j < height; ++j)
        {
            int i = 0;

            for(; i < width && grideMatrix[i][j] != 0; ++i);

            if(i == width) // On a trouvé une ligne pleine
            {
                nbLinesDeleted++; // On incrémente le nombre de lignes supprimées
                deleteLine(j); // On supprime la ligne
            }
        }

        return nbLinesDeleted;
    }



    /* Teste si la pièce courante est tombée donc ne peut plus bouger */
    public boolean isCurrentPieceFallen()
    {
        if(checkIfMovable(activePiece.x + 1, activePiece.y)) // Si on peut encore la bouger vers le bas
            return true; // on renvoie faux

        check_if_delete_line();
        return false; // si non on renvoie vrai
    }

    public int getActivePieceColor(){
        return activePiece.color;
    }

    /*
    bool Board::isGameOver()
    {
        for(int i = 0; i < BOARD_WIDTH; ++i)
        {
            if(area[i][0] != FREE) // Si il y a un bloc sur la première ligne de l'aire
                return true; // C'est que la partie est finie
        }

        return false;
    }
    */


}
