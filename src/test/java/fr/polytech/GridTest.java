package fr.polytech;

import fr.polytech.back.Grid;
import fr.polytech.back.GridStatus;
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
    public void should_return_gridStatus_inactive_piece(){

    }


}
