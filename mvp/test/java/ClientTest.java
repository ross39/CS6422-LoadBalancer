package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import main.java.Client;

public class ClientTest {

     // Test for one line in a file
     @Test
     public void testReadFile() throws IOException {
 
         String expectedResult = "Tom";

         String actualResult;

         // root start from file current folder which is /test/java/
         // alternative path  /test/java/resource/multipleclientrequests.txt
         InputStream file = getClass().getResourceAsStream("resource/clientrequest.txt");

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

         // root start from file current folder which is /test/java/
         // alternative path  /test/java/resource/multipleclientrequests.txt
         InputStream file = getClass().getResourceAsStream("resource/multipleclientrequests.txt");

         BufferedReader input = new BufferedReader(new InputStreamReader(file));
 
         List<String> actualResult = input.lines().collect(Collectors.toList());
 
         System.out.println(actualResult);
         
         assertEquals(expectedResult, actualResult);

         input.close();
 
     }
 
     @Test
     void testClient(){

         List<String> expectedResult = Arrays.asList("Tom", "Sam", "Tim", "May");

         //root start from
         // if your path is incompatible with current environment
         // right click the file -> copy path
         // copy <the path from content root> of the file
         Client.readFile("resource/multipleclientrequests.txt");

         assertEquals(expectedResult, Client.actualResult);

     }
 }
 