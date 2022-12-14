package main.java;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * Authors Cheuk Wei Lin, Meiqi Huang,Ross Heaney, Ruyun Sun, Yiqiu Wang
 */

public class Server implements Comparable {

    private List<String> requests = new LinkedList<>();

    //the amount of request that the server can process =  weight * factor;
    public static int factor = 2;

    private boolean server_switch = true;

    private boolean server_off = false;


    /**
     * initialize the ip adrress and default weight once start.
     * then register itself to serverpool
     */
    Server() {

        this.ip = getIPAddress();

        this.weight = 1;

        // register itself to serverpool once created new object
        register();

        startListen();

    }

    public Server(int weight, String ip) {

        this.ip = ip;

        this.weight = weight;

        // register itself to serverpool once created new object
        register();

        startListen();

    }


    /**
     * initialize the ip adrress and specify the weight of server once start
     * then register itself to serverpool
     *
     * @param weight
     */
    public Server(int weight) {

        this.ip = getIPAddress();

        this.weight = weight;

        // register itself to serverpool once created new object
        register();

        startListen();
    }

    /**
     * get the ip address from IPGenerator
     *
     * @return a avaliable ip address
     */
    private String getIPAddress() {

        return IpGenerator.getInstance().getIP();

    }

    /**
     * mock a fake ip address
     *
     * @return
     */
    private String getFakeIpAddress() {

        return "server1.txt";

    }

    /**
     * register server itself to the serverpool
     */
    private void register() {

        ServerPool serverPool = ServerPool.getServerPool();

        serverPool.add_server(this);
    }

    /**
     * server start listening
     * create an empty file using the ip address
     */
    public void startListen() {

        try {

            File file = new File(this.ip);

            if (file.createNewFile()) {

            } else {

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        ProcessingRequest();

    }

    public String getIp() {

        return ip;

    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    String ip;

    int weight;

    @Override
    public int compareTo(Object o) {

        if (o == null)
            throw new NullPointerException("cannot compare the null object");

        Server server = (Server) o;

        if (server.getWeight() > this.weight)
            return -1;

        else if (server.getWeight() < this.weight)
            return 1;

        return 0;
    }

    public synchronized List<String> getRequests() {

        return requests;
    }

    public synchronized Boolean push(String clientinfo){

        if (requests.size() <= weight*factor){

            requests.add(clientinfo);

            return true;

        }
        else{

            return false;

        }
    }

    private void ProcessingRequest(){

        Server server = this;

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                while (server_switch){

                    if (requests.isEmpty()) {

                        System.out.println("\n----Server Thread: Server "+server.ip+" is waiting for request...");

                        // sleep
                        delay(3000);


                    }else{


                        while (!requests.isEmpty()){

                            Socket socket = new Socket(requests.get(0), server);

                            ProcessRequestOneByOne(socket);

                            requests.remove(0);

                        }

                    }



                }

                System.out.println("\n----Server Thread: Server "+server.ip+" is closed");

                server_off = true;
            }
        });

        thread.start();




    }

    private void ProcessRequestOneByOne(Socket socket) {

        String clientip = socket.getClientip();

        String message = socket.getMessage();

        String serverip = socket.getServerip();

        String clientinfo = socket.getClientinfo();

        File file = new File(serverip);

        System.out.println("\n" + ColorText.ANSI_CYAN_BACKGROUND + "----Server Thread: Server "+serverip+" processing the request "+clientinfo+"!" + ColorText.ANSI_RESET + "\n");

        try {

            FileOutputStream fileOutputStream = new FileOutputStream(file, true);

            if (clientinfo.isEmpty()){

                String s = clientip + " : " + message+"\n";

                fileOutputStream.write(s.getBytes());

            }else{

                String s = clientinfo + "\n";

                fileOutputStream.write(s.getBytes());

            }

            // delay
            delay(4000);

            System.out.println("\n" + ColorText.ANSI_GREEN_BACKGROUND + "----Server Thread: Server "+serverip+" complete processing "+clientinfo + ColorText.ANSI_RESET + "\n");

            fileOutputStream.close();


        } catch (IOException e) {

            System.out.println("Server shut down or not exist");
        }





    }

    private void delay(int i) {
        try {

            Thread.sleep(i);

        } catch (InterruptedException e) {

            e.printStackTrace();

        }
    }

    public boolean isServer_switch() {
        return server_switch;
    }

    public void setServer_switch(boolean server_switch) {
        this.server_switch = server_switch;
    }

    public boolean isServer_off() {
        return server_off;
    }
}
