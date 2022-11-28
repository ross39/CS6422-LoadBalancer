package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import main.java.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IntegrationTest {

  public static String CLIENTINFO_TXT = "clientinfo.txt";

  @BeforeEach
  void checkFileExist() throws IOException {

    cleanFile();

    File file = new File(CLIENTINFO_TXT);

    if (!file.exists()) {

      file.createNewFile();

    } else {

      file.delete();

      file.createNewFile();
    }

    FileOutputStream fileOutputStream = new FileOutputStream(file);

    String data =
        "client1 tom\n" + "client2 marry\n" + "client3 jack\n" + "client4 lily\n" + "client5 leo";

    fileOutputStream.write(data.getBytes());

    fileOutputStream.close();
  }

  @AfterEach
  void cleanFile() {

    String filePath = IpGenerator.getInstance().getFilePath();

    int i = 1;

    File file = new File(filePath + "server" + i + ".txt");

    while (file.exists()) {

      file.delete();

      i++;

      file = new File(filePath + "server" + i + ".txt");
    }

    File file1 = new File(CLIENTINFO_TXT);

    file1.delete();
  }

  static TransmitTool transmitTool = new TransmitTool();

  static Server server1;

  static Server server2;

  static List<String> clientinfo = new ArrayList<>();

  @Test
  void integrationTest() throws IOException {

    // servers start listening
    server1 = new Server(1);

    server2 = new Server(3);

    // loadbalancer get the server list from serverpool
    LoadBalancer.getInstance().setServer_list(ServerPool.getServerPool().getPool());

    // clients start sending requests
    Client.readFile(CLIENTINFO_TXT);

    clientinfo = Client.actualResult;

    List<Socket> socketList = new ArrayList<>();

    // loadbalancer choose a server for a request
    for (int i = 0; i < clientinfo.size(); i++) {

      Server next = LoadBalancer.getInstance().getNext();

      Socket socket = new Socket(clientinfo.get(i), next);

      socketList.add(socket);
    }

    // transmit tool get the socketlists
    transmitTool.setSockets(socketList);

    // transmit tool sent messages to servers according to sockets
    transmitTool.sendToServer();

    assertEquals(true, transmitTool.getSockets().isEmpty());

    assertEquals(true, new File(server1.getIp()).length() > 0);

    assertEquals(true, new File(server2.getIp()).length() > 0);
  }
}
