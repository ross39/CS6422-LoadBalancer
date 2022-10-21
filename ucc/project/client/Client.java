package ucc.project.client;

import java.io.BufferedReader;
import java.io.FileReader;

public class Client {

    public static void main(String[] args) {
       readFile();
    }

    public static void readFile() {
        // Will read a client request from a file

        try {
            // BufferedReader to read text from an input stream
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/java/org/example/client/file.txt"));

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
            System.out.println("Error");
        }
    }

    public void sendClientRequest(){
        // Sends the client request

    }
}
