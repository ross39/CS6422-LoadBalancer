package main.java;

import org.junit.runner.Request;

import java.io.*;
import java.util.*;

public class TransmitTool {

    List<Socket> sockets = new ArrayList<>();

    public List<Socket> getSockets() {
        return sockets;
    }

    public void setSockets(List<Socket> sockets) {
        this.sockets = sockets;
    }

    public void sendToServer() {

        for ( int i = 0; i < sockets.size() ; i++) {


            String clientip = sockets.get(i).getClientip();

            String message = sockets.get(i).getMessage();

            String serverip = sockets.get(i).getServerip();

            String clientinfo = sockets.get(i).getClientinfo();

            File file = new File(serverip);

            System.out.println("----Server Thread: Server "+serverip+" processing the request "+clientinfo+"!");

            try {

                FileOutputStream fileOutputStream = new FileOutputStream(file, true);

                if (clientinfo.isEmpty()){

                    String s = clientip + " : " + message+"\n";

                    fileOutputStream.write(s.getBytes());


                }else{

                    String s = clientinfo + "\n";

                    fileOutputStream.write(s.getBytes());

                }

                System.out.println("\n----Server Thread: Server "+serverip+" complete processing "+clientinfo+"\n");

                fileOutputStream.close();


            } catch (IOException e) {

                System.out.println("Server shut down or not exist");
            }

        }

        sockets.clear();

    }

}