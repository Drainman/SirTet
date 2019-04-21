package fr.polytech.back;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class PieceReader {

    private static final File folderPiece = new File("pieces/");

    public static HashMap<String,int[][]> load_data(String file_path) throws IOException, ParseException {

        HashMap<String,int[][]> pieceHashmap = new HashMap<String,int[][]>();
        String jsontxt = new String(Files.readAllBytes(Paths.get(file_path)));
        JSONObject obj = new JSONObject(jsontxt);



        JSONArray arr = obj.getJSONArray("matrix");
        String pieceName = obj.getString("name");
        int size = obj.getInt("size");


        for (int t = 0; t < size; t++) {

            int[][] a_template = new int[size][size];
            JSONArray amatrix = arr.getJSONArray(t);

            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    a_template[i][j] = amatrix.getJSONArray(i).getInt(j);


            pieceHashmap.put(pieceName + (t+1), a_template);
        }
        return pieceHashmap;
    }

    public static ArrayList<String> getFilesName(){

        ArrayList<String> arrayListFilesName = new ArrayList<>();

        for (final File fileEntry : folderPiece.listFiles()) {
            if (!fileEntry.isDirectory()) {
                arrayListFilesName.add(fileEntry.getName());
            }
        }

        return arrayListFilesName;

    }


}
