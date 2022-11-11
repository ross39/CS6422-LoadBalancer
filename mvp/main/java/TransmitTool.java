package main.java;

import java.io.*;

public class TransmitTool {

    /*
    example input:
    serveraddress: 10.0.0.1
    clientreq: 10.0.0.2 /batch1/stream1
    token: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.
    eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.
    SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
    */

    String json;
    int id;

    public boolean authMessage(String token) {

        /*
        verify token here
        */

        boolean isPass = true;
        return isPass;
    }

    public void processMessage(Request Req) {

        if(authMessage(Req.getToken()) == true) {
            String[] newclientreq = Req.getClientReqStr().split("\\s+");
            String clientaddress = newclientreq[0];
            String clientreqloc = newclientreq[1];

            String json = "{\n" +
                    "   \"id\": " + Req.getId() + ",\n" +
                    "   \"serveraddress\": \"" + Req.getServerAddress() + "\",\n" +
                    "   \"clientAddress\": \"" + clientaddress + "\",\n" +
                    "   \"clientreqloc\": \"" + clientreqloc + "\"\n" +
                    "}";

            System.out.println(json + "\n");

            this.id = Req.getId();
            this.json = json;
        }
        else {
            System.out.println("Authorization failed.");
        }
    }

    public void sendToServer() {

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
