package fr.polytech.back;

import java.util.HashMap;

public class Piece {

    public HashMap<String,int[][]> mapPiece = new HashMap<>();
    public String kind;
    private int orientation;

    public int color;
    public int x,y;


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
    }

    public Piece(HashMap<String,int[][]> pHashmap,String keyName,int p_color){
        mapPiece = pHashmap;
        orientation =1;
        color = p_color;
        kind = keyName;
    }

    public Piece(Piece aRandomPiece) {
        mapPiece = aRandomPiece.mapPiece;
        orientation=1;
        color=aRandomPiece.color;
        kind = aRandomPiece.kind;
    }

    public String toString(){
        int[][] extract =  mapPiece.get(kind+orientation);
        String str = kind+orientation +" -> " + color + "\n- - - - - - - - - - \n";

        for (int i=0;i<4;i++) {
            str += "[";
            for(int j=0;j<4;j++){
                str+= extract[i][j]+ " ";
            }
            str+="]\n";
        }
        return str+"- - - - - - - - -";
    }

    public void setOrientation(int p_o){
        orientation = p_o;
    }

    public int getOrientation(){
        return orientation;
    }


}
