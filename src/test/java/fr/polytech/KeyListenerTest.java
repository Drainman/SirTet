package fr.polytech;

import fr.polytech.back.Grid;
import fr.polytech.ihm.MainPannel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.event.KeyEvent;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class KeyListenerTest {

    @Test
    public void should_return_same_grid_keyDown_and_oneCycle(){

        Game.run();
        Game.cycle();
        Grid saveGrid = Game.game_gride; // <- save the original grid

        //Make a keyevent down
        MainPannel mainPannel = new MainPannel(Game.game_gride);
        KeyEvent key = new KeyEvent(mainPannel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_DOWN,'Z');

        Grid compareGrid = Game.game_gride;

        //Reset grid
        Game.game_gride = saveGrid;
        //Make a cycle
        Game.cycle();

        assertThat(compareGrid).isEqualTo(Game.game_gride);

    }
}
