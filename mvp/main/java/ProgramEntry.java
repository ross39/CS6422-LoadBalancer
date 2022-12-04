package main.java;

import java.io.*;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProgramEntry {

  public static String CLIENTINFO_TXT = "clientinfo.txt";
  static ArrayList<String> data = new ArrayList<>();
  static List<String> clientinfo = new ArrayList<>();

  public static void main(String[] args) throws IOException, URISyntaxException {

    cleanFile();

    checkFileExist();

    prologue();

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    String choice = reader.readLine();

    boolean flag = true;

    while (flag) {
      switch (choice) {
        case "1":
          addServerPrologue();
          break;
        case "2":
          addClientPrologue();
          break;
        case "3":
          flag = false;
          break;
        default:
          System.out.println("sorry, please enter again!");
      }

      if (flag) {

        prologue();

        reader = new BufferedReader(new InputStreamReader(System.in));

        choice = reader.readLine();

      } else {

        ServerPool.getServerPool().closePool();

        System.out.println("\nbye!");
      }
    }
  }

  public static String generateClientIdentifier(int desiredLength) {

    String randomClient = UUID.randomUUID().toString().substring(0, desiredLength);
    return randomClient;
  }

  static void checkFileExist() throws IOException {

    File file = new File(CLIENTINFO_TXT);

    if (!file.exists()) {
      file.createNewFile();
    } else {
      file.delete();
    }

    FileOutputStream fileOutputStream = new FileOutputStream(file);
    ObjectOutputStream writeStream = new ObjectOutputStream(fileOutputStream);

    // Generate random client identifiers are write to the file
    for (int i = 0; i < 20; i++) {
      data.add(generateClientIdentifier(6));
    }

    /* fileOutputStream.write(data.getBytes()); */
    writeStream.writeObject(data.toString());
    fileOutputStream.close();
    writeStream.flush();
    writeStream.close();
  }

  static void cleanFile() {

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

  // used only when export jar
  private static void generatejar() throws URISyntaxException {

    CodeSource codeSource = ProgramEntry.class.getProtectionDomain().getCodeSource();

    File jarFile = new File(codeSource.getLocation().toURI().getPath());

    String jarDir = jarFile.getParentFile().getPath();

    System.out.println(jarDir);

    String serverpath = jarDir + "/resource/server/";

    IpGenerator.getInstance().setFilePath(serverpath);

    String clientpath = jarDir + "/resource/" + CLIENTINFO_TXT;

    CLIENTINFO_TXT = clientpath;
  }

  private static void addClientPrologue() {

    System.out.println("--start creating a client!");

    clientlisten();

    System.out.println("--the client has started!\n");
  }

  private static void addServerPrologue() throws IOException {

    System.out.println("--please assign a weight for server!");

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    try {

      String weight = reader.readLine();

      int i = Integer.parseInt(weight);

      addNewServer(i);

      System.out.println("--a server is listening!");

    } catch (Exception e) {

      System.out.println("--Wrong input for server! Failed to create a server!");
    }
  }

  private static void prologue() {

    System.out.println("\nhello, this is a loadbalancer program, which action do you want to do?");

    System.out.println("A: add a new server and start to listen! press 1 to start!");

    System.out.println("B: add a new client and start sending requests! press 2 to start!");

    System.out.println("C: quit the program! press 3 to quit!\n");
  }

  private static void clientlisten() {

    // clientsend thread1
    Thread clientsend =
        new Thread(
            new Runnable() {

              @Override
              public void run() {

                // clients start sending requests
                Client.readFile(CLIENTINFO_TXT);

                clientinfo = Client.actualResult;

                List<Socket> socketList = new ArrayList<>();

                CheckServerPool();

                // loadbalancer choose a server for a request based on weight
                AssignRequestToThread(socketList);
              }
            });

    clientsend.start();
  }

  private static void CheckServerPool() {

    while (LoadBalancer.getInstance().getServer_list().isEmpty()) {

      try {

        System.out.println("----Client Thread: No server started! Please add a Server!");

        Thread.sleep(10000);

      } catch (InterruptedException e) {

        e.printStackTrace();
      }
    }
  }

  private static void AssignRequestToThread(List<Socket> socketList) {

    Thread assign_thread =
        new Thread(
            new Runnable() {
              @Override
              public void run() {

                try {
                  AssignBasedOnWeightRoundRobin(socketList);
                } catch (InterruptedException | IOException e) {
                  e.printStackTrace();
                }
              }
            });

    assign_thread.start();
  }

  /*
   * Method to spin up new servers if client list exceeds some arbitrary value
   */

  // TODO: refactor this method to include spinUpNewServer()
  // FIXME: why are we passing in a socketlist if we are not using it?
  private static void AssignBasedOnWeightRoundRobin(List<Socket> socketList)
          throws InterruptedException, IOException {

    boolean overload = false;

    int warningcount = 0;

    for (int i = 0; i < clientinfo.size(); i++) {

      Server next = LoadBalancer.getInstance().getNext();

      Boolean push = next.push(clientinfo.get(i));

      System.out.println("----LoadBalancer: Assigned to Server!");

      if (data.size() > 15) {
        System.out.println("UH OH!! We need to spin up a new server to handle the load!");
        System.out.println("Number of clients has exceeded 15");
        addServerPrologue();
      }
    }
  }

  private static void addNewServer(int weight) {

    Thread serverlisten =
        new Thread(
            new Runnable() {

              @Override
              public void run() {

                // servers start listening
                Server server = new Server(weight);

                // loadbalancer get the server list from serverpool
                LoadBalancer.getInstance().setServer_list(ServerPool.getServerPool().getPool());
              }
            });

    serverlisten.start();
  }
}
