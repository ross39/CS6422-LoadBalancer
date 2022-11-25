package test.java;
/*
 * Authors Cheuk Wei Lin, Meiqi Huang,Ross Heaney, Ruyun Sun, Yiqiu Wang
 */

import main.java.Server;
import main.java.ServerPool;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

/*
 * Test class for Server.java
 * test
 */
public class ServerTest{

    Server server;

    //Test that it starts listening
    @Test
     void testStartListen(){



        File file = new File("ip1");

        boolean exists = file.exists();

        assertEquals(true, exists);
    
    }

    @Test
     void testGetIpAddress(){



        assertEquals("ip1", server.getIp());

    }

    @Test
     void testRegister(){


        //get the server from serverpool
        ServerPool serverPool = ServerPool.getServerPool();

        ArrayList<Server> pool = serverPool.getPool();

        //check if the pool contains server
        boolean contains = pool.contains(server);

        assertEquals(true, contains);

    }

    @BeforeEach
    void CreateFile(){

        //check that server has been added to serverpool
        server = new Server(3, "ip1");


    }

    @AfterEach
    void ClearFile(){

        File file = new File(server.getIp());

        file.delete();

    }

}
