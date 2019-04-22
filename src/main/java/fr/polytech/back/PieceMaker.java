package fr.polytech.back;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class PieceMaker {

    private static ArrayList<Piece> pieceList = new ArrayList<Piece>();
    public static HashMap<String, Piece> pieceMap = new HashMap<String, Piece>();
    private static final File folderPiece = new File("./src/main/resources/fr/polytech/");

    public PieceMaker(){
        try { loadAllPiece();}
        catch (IOException | ParseException e) { e.printStackTrace(); }
    }

    /**
     * Load all piece and save them in an array
     * @throws IOException
     * @throws ParseException
     */
    private static void loadAllPiece() throws IOException, ParseException {

       //We get the piece name files
       ArrayList<String> names = PieceReader.getFilesName(folderPiece);

       //For each piece file
       for(int countColor=0;countColor<names.size();countColor++){

           //We get the matrix piece
           HashMap<String,int[][]> hashMap = PieceReader.load_data(folderPiece.getAbsolutePath()+"/"+names.get(countColor));
           //We get the key name piece
           String keyName = "";
           for ( String key : hashMap.keySet() ) {  keyName = key.substring(0,1); }

           //We create a new piece
           Piece piece = new Piece(hashMap,keyName,countColor+1);
           //And we add a sample in our list
           pieceList.add(piece);
           pieceMap.put(names.get(countColor).split("\\.")[0], piece);
       }
    }

    /**
     * Return a random piece according to the sample usable
     * @return
     */
    public static Piece getARandomPiece(){
        Random rand = new Random();
        int n = rand.nextInt(pieceList.size());
        return pieceList.get(n);
    }

}
