package fr.polytech;

import java.util.Arrays;
import java.util.HashMap;

public class Grid {

    private static final int width = 10;
    private static final int height = 24;
    private boolean [][] gride_state = new boolean[width][height] ;

    public Grid(){
        for(int i=0;i<width;i++)
            Arrays.fill(gride_state[i],false);
    }

    public static HashMap<String,Integer> getSize(){
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        map.put("height",height);
        map.put("width",width);
        return map;
    }

    public String toString(){
        String str = "";

        for(int i=0;i<height;i++){
            str += "[";
            for(int j=0;j<width;j++){
                str += gride_state[j][i] + " ";
            }
            str += "]\n";
        }

        return str;
    }
}
