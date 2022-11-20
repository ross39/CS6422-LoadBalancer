package test.java;

import main.java.Socket;
import main.java.TransmitTool;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransmitToolTest {

    TransmitTool transmitTool = new TransmitTool();


    void prepareFileEnvironment(){

        // create server file
        for (int i = 1; i < 4; i++){

            File file = new File("Server" + i + ".txt");

            if (file.exists()){

                file.delete();

            }

            try {

                file.createNewFile();

                }
            catch (IOException exception) {

                exception.printStackTrace();

                }

        }

        // create client file
        for (int i = 1; i < 4; i++){

            File file = new File("Client" + i + ".txt");

            if (file.exists()){

                file.delete();

            }

        }



    }


    void prepareSocket(){

        Socket s1 = new Socket("client1.txt","Server1.txt","Tom");

        Socket s2 = new Socket("client2.txt","Server2.txt","Marry");

        Socket s3 = new Socket("client3.txt","Server3.txt","Jack");

        List<Socket> socketList = new ArrayList<>();

        socketList.add(s1);

        socketList.add(s2);

        socketList.add(s3);

        transmitTool.setSockets(socketList);
    }

    @Test
    void generateNewFile() {

        prepareFileEnvironment();

        prepareSocket();

        assertEquals(0, new File("Server1.txt").length());

        assertEquals(0, new File("Server2.txt").length());

        assertEquals(0, new File("Server3.txt").length());

        assertEquals(false, transmitTool.getSockets().isEmpty());

        transmitTool.sendToServer();

        assertEquals(true, transmitTool.getSockets().isEmpty());

        assertEquals(true, new File("Server1.txt").length() > 0);

        assertEquals(true, new File("Server2.txt").length() > 0);

        assertEquals(true, new File("Server3.txt").length() > 0);



    }

}
