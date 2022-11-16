package main.java;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class Client {
    
    private static String request;

    public static List<String> actualResult;

    public static void main() {
        String path = "main/java/resource/multipleclientrequests.txt";
        readFile(path);
    }

    public static void readFile(String filepath) {

        try {

            InputStream file = new FileInputStream(new File(filepath));

            BufferedReader input = new BufferedReader(new InputStreamReader(file));

            // for test
            actualResult = input.lines().collect(Collectors.toList());

           /* While loop to read each line of the file. request - each line is a different client request */
           while ((request = input.readLine()) != null) {
            System.out.println("Client request: " + request);
           }


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
}
