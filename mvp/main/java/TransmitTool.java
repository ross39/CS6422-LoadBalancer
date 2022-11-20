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

        int i = sockets.size() - 1;

        while (sockets.size()!=0) {

            String clientip = sockets.get(i).getClientip();

            String message = sockets.get(i).getMessage();

            String serverip = sockets.get(i).getServerip();

            File file = new File(serverip);

            try {

                PrintWriter pw = new PrintWriter(file);

                pw.println(clientip+" : "+message);

                pw.close();

            } catch (IOException e) {

                System.out.println("Server shut down or not exist");
            }

            sockets.remove(i);

            i--;

        }



    }

}