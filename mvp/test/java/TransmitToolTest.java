package test.java;

import main.java.*;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static main.java.Client.PATH;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TransmitToolTest {

    TransmitTool transmitTool = new TransmitTool();

    Server server1;

    Server server2;

    List<String> clientinfo = new ArrayList<>();

    void prepareFileEnvironment(){

            // create server file

             server1 = new Server(1);

             server2 = new Server(3);

             LoadBalancer.getInstance().setServer_list(ServerPool.getServerPool().getPool());

             Client.readFile("main/java/resource/clientinfo.txt");

             clientinfo = Client.actualResult;

    }

    void prepareSocket(){

        List<Socket> socketList = new ArrayList<>();

        for (int i = 0 ; i < clientinfo.size() ; i++){

            Server next = LoadBalancer.getInstance().getNext();

            Socket socket = new Socket( clientinfo.get(i), next );

            socketList.add(socket);

        }

        transmitTool.setSockets(socketList);

    }

    @Test
    void testTransmission() {

        prepareFileEnvironment();

        prepareSocket();

        assertEquals(0, new File(server1.getIp()).length());

        assertEquals(0, new File(server2.getIp()).length());

        assertEquals(false, transmitTool.getSockets().isEmpty());

        transmitTool.sendToServer();

        assertEquals(true, transmitTool.getSockets().isEmpty());

        assertEquals(true, new File(server1.getIp()).length() > 0);

        assertEquals(true, new File(server2.getIp()).length() > 0);

    }

}
