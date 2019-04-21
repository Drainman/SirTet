package fr.polytech.back;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;

public class PieceMaker {

    private static ArrayList<Piece> pieceList = new ArrayList<Piece>();

    public PieceMaker(){
        try {
            loadAllPiece();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void loadAllPiece() throws IOException, ParseException {

       /* ArrayList<String> names = PieceReader.getFilesName();
        System.out.println(names);*/
       int countColor = 1;

        Piece piece = new Piece(PieceReader.load_data("C:\\Users\\Kurai\\WorkSpace\\Qualite\\SirTet\\src\\main\\resources\\fr\\polytech\\L.json"),"L",countColor++);
        pieceList.add(piece);
        piece = new Piece(PieceReader.load_data("C:\\Users\\Kurai\\WorkSpace\\Qualite\\SirTet\\src\\main\\resources\\fr\\polytech\\J.json"),"J",countColor++);
        pieceList.add((piece));
        piece = new Piece(PieceReader.load_data("C:\\Users\\Kurai\\WorkSpace\\Qualite\\SirTet\\src\\main\\resources\\fr\\polytech\\T.json"),"T",countColor++);
        pieceList.add((piece));
    }

    public static Piece getARandomPiece(){
        Random rand = new Random();
        int n = rand.nextInt(pieceList.size());
        return pieceList.get(n);
    }
}
