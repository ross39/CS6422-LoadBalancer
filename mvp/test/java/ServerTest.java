package test.java;
/*
 * Authors Cheuk Wei Lin, Meiqi Huang,Ross Heaney, Ruyun Sun, Yiqiu Wang
 */

import main.java.Server;
import main.java.ServerPool;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class ServerTest{
    /*
     * Test class for Server.java 
     * test
     */


   

    @Test
     void testStartListen(){
        //Test that it starts listening
    
    }

    @Test
     void testGetIpAddress(){
        //Test that it returns the correct ip address using reflection
        Server server = new Server(3, "ip1");
        assertEquals("ip1", server.getIp());

    }

    @Test
     void testRegister(){
        //check that server has been added to serverpool
        /* Server server = new Server(3, "ip1");
        assertEquals(server, ServerPool.getServerPool().getPool().get(0)); */
    }

}
