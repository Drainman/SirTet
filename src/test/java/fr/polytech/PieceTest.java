package fr.polytech;

import fr.polytech.back.Piece;
import fr.polytech.back.PieceMaker;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PieceTest {
    @Test
    public void should_return_rotated_piece(){
        Piece p = new Piece();
        int[][] rotated = p.mapPiece.get("S2");
        p.setOrientation(2);
        assertThat(p.getCurrentMapPiece()).isEqualTo(rotated);
    }

    @Test
    public void should_return_I_piece(){
        Piece p = PieceMaker.pieceMap.get("I");
    }
}
