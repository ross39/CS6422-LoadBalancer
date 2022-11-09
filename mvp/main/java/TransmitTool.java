package main.java;

import java.io.*;

public class TransmitTool {

    /*
    sample input:
    serveraddress: 10.0.0.1
    clientreq: 10.0.0.2 /batch1/stream1
    */

    String json;
    int id;

    public void processMessage(Request Req) {

        String [] newclientreq = Req.getClientReqStr().split("\\s+");
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
