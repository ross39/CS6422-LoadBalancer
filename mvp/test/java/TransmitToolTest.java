package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import main.java.*;
import org.junit.jupiter.api.Test;

class TransmitToolTest {

  // Change it by yourself if incompatible
  public static String CLIENTINFO_TXT = "clientinfo.txt";

  TransmitTool transmitTool = new TransmitTool();

  Server server1;

  Server server2;

  List<String> clientinfo = new ArrayList<>();

  @Test
  void testTransmission() throws IOException {

    cleanFile();

    prepareFileEnvironment();

    prepareSocket();

    assertEquals(true, (new File(server1.getIp()).length()) == 0);

    assertEquals(true, (new File(server2.getIp()).length()) == 0);

    assertEquals(false, transmitTool.getSockets().isEmpty());

    transmitTool.sendToServer();

    assertEquals(true, transmitTool.getSockets().isEmpty());

    assertEquals(true, new File(server1.getIp()).length() > 0);

    assertEquals(true, new File(server2.getIp()).length() > 0);

    cleanFile();
  }

  void prepareFileEnvironment() throws IOException {

    // create server file

    server1 = new Server(3);

    server2 = new Server(3);

    LoadBalancer.getInstance().setServer_list(ServerPool.getServerPool().getPool());

    checkFileExist();

    Client.readFile(CLIENTINFO_TXT);

    clientinfo = Client.actualResult;
  }

  void prepareSocket() {

    List<Socket> socketList = new ArrayList<>();

    for (int i = 0; i < clientinfo.size(); i++) {

      Server next = LoadBalancer.getInstance().getNext();

      Socket socket = new Socket(clientinfo.get(i), next);

      socketList.add(socket);
    }

    transmitTool.setSockets(socketList);
  }

  @Test
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

  public static void checkFileExist() throws IOException {

    File file = new File(CLIENTINFO_TXT);

    if (!file.exists()) {

      file.createNewFile();

    } else {

      file.delete();
    }

    FileOutputStream fileOutputStream = new FileOutputStream(file);

    String data =
        "client1 tom\n" + "client2 marry\n" + "client3 jack\n" + "client4 lily\n" + "client5 leo";

    fileOutputStream.write(data.getBytes());

    fileOutputStream.close();
  }
}
