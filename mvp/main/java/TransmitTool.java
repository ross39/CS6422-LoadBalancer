package main.java;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class TransmitTool {

    String json;
    int id;
    boolean pass;
    public boolean authMessage(String token) {

        boolean isPass = false;

        /*
            verify token here
        */

        return isPass;
    }

    public void processMessage(Request Req) {

        if(authMessage(Req.getToken()) == true) {
            String[] newclientreq = Req.getClientReqStr().split("\\s+");
            String clientaddress = newclientreq[0];
            String clientreqloc = newclientreq[1];

            String json = "{\n" +
                    "   \"clientid\": " + Req.getClientId() + ",\n" +
                    "   \"serveraddress\": \"" + Req.getServerAddress() + "\",\n" +
                    "   \"clientAddress\": \"" + clientaddress + "\",\n" +
                    "   \"clientreqloc\": \"" + clientreqloc + "\"\n" +
                    "}";

            System.out.println(json + "\n");

            this.id = Req.getClientId();
            this.json = json;
            this.pass = true;
        }
        else {
            this.pass = false;
            System.out.println("Authorization failed.");
        }
    }

    public void sendToServer() {
        if (pass == true) {
            String filename = this.id + ".json";
            File file = new File(filename);

            try {
                PrintWriter pw = new PrintWriter(file);
                pw.println(this.json);
                pw.close();
            } catch (IOException e) {
                System.out.println("Error occurred.");
            }

            System.out.println("File generated.");
        }
    }

}
