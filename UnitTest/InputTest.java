import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.*;

public class InputTest {

    @Test
    public void testValidSTVInputs() throws IOException {
        Path tempFile = Files.createTempFile("valid_election", ".txt");
        Files.write(tempFile, List.of("STV", "2"));

        Input input = new Input(tempFile.toString());

        assertEquals(2, input.getNumSeats());
        assertEquals(1, input.getSTV_Plu());
//        assertTrue(input.isShuffle());
    }

    @Test
    public void testValidPVInputs() throws IOException {
        Path tempFile = Files.createTempFile("valid_election", ".txt");
        Files.write(tempFile, List.of("PV", "3"));

        Input input = new Input(tempFile.toString());

        assertEquals(3, input.getNumSeats());
        assertEquals(2, input.getSTV_Plu());
//        assertFalse(input.isShuffle());
    }

//    @Test
//    public void testValidMVInputs() throws IOException {
//        Path tempFile = Files.createTempFile("valid_election", ".txt");
//        Files.write(tempFile, List.of("MV", "3"));
//
//        Input input = new Input(tempFile.toString());
//
//        assertEquals(3, input.getNumSeats());
//        assertEquals(3, input.getSTV_Plu());
////        assertFalse(input.isShuffle());
//    }



}

