package main.java;

import java.io.*;
import java.util.*;

public class TransmitTool {

    static String[] verList = new String[500]; // store tokens for each client
    static ArrayList<String> reqList = new ArrayList<String>(); // store request form client

    public static String createToken(int clientid, String clientpw) {

        /*
            verify client credentials here
        */

        UUID uuid = UUID.randomUUID();
        String postfix = uuid.toString();
        String str1 = clientid + "," + postfix;

        verList[clientid] = postfix;
        String token = Base64.getEncoder().encodeToString(str1.getBytes());

        return token;

    }

    public boolean authMessage(String token) {

        boolean isPass = false;

        byte[] tokenBytes = Base64.getDecoder().decode(token);
        String str1 = new String(tokenBytes);

        try {
            String[] str2 = str1.split(",");
            int clientid = Integer.parseInt(str2[0]);
            if (verList[clientid].equals(str2[1])) {
                isPass = true;
                return isPass;
            } else {
                return isPass;
            }
        } catch (Exception e) {
            return isPass;
        }

    }

    public void processMessage(Request Req) {

        boolean tempStatus = true; // remove auth clients for Prototype 1, always true

         //if(authMessage(Req.getToken()) == true) {

        if(tempStatus == true) {

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

            if(json != null) {
                reqList.add(json);
                System.out.println("Authorization success and ready for sending.\n");
            } else {
                System.out.println("Error during processing message.\n");
            }
        }
        else {
            System.out.println("Authorization failed.\n");
        }
    }

    public void sendToServer() {

        for (int i = 0; i < reqList.size(); i++) {

            String jsonCache = reqList.get(i);
            String getId = jsonCache.substring(jsonCache.indexOf(":"), jsonCache.indexOf(","));
            String[] getId2 = getId.split("\\s+");

            String filename = getId2[1] + ".json";
            File file = new File(filename);

            try {
                PrintWriter pw = new PrintWriter(file);
                pw.println(jsonCache);
                pw.close();
            } catch (IOException e) {
                System.out.println("File generator: error occurred.\n");
            }

            System.out.println("File generated for Client ID: " + getId2[1] + ".");

        }

        reqList.removeAll(reqList);

    }

}