package main.java;

import java.io.*;

public class TransmitTool {

    /*
    sample input:
    serveraddress: 10.0.0.1
    clientreq: 10.0.0.2 /batch1/stream1
    */

    int id;
    String json;

    public void processMessage(int id, String serveraddress, String clientreq) {
        String [] newclientreq = clientreq.split("\\s+");
        String clientaddress = newclientreq[0];
        String clientreqloc = newclientreq[1];

        String json = "{\n" +
                "   \"id\": " + id + ",\n" +
                "   \"serveraddress\": \"" + serveraddress + "\",\n" +
                "   \"clientAddress\": \"" + clientaddress + "\",\n" +
                "   \"clientreqloc\": \"" + clientreqloc + "\"\n" +
                "}";

        System.out.println(json + "\n");

        this.id = id;
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
            System.out.println("Error Occurred.");
        }


        System.out.println("File Generated.");
    }

}
