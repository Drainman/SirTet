package fr.polytech.back;

import java.util.Arrays;
import java.util.HashMap;

public class Grid {

    //PRIVATE ELEMENTS

    /* GRID PARAMETERS */
    private static final int width = 10;        //WIDTH GRID
    private static final int height = 24;       //HEIGHT GRID
    private static final int origineX = 0;      //Spawn x for piece
    private static final int origineY = 5;      //Spawn y for piece
    /* PIECE PARAMETERS (MOVE ?) */
    private static final int pivotX = 1;
    private static final int pivotY = 2;        //Define the rotation pivot for the piece
    private static final int maxSizePiece = 4;  //Define the mamximum size (height & width) for a piece
    /* OTHER PRIVATE VARS */
    private boolean flag;                       //Use to control movable/rotable piece
    private Piece activePiece;                  //Current piece;
    private PieceMaker pieceMaker;

    //PUBLIC ELEMENTS
    public GridStatus gridStatus;               //Status of the grid
    public int [][] grideMatrix = new int[width][height] ; //Matrix of the grid


    /**
     * Constructor
     */
    public Grid(){
        //Wait piece creation
        gridStatus = GridStatus.piece_creation;

        //We add the piece maker
        pieceMaker = new PieceMaker();

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
     * @param i position of the piece (x)
     * @param j position of the piece (y)
     * @param px pivot for the piece (x)
     * @param py pivot for the piece (y)
     * @param value color of the piece
     */
    private void floodFill(int i,int j,int px,int py,int value){

        //We define a new array, same size as a piece
        boolean[][] visited = new boolean[maxSizePiece][maxSizePiece];
        //Fill with false
        for(int m=0;m<maxSizePiece;m++)
            Arrays.fill(visited[m],false);

        flood(i, j, px, py, value, visited);
    }

    /**
     * Fill the environment of the piece (recursive)
     * @param i position of the piece (x)
     * @param j position of the piece (y)
     * @param px pivot for the piece (x)
     * @param py pivot for the piece (y)
     * @param value color of the piece
     * @param visited matrix of element visited
     */
    private void flood(int i,int j,int px,int py,int value,boolean visited[][]){

        //We do nothing
        if( px < 0 || px >= maxSizePiece
         || py < 0 || py >= maxSizePiece
         || i < 0
         || visited[px][py]
         || activePiece.mapPiece.get(activePiece.kind+activePiece.getOrientation())[px][py] == 0)
                return;

        visited[px][py] = true; //We notice that we have visited the element

        grideMatrix[j][i] = value; //We fill our matrix with the color of our piece

        //We do the same action all around the piece
        flood(i, j - 1, px, py - 1, value, visited);
        flood(i + 1, j, px + 1, py, value, visited);
        flood(i, j + 1, px, py + 1, value, visited);
        flood(i - 1, j, px - 1, py ,value, visited);
    }

    /**
     * Check if we can fill the environment of the piece (recursive)
     * @param i position of the piece (x)
     * @param j position of the piece (y)
     * @param px pivot for the piece (x)
     * @param py pivot for the piece (y)
     * @param visited matrix of element visited
     */
    private void flood(int i, int j, int px, int py, boolean visited[][]) //flag
    {
        if(px < 0 || px >= maxSizePiece
         || py < 0 || py >= maxSizePiece
         || visited[px][py]
         || activePiece.mapPiece.get(activePiece.kind+activePiece.getOrientation())[px][py] == 0)
            return;


        visited[px][py] = true; //notice we visited this case

        // If this is outside the limits or the case isn't empty
        if(i < 0 || i >= height|| j < 0 || j >= width || grideMatrix[j][i] != 0)
        {
            flag = false; //Flag (OK) become false
            return;
        }

        //We do the same action all around the piece
        flood(i, j - 1, px, py - 1, visited);
        flood(i + 1, j, px + 1, py, visited);
        flood(i, j + 1, px, py + 1, visited);
        flood(i - 1, j, px - 1, py, visited);
    }

    /**
     * Draw the actual piece in the grid
     * @use flooFill()
     * @return active piece of the grid
     */
    public Piece draw_piece(){
        floodFill(activePiece.x, activePiece.y, pivotX, pivotY,activePiece.color);
        return activePiece;
    }

    /**
     * Create a piece.
     * Warning : this function call a unique piece hardcoded.
     * Do not use it anymore except for test or issue for an error.
     * @return active piece of the grid
     */
    public Piece create_piece(){
        activePiece = new Piece();
        activePiece.x = origineX;
        activePiece.y = origineY;

        draw_piece();
        gridStatus = GridStatus.active_piece;
        return activePiece;
    }

    /**
     * Create a random piece.
     * @return active piece of the grid
     */
    public Piece random_create_piece(){

        activePiece = new Piece(PieceMaker.getARandomPiece());
        System.out.println(activePiece);

        activePiece.x = origineX;
        activePiece.y = origineY;

        draw_piece();
        gridStatus = GridStatus.active_piece;
        return activePiece;
    }


    /**
     * Clear the piece (erase from the grid)
     * @use floodFill()
     */
    private void clearPiece(){
        floodFill(activePiece.x, activePiece.y, pivotX, pivotY,0);
    }

    /**
     * Check if the piece is movable
     * @param x position to check x
     * @param y position to check y
     * @return true if piece is movable, else false
     */
    private boolean checkIfMovable(int x,int y){

        clearPiece(); //We erase the piece

        flag = true; //Initialize the flag

        //We declare & fill the visited array
        boolean visited[][] = new boolean[maxSizePiece][maxSizePiece];
        for(int m=0;m<maxSizePiece;m++)
            Arrays.fill(visited[m],false);


        flood(x, y, pivotX, pivotY, visited);   //We launch the flood
        draw_piece();                           //We draw again the piece
        return flag;
    }

    /**
     * Move the piece in a specific direction
     * @param direction down, left or right. Default -> error ?
     */
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

    /**
     * Check if the piece is rotable
     * @return true if yes, else false
     */
   private boolean isCurrentPieceRotable()
    {
        clearPiece(); //We erase the piece

        flag = true; //Initialize the flag

        //We declare & fill the visited array
        boolean visited[][] = new boolean[maxSizePiece][maxSizePiece];
        for(int m=0;m<maxSizePiece;m++)
            Arrays.fill(visited[m],false);

        flood(activePiece.x, activePiece.y, pivotX, pivotX, visited);   //We launch the flood
        draw_piece();                                                   //We draw again the piece
        return flag;
    }

    /**
     * Make a piece rotation (left)
     */
    public void rotateCurrentPiece()
    {
        //Current rotation
        int o = activePiece.getOrientation();

        //Change rotation
        //if it isn't the last element
        if(o > 1) o--;
        //else
        else o = activePiece.mapPiece.size() ;

        //Check if we can rotate the piece
        if(isCurrentPieceRotable())
        {
            clearPiece(); // We erase the piece
            activePiece.setOrientation(o); // We change the rotation
            draw_piece(); // We draw the piece with this new parameter
        }
    }

    /**
     * Delete a line
     * @param y the index of the line to delete
     */
    public void deleteLine(int y)
    {
        clearPiece(); //We erase the current piece
        gridStatus = GridStatus.inactive_piece;

        //We move all the line to delete the last one
        for(int j = y; j > 0; --j)
        {
            for(int i = 0; i < width; ++i)
                grideMatrix[i][j] = grideMatrix[i][j-1];
        }

        draw_piece(); //We draw the piece again
    }

    /**
     * Check if we can delete some lines
     * @return count of the deleted lines
     */
    public int check_if_delete_line()
    {
        int nbLinesDeleted = 0;

        for(int j = 0; j < height; ++j)
        {
            int i = 0;
            //Check if the case isn't empty
            for(; i < width && grideMatrix[i][j] != 0; ++i);

            if(i == width)//We need to delete this line
            {
                nbLinesDeleted++;
                deleteLine(j);
            }
        }

        return nbLinesDeleted;
    }


    /**
     * Check if the current piece fall
     * @return
     */
    public boolean isCurrentPieceFallen()
    {
        //If we can move the piece down
        if(checkIfMovable(activePiece.x + 1, activePiece.y))
            return true;

        //Implicit else
        check_if_delete_line(); //we check if we have to delete some lines
        return false;
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
