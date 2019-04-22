package fr.polytech;

import fr.polytech.back.PieceReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.text.ParseException;
import java.util.HashMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PieceReaderTest {

    @Test
    public void should_throw_error_NullPointerException_cause_not_a_correct_path(){
        assertThrows(NullPointerException.class, ()-> { PieceReader.getFilesName(new File("bidule"));});
    }

    @Test
    public void should_return_throw_error_FileNotExist() {
        assertThrows(NoSuchFileException.class, ()-> {PieceReader.load_data("bidule");});
    }

    @Test
    public void should_return_hashMap_L() throws IOException, ParseException {
        HashMap<String,int [][]> hashMap = PieceReader.load_data("./src/main/resources/fr/polytech/L.json");
        int[][] L1 = { {0, 0, 0, 0},{0, 1, 2, 1},{0, 1, 0, 0},{0, 0, 0, 0} };
        assertThat(L1).isEqualTo(hashMap.get("L1"));
    }

    @Test
    public void should_throw_error_invalidJSON() {
        assertThrows(org.json.JSONException.class, () -> {PieceReader.load_data("./src/test/resources/TEST.json");});
    }

}
