package main.java;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProgramEntry {

    public static final String CLIENTINFO_TXT = "main/java/resource/clientinfo.txt";

    static TransmitTool transmitTool = new TransmitTool();

    static Server server1;

    static Server server2;

    static List<String> clientinfo = new ArrayList<>();


    static void cleanServerFile() {

        String filePath = IpGenerator.getInstance().getFilePath();

        int i = 1;

        File file = new File(filePath + "server" + i + ".txt");

        while (file.exists()) {

            file.delete();

            i++;

            file = new File(filePath + "server" + i + ".txt");


        }

    }

    public static void main(String[] args) throws IOException {

        cleanServerFile();

        prologue();

        BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));

        String choice = reader.readLine();

        boolean flag = true;

        while (flag){

            switch (choice){

                case "1":

                    addServerPrologue();

                    break;

                case "2":

                    addClientPrologue();

                    break;

                case "3":

                    System.out.println("bye!");

                    flag = false;

                    break;

                default:

                    System.out.println("sorry, please enter again!");

            }

            if (flag){

                prologue();

                reader = new BufferedReader( new InputStreamReader(System.in));

                choice = reader.readLine();

            }


        }


    }

    private static void addClientPrologue() {

        System.out.println("--start creating a client!");

        clientlisten();

        System.out.println("--the client has started!\n");



    }

    private static void addServerPrologue() throws IOException {

        System.out.println("--please assign a weight for server!");

        BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));

        String weight = reader.readLine();

        int i = Integer.parseInt(weight);

        addNewServer(i);

        System.out.println("--a server is listening!");

    }

    private static void prologue() {

        System.out.println("\nhello, this is a loadbalancer program, which action do you want to do?");

        System.out.println("A: add a new server and start to listen! press 1 to start!");

        System.out.println("B: add a new client and start sending requests! press 2 to start!");

        System.out.println("C: quit the program! press 3 to quit!\n");

    }

    private static void clientlisten() {

        //clientsend thread1
        Thread clientsend = new Thread(new Runnable() {

            @Override
            public void run() {

                // clients start sending requests

                Client.readFile(CLIENTINFO_TXT);

                clientinfo = Client.actualResult;

                List<Socket> socketList = new ArrayList<>();

                while (LoadBalancer.getInstance().getServer_list().isEmpty()) {

                    try {
                        System.out.println( "----Client Thread: No server started! Please add a Server!");

                        Thread.sleep(10000);

                    } catch (InterruptedException e) {

                        e.printStackTrace();

                    }

                }

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

                System.out.println( "----Client Thread: Complete sending!\n");

            }
        });

        clientsend.start();
    }

    private static void addNewServer(int weight) {

        Thread serverlisten = new Thread(new Runnable() {

            @Override
            public void run() {

                // servers start listening
                Server  server = new Server(weight);

                // loadbalancer get the server list from serverpool
                LoadBalancer.getInstance().setServer_list(ServerPool.getServerPool().getPool());

            }
        });

        serverlisten.start();
    }
}
