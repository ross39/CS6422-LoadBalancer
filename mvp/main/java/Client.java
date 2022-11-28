package main.java;

import java.io.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class Client {

    public static List<String> actualResult;

    private static String PATH;

    public static void readFile(String filepath) {

        try {

            PATH = filepath;

            InputStream file = new FileInputStream(new File(filepath));

            BufferedReader input = new BufferedReader(new InputStreamReader(file));

            // for test
            actualResult = input.lines().collect(Collectors.toList());

            /* Closes input */
            input.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendClientRequest(){
        /* Sends the client request to the load balancer */
    }

    public static void clearFile() {

        File file = new File(PATH);

        file.delete();
    }

}