package main.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Client {

    public static void main(String[] args) {
       readFile();
    }

    public static void readFile() {

        try {
            // BufferedReader to read text from an input stream
           BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

           // While loop to read each line of the file. request - each line is a different client request
           String request;
           while ((request = input.readLine()) != null) {
            System.out.println("Client request: " + request);
           }

           // Closes input
           input.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendClientRequest(){
        // Sends the client request

    }
}