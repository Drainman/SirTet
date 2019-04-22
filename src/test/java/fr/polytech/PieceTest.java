package fr.polytech;

import fr.polytech.back.Piece;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PieceTest {
    @Test
    public void should_return_rotated_right_piece(){
        Piece p = new Piece();
        int[][] rotated = p.mapPiece.get("S2");
        p.setOrientation(2);
        assertThat(p.getCurrentMapPiece()).isEqualTo(rotated);
    }

    @Test
    public void should_return_rotated_left_piece(){
        Piece p = new Piece();
        int[][] rotated = p.mapPiece.get("S4");
        p.setOrientation(4);
        assertThat(p.getCurrentMapPiece()).isEqualTo(rotated);
    }

    @Test
    public void should_return_I_piece(){

    }
}