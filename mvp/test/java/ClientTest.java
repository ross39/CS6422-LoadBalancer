package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.Client;

public class ClientTest {

    public static String CLIENTINFO_TXT = "clientinfo.txt";

    @BeforeEach
    void checkFileExist() throws IOException {

        File file = new File(CLIENTINFO_TXT);

        if (!file.exists()){

            file.createNewFile();

        }else{

            file.delete();

            file.createNewFile();

        }

        FileOutputStream fileOutputStream = new FileOutputStream(file);

        String data = "client1 tom\n" +
                "client2 marry\n" +
                "client3 jack\n";

        fileOutputStream.write(data.getBytes());

        fileOutputStream.close();
    }

    @AfterEach
    void cleanFile() {

        File file1 = new File(CLIENTINFO_TXT);

        file1.delete();

    }

    // Test for multiple lines in a file
     @Test
     public void testClient() throws IOException {
         
         List<String> expectedResult = Arrays.asList("client1 tom", "client2 marry", "client3 jack");

         Client.readFile(CLIENTINFO_TXT);

         System.out.println(Client.actualResult);
         
         assertEquals(expectedResult, Client.actualResult);

     }
 

 }
 