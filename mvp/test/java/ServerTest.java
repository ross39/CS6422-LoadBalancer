package test.java;
/*
 * Authors Cheuk Wei Lin, Meiqi Huang,Ross Heaney, Ruyun Sun, Yiqiu Wang
 */

import main.java.Server;
import main.java.ServerPool;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

/*
 * Test class for Server.java
 * test
 */
public class ServerTest{

    //Test that it starts listening
    @Test
     void testStartListen(){

        Server server = new Server(3, "ip1");

        // once start listening, server would create an empty file according to ip address
        server.startListen();

        File file = new File("ip1");

        boolean exists = file.exists();

        assertEquals(true, exists);
    
    }

    @Test
     void testGetIpAddress(){

        //Test that it returns the correct ip address using constructor method.
        Server server = new Server(3, "ip1");

        assertEquals("ip1", server.getIp());

    }

    @Test
     void testRegister(){

        //check that server has been added to serverpool
        Server server = new Server(3, "ip1");

        //get the server from serverpool
        ServerPool serverPool = ServerPool.getServerPool();

        ArrayList<Server> pool = serverPool.getPool();

        //check if the pool contains server
        boolean contains = pool.contains(server);

        assertEquals(true, contains);

    }

}
