package fr.polytech.back;

import java.util.HashMap;

public class Piece {

    public HashMap<String,int[][]> mapPiece = new HashMap<>();
    public String kind;
    private int orientation;

    public int color;
    public int x,y;
    public int size;


    /**
     * Default constructor.
     * Warning : Will create only a S piece
     */
    public Piece(){
        mapPiece.put("S1",new int[][]{
                {0, 0, 0, 0},
                {0, 0, 2, 1},
                {0, 1, 1, 0},
                {0, 0, 0, 0}});
        mapPiece.put("S2",new int[][]{
                {0, 0, 1, 0},
                {0, 0, 2, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 0}});
        mapPiece.put("S3",new int[][]{
                {0, 0, 0, 0},
                {0, 0, 2, 1},
                {0, 1, 1, 0},
                {0, 0, 0, 0}});
        mapPiece.put("S4",new int[][]{
                {0, 0, 1, 0},
                {0, 0, 2, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 0}});

        orientation = 1;
        color = 1;
        kind  = "S";
        size=4;
    }

    /**
     * Prefered constructor
     * @param pHashmap The matrix of the piece and all these orientations
     * @param keyName  Key Name of the piece
     * @param p_color Color value for the piece
     */
    public Piece(HashMap<String,int[][]> pHashmap,String keyName,int p_color){
        mapPiece = pHashmap;
        orientation =1;
        color = p_color;
        kind = keyName;
        size = pHashmap.size();
    }

    /**
     * Copy constructor
     * @param aRandomPiece Piece to copy
     */
    public Piece(Piece aRandomPiece) {
        mapPiece = aRandomPiece.mapPiece;
        orientation=1;
        color=aRandomPiece.color;
        kind = aRandomPiece.kind;
        size = aRandomPiece.size;
    }

    /**
     * Display the piece (orientation 1 by default)
     * @return String presentation of the piece
     */
    public String toString(){
        int[][] extract =  mapPiece.get(kind+orientation);
        String str = kind+orientation +" -> " + color + "\n- - - - - - - - - - \n";

        for (int i=0;i<size;i++) {
            str += "[";
            for(int j=0;j<size;j++){
                str+= extract[i][j]+ " ";
            }
            str+="]\n";
        }
        return str+"- - - - - - - - -";
    }

    /**
     * Change the orientation of the piece
     * @param p_o orientation to set
     */
    public void setOrientation(int p_o){
        orientation = p_o;
    }

    /**
     * Give the orientation of the piece
     * @return orientation of the piece
     */
    public int getOrientation(){
        return orientation;
    }


}
