package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import main.java.Client;

public class ClientTest {



     // Test for one line in a file
     @Test
     public void testReadFile() throws IOException {

         String actualResult;

         Client.checkFileExist();

         FileInputStream inputStream = new FileInputStream(new File(Client.getPATH()));

         BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));

         actualResult = input.readLine();
 
         assertEquals("Tom", actualResult);
 
         input.close();

         Client.clearFile();

     }




    // Test for multiple lines in a file
     @Test
     public void testReadFileMultipleLines() throws IOException {    
         
         List<String> expectedResult = Arrays.asList("Tom", "Sam", "Tim", "May");

         Client client = new Client();

         client.checkFileExist();

         FileInputStream inputStream = new FileInputStream(new File(Client.getPATH()));

         BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
 
         List<String> actualResult = input.lines().collect(Collectors.toList());
 
         System.out.println(actualResult);
         
         assertEquals(expectedResult, actualResult);

         input.close();

         client.clearFile();
 
     }
 
     @Test
     void testClient(){

         List<String> expectedResult = Arrays.asList("Tom", "Sam", "Tim", "May");

         Client.readFile(Client.getPATH());

         assertEquals(expectedResult, Client.actualResult);

         Client.clearFile();

     }
 }
 