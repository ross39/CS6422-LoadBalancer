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

            String clientinfo = sockets.get(i).getClientinfo();

            File file = new File(serverip);

            try {

                FileOutputStream fileOutputStream = new FileOutputStream(file, true);

                if (clientinfo.isEmpty()){

                    String s = clientip + " : " + message+"\n";

                    fileOutputStream.write(s.getBytes());


                }else{

                    String s = clientinfo + "\n";

                    fileOutputStream.write(s.getBytes());

                }


                fileOutputStream.close();


            } catch (IOException e) {

                System.out.println("Server shut down or not exist");
            }

            sockets.remove(i);

            i--;

        }



    }

}