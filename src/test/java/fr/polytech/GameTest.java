package fr.polytech;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class GameTest {

    @Test
    public void should_return_inferior_delay(){
        int delaymax = Game.getBeginDelay();
        Game.deletedLine++;
        assertThat(delaymax).isGreaterThan(Game.waitValue());
    }
}
