package test.java;
/*
 * Cheuk Wei Lin, Meiqi Huang,Ross Heaney, Ruyun Sun, Yiqiu Wang
 */

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import main.java.Server;
import main.java.ServerPool;
import org.junit.jupiter.api.Test;

public class ServerPoolTest{

    /*
    * Test class for ServerPool.java     
    */

    private ServerPool sp = ServerPool.getServerPool();

    @Test
    public void testGetServerPool(){
        //Test that it returns the same instance
        assertEquals(sp, ServerPool.getServerPool());
    }

    @Test
    public void testSetServerPool(){
        //Test that it sets the pool
        ArrayList<Server> pool = new ArrayList<>();
        sp.setPool(pool);
        assertEquals(pool, sp.getPool());
    }

    @Test 
    public void testAddServer(){
        //Test that it adds a server to the pool
        Server s = new Server(2, "ip3");
        // it would automatically register to pool inside server's constructor method
//        sp.add_server(s);
        assertEquals(s, sp.getPool().get(0));
    }






}



