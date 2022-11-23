package main.java;

import java.io.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class Client {

    public static List<String> actualResult;

    private static String PATH = "multipleclientrequests.txt";

    public static void readFile(String filepath) {

        try {
            checkFileExist();

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

    public static void checkFileExist() throws IOException {

        File file = new File(Client.getPATH());

        if (!file.exists()){

            file.createNewFile();

            FileOutputStream fileOutputStream = new FileOutputStream(file);

            String data = "Tom\n" +
                    "Sam\n" +
                    "Tim\n" +
                    "May";

            fileOutputStream.write(data.getBytes());

            fileOutputStream.close();

        }else{

            file.delete();

            file.createNewFile();

            FileOutputStream fileOutputStream = new FileOutputStream(file);

            String data = "Tom\n" +
                    "Sam\n" +
                    "Tim\n" +
                    "May";

            fileOutputStream.write(data.getBytes());

            fileOutputStream.close();

        }
    }

    public static String getPATH() {
        return PATH;
    }

    public static void setPATH(String PATH) {
        Client.PATH = PATH;
    }

    public static void clearFile() {

        File file = new File(Client.getPATH());

        file.delete();
    }

    public static void main(String[] args) {

        readFile(PATH);

    }
}