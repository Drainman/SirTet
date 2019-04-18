package fr.polytech;

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
}
