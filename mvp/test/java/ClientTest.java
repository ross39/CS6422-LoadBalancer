package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.junit.jupiter.api.Test;
import main.java.Client;

public class ClientTest {

    // Test for one line in a file
    @Test
    public void testReadFile() throws IOException {

        String test = "Tom";
        String request;

        InputStream file = getClass().getResourceAsStream("clientrequest.txt");
        BufferedReader input = new BufferedReader(new InputStreamReader(file));
        request = input.readLine();

        assertEquals(test, request);
        
        input.close();
    }

    // Test for multiple lines in a file
    @Test
    public void testReadFileMultipleLines() throws IOException {    
    
    }
}
