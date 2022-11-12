package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Test;

public class ClientTest {

    @Test
    public static void testReadFile() throws IOException {

        String test = "Tom";

        String request;
        // BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader input = new BufferedReader(new FileReader("mvp/test/java/clientrequests.txt"));
        while ((request = input.readLine()) != null) {
            System.out.println(request);
            input.close();
        }

        assertEquals(test, request);
    }
    
}
