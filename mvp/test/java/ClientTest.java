package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Test;

public class ClientTest {

    // Test one line of code
    @Test
    public void testReadFile() throws IOException {

        String test = "Tom";
        String request;
        // BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String filename="test/java/clientrequests.txt";
        BufferedReader input = new BufferedReader(new FileReader(filename));
        request = input.readLine();
        assertEquals(test, request);
        input.close();
    }
    
}
