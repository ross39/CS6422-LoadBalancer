package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class ClientTest {

    // Test for one line in a file
    @Test
    public void testReadFile() throws IOException {

        String expectedResult = "Tom";
        String actualResult;

        InputStream file = getClass().getResourceAsStream("clientrequest.txt");
        BufferedReader input = new BufferedReader(new InputStreamReader(file));
        actualResult = input.readLine();

        assertEquals(expectedResult, actualResult);

        input.close();
    }

    // Test for multiple lines in a file
    @Test
    public void testReadFileMultipleLines() throws IOException {    
        
        List<String> expectedResult = Arrays.asList("Tom", "Sam", "Tim", "May");
        
        // BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        InputStream file = getClass().getResourceAsStream("multipleclientrequests.txt");
        BufferedReader input = new BufferedReader(new InputStreamReader(file));

        List<String> actualResult = input.lines().collect(Collectors.toList());

        System.out.println(actualResult);
        
        assertEquals(expectedResult, actualResult);
        input.close();

    }
}
