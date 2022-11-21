package main.java;

import java.io.File;
import java.io.IOException;

/**
 * A IPGenerator class that generate ipAddress. use the path of the server file to stimulate IP address
 *
 * @author Ruyun
 */
public class IpGenerator {

    private int i = 1;

    private String filePath = "main/java/resource/server/";

    private String fileNamePrefix = "server" ;

    private String fileNameSuffix = ".txt";


    public String getFilePath() {

        return filePath;

    }

    public void setFilePath(String filePath) {

        this.filePath = filePath;

    }

    private static IpGenerator ipGenerator = new IpGenerator();

    private IpGenerator() {

    }

    public static IpGenerator getInstance() {

        return ipGenerator;

    }

    /**
     * generate a IP address and return it (followed as server1, server2, server3)
     * server is stored in out repository now
     *
     * @return String
     */
    public String getIP() {

        String fileName = filePath + fileNamePrefix + i + fileNameSuffix;

        File file = new File(fileName);

        while (file.exists()) {

            i++;

            fileName = filePath + fileNamePrefix + i + fileNameSuffix;

            file = new File(fileName);

        }

        return fileName;
    }

}
