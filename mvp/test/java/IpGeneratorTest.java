package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import main.java.IpGenerator;
import main.java.Server;

public class IpGeneratorTest {

    @Test
    void initializationTest(){
        String address1 = IpGenerator.getIP();
        assertEquals(address1, "mvp/out/server1.txt");
    }

    @Test
    void checkFileAddress(){
        Server server1 = new Server('1',"mvp/out/server2.txt");
        String ipAddress = server1.getIp();
        assertEquals(ipAddress, "mvp/out/server2.txt");
    }
}
