package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import main.java.IpGenerator;
import main.java.Server;

import java.io.File;

public class IpGeneratorTest {

    @Test
    void initializationTest(){

        IpGenerator instance = IpGenerator.getInstance();

        String address1 = instance.getIP();

        File file = new File(address1);

        boolean exists = file.exists();

        // ensure return a available file path
        assertEquals(false, exists);

    }

    @Test
    void checkFileAddress(){

        // if your File path is incompatible with current working environment
        // Please set File path to ""
        // by default file path is main/java/resources
        IpGenerator.getInstance().setFilePath("");

        Server server1 = new Server('3', IpGenerator.getInstance().getIP());

        server1.startListen();

        String ipAddress = server1.getIp();

        File file = new File(ipAddress);

        boolean exists = file.exists();

        //ensure server has created the file with given path
        assertEquals(true, exists);
    }
}
