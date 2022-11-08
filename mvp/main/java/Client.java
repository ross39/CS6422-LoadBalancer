package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Scanner;


public class Client {

    public static void main(String[] args) {
       readFile();
    }

    public static void readFile() {

        try {
            // BufferedReader to read text from an input stream
            BufferedReader bufferedReader = new BufferedReader(new FileReader("CS6422-LoadBalancer/mvp/clientrequests.txt"));

            // Prints a single line
            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }

            // Closes the bufferedReader
             bufferedReader.close();
        }
        catch (Exception e) {

        }
    }

    public void sendClientRequest(){
        // Sends the client request

    }
}
