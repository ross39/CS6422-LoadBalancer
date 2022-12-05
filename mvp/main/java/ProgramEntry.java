package main.java;

import java.io.*;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;

public class ProgramEntry {

    public static String CLIENTINFO_TXT = "clientinfo.txt";

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

                    System.out.println(ColorText.ANSI_RED_BACKGROUND + "sorry, please enter again!" + ColorText.ANSI_RESET);

            }

            if (flag) {

                prologue();

                reader = new BufferedReader(new InputStreamReader(System.in));

                choice = reader.readLine();

            } else {

                ServerPool.getServerPool().closePool();

                System.out.println("\n" + ColorText.ANSI_GREEN_BACKGROUND + "bye!" + ColorText.ANSI_RESET);
            }


        }

    }

    static void checkFileExist() throws IOException {

        File file = new File(CLIENTINFO_TXT);

        if (!file.exists()) {

            file.createNewFile();

        } else {

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

//        String serverpath = jarDir + "/resource/server/";

//        IpGenerator.getInstance().setFilePath(serverpath);

//        String clientpath = jarDir + "/resource/" + CLIENTINFO_TXT;
//
//        CLIENTINFO_TXT = clientpath;
    }

    private static void addClientPrologue() {

        System.out.println("--start creating a client!");

        clientlisten();

        System.out.println(ColorText.ANSI_GREEN_BACKGROUND + "--the client has started!\n" + ColorText.ANSI_RESET);


    }

    private static void addServerPrologue() throws IOException {

        System.out.println(ColorText.ANSI_YELLOW_BACKGROUND + "--please assign a weight for server!" + ColorText.ANSI_RESET);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {

            String weight = reader.readLine();

            int i = Integer.parseInt(weight);

            addNewServer(i);

            System.out.println(ColorText.ANSI_GREEN_BACKGROUND + "--a server is listening!" + "\n --this server can accept " + i * Server.factor + " requests one time!" + ColorText.ANSI_RESET);

        } catch (Exception e) {

            System.out.println(ColorText.ANSI_RED_BACKGROUND + "--Wrong input for server! Fail to create a server!" + ColorText.ANSI_RESET);
        }


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

                System.out.println(ColorText.ANSI_YELLOW_BACKGROUND + "----Client Thread: No server started! Please add a Server!" + ColorText.ANSI_RESET);

                Thread.sleep(10000);

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

        }
    }

    private static void AssignRequestToThread(List<Socket> socketList) {

        Thread assign_thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    AssignBasedOnWeightRoundRobin(socketList);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        assign_thread.start();

    }

    private static void AssignBasedOnWeight(List<Socket> socketList) {

    }

    private static void AssignBasedOnWeightRoundRobin(List<Socket> socketList) throws InterruptedException {

        boolean overload = false;

        int warningcount = 0;

        for (int i = 0; i < clientinfo.size(); i++) {

            Server next = LoadBalancer.getInstance().getNext();

            Boolean push = next.push(clientinfo.get(i));

            if (!push) {

                if (overload) {

                    Thread.sleep(3000);

                    warningcount = 0;

                    overload = false;

                }

                warningcount++;

                if (warningcount > 10) {

                    System.out.println(ColorText.ANSI_YELLOW_BACKGROUND + "----LoadBalancer: This Server " + next.getIp() + " is overloading!" + ColorText.ANSI_RESET);

                    overload = true;

                }

                i--;

            } else {
                System.out.println(ColorText.ANSI_GREEN_BACKGROUND + "----LoadBalancer: Assigned a request" + clientinfo.get(i) + " to a Server" + next.getIp() + "!" + ColorText.ANSI_RESET);
            }


        }
    }

    private static void addNewServer(int weight) {

        Thread serverlisten = new Thread(new Runnable() {

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

    static List<String> clientinfo = new ArrayList<>();
}
