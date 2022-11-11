package main.java;

import java.io.*;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.*;

public class TransmitTool {

    String json;
    int id;
    boolean pass;

    private static final long EXPIRE_TIME = 120 * 60 * 1000;//默认15分钟
    private static final String TOKEN_SECRET = "dsGUYSFef78dhf";

    public static String createToken(int clientid, String clientpw) {

        /*
         * verify user credentials here
         * */

        try {

            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);

            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

            Map<String, Object> header = new HashMap<>(2);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");

            return JWT.create()
                    .withHeader(header)
                    .withClaim("clientId", clientid)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean authMessage(String token) {

        boolean isPass = false;
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);

            // String clientId = jwt.getClaim("clientId").asString();

            /*
            verify if the user id match here
            */

            if(jwt != null) {
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
