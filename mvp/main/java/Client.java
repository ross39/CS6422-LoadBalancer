package main.java;

import java.io.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class Client {

    public static List<String> actualResult;
    public static final String PATH = "main/java/resource/multipleclientrequests.txt";

    public static void readFile(String filepath) {

        try {

            InputStream file = new FileInputStream(new File(filepath));

            BufferedReader input = new BufferedReader(new InputStreamReader(file));

            // for test
            actualResult = input.lines().collect(Collectors.toList());

            /* Closes input */
            input.close();

//            alternative
//            InputStream file2 = new FileInputStream(new File(filepath));
//
//            BufferedReader input2 = new BufferedReader(new InputStreamReader(file2));
//
//           /* While loop to read each line of the file. request - each line is a different client request */
//           while ((request = input2.readLine()) != null) {

//            System.out.println("Client request: " + request);

//           }
//
//           input2.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendClientRequest(){
        /* Sends the client request to the load balancer */
    }

    public static void main(String[] args) {

        readFile(PATH);

    }
}