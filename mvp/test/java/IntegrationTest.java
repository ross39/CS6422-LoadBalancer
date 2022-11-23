package test.java;

import main.java.*;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {

    public static String CLIENTINFO_TXT = "clientinfo.txt";

    static TransmitTool transmitTool = new TransmitTool();

    static Server server1;

    static Server server2;

    static List<String> clientinfo = new ArrayList<>();


    @Test
    void integrationTest() throws IOException {

        cleanServerFile();

        checkFileExist();

        // servers start listening
        server1 = new Server(1);

        server2 = new Server(3);

        // loadbalancer get the server list from serverpool
        LoadBalancer.getInstance().setServer_list(ServerPool.getServerPool().getPool());

        // clients start sending requests
        ClientReadFile();

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

        cleanFile();

    }

    @Test
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

        checkFileExist();

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

                Client.clearFile();

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

    public static void checkFileExist() throws IOException {

        File file = new File(CLIENTINFO_TXT);

        if (!file.exists()){

            file.createNewFile();

        }else{

            file.delete();

        }

        FileOutputStream fileOutputStream = new FileOutputStream(file);

        String data = "client1 tom\n" +
                "client2 marry\n" +
                "client3 jack\n" +
                "client4 lily\n" +
                "client5 leo";

        fileOutputStream.write(data.getBytes());

        fileOutputStream.close();
    }

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

    private void ClientReadFile() {

        Client.readFile(CLIENTINFO_TXT);

    }

}
