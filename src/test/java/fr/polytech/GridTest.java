package fr.polytech;

import fr.polytech.back.Grid;
import fr.polytech.back.GridStatus;
import fr.polytech.back.Piece;
import fr.polytech.back.PieceMaker;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class GridTest {

    @Test
    public void should_return_array_10_per_24(){
        HashMap<String,Integer> map = new HashMap<>();
        map.put("height",24);
        map.put("width",10);

        assertThat(map).isEqualTo(Grid.getSize());
    }

    @Test
    public void should_return_gridStatus_piece_creation(){
        Game.run();
        assertThat(GridStatus.piece_creation).isEqualTo(Game.game_gride.gridStatus);
    }

    @Test
    public void should_return_gridStatus_active_piece(){
        Game.run(); //precedent tests check if return -> piece_cretation status
        Game.cycle(); //run one cycle
        assertThat(GridStatus.active_piece).isEqualTo(Game.game_gride.gridStatus);
    }

    @Test
    public void should_return_empty_line_after_filling_it(){
        Game.run(); //precedent tests check if return -> piece_cretation status

        Piece p = PieceMaker.pieceMap.get("I");

        int width = (int)Grid.getSize().get("width");
        int height = (int)Grid.getSize().get("height");

        for(int i = 0; i < width-4; ++i){
            Game.game_gride.grideMatrix[i][height-1] = 1;
        }

        Game.game_gride.create_piece(p);
        Game.game_gride.movePiece("right");
        Game.game_gride.movePiece("right");
        Game.game_gride.movePiece("right");

        for(int i = 0; i < height; ++i) Game.cycle(); // Downing the piece until it arrive at the bottom

        for(int i = 0; i < width; ++i) assertThat(Game.game_gride.grideMatrix[i][height-1]).isEqualTo(0);
    }

    @Test
    public void should_return_piece_move_down(){
        Game.run();
        Game.cycle(); // Create piece

        int width = (int)Grid.getSize().get("width");
        int height = (int)Grid.getSize().get("height");

        System.out.println(Game.game_gride);
        Game.game_gride.movePiece("down");
        System.out.println(Game.game_gride);

        for(int i = 0; i < width; ++i) assertThat(Game.game_gride.grideMatrix[i][0]).isEqualTo(0);
    }

    @Test
    public void should_return_rotated_piece(){
        Game.run();
        Piece p = PieceMaker.pieceMap.get("L");
        Game.game_gride.create_piece(p);

        Game.cycle(); Game.cycle();

        int current_rotation = Game.game_gride.getActivePieceRotation();

        Game.game_gride.rotateCurrentPiece(); // should do nothing because the piece exceed the grid

        assertThat(Game.game_gride.getActivePieceRotation()).isEqualTo(4);
    }

    @Test
    public void should_return_none_rotated_piece_if_rotate_in_corner(){
        Game.run();

        Piece p = PieceMaker.pieceMap.get("L");

        Game.game_gride.create_piece(p);
        Game.game_gride.movePiece("right");
        Game.game_gride.movePiece("right");
        Game.game_gride.movePiece("right");
        Game.cycle(); Game.cycle();

        int[][] grid = Game.game_gride.grideMatrix;

        System.out.println(Game.game_gride);
        Game.game_gride.rotateCurrentPiece();
        System.out.println(Game.game_gride);

        assertThat(grid).isEqualTo(Game.game_gride.grideMatrix);
    }

    @Test
    public void should_return_none_rotated_piece_if_rotate_exceeds_grid(){
        Game.run();
        Piece p = PieceMaker.pieceMap.get("I");
        Game.game_gride.create_piece(p);

        int[][] grid = Game.game_gride.grideMatrix;

        Game.game_gride.rotateCurrentPiece(); // should do nothing because the piece exceed the grid

        assertThat(grid).isEqualTo(Game.game_gride.grideMatrix);
    }
}
