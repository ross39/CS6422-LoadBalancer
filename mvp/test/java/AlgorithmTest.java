package test.java;
/*
 * Authors Cheuk Wei Lin, Meiqi Huang,Ross Heaney, Ruyun Sun, Yiqiu Wang
 */

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import main.java.Algorithm;
import main.java.Server;

public class AlgorithmTest {
    /*
    * Test class for Algorithm.java  
    * Testing the following
        1)SetServerlist
        2)RandomSelectNext
        3)RoundRobinNext
        4)WeightDependentNext 
    */

    private final Algorithm algo = new Algorithm();


    @Test
    void testSetServerList(){
       /* ArrayList<Server> testListActual = new ArrayList<>();
        testListActual.add(new Server(1, "ip1"));
        testListActual.add(new Server(1, "ip2"));
        */
        ArrayList<Server> testListExpected = new ArrayList<>();
        testListExpected.add(new Server(1, "ip1"));
        testListExpected.add(new Server(1, "1p2"));

        algo.setServerList(testListExpected);
        assertArrayEquals(algo.getServerList().toArray(), testListExpected.toArray());
    }

    void testRandomSelectNext(){
        //This class seems to pick a random server, not sure how to test this as its random
        // you could assertFalse but this seems gimmicky as the test is guaranteed to fail 1/x times where x is size of severlist
        // if you assertTrue the test is guaranteed to fail 1 - 1/x times 
    }

    @Test
    void testRoundRobinNext() {
        //Round robin works by assigning time slices to each process in equal portions
        //and in circular order, handling all processes without priority
       ArrayList<Server> testListExpected = new ArrayList<>();
       testListExpected.add(new Server(1, "ip2"));
       testListExpected.add(new Server(2, "ip4"));
       testListExpected.add(new Server(3, "ip6"));
       testListExpected.add(new Server(5, "ip7"));

       algo.setServerList(testListExpected);
       
       //I expect RoundRobinNext() to return testListExpected[0]
       
       assertEquals(algo.RoundRobinNext(),testListExpected.get(0));


    }

    @Test
    void testWeightDependentNext() {
      //Test weighted round robin 
      
      ArrayList<Server> testListExpected = new ArrayList<>();
      testListExpected.add(new Server(1, "ip2"));
      testListExpected.add(new Server(2, "ip4"));
      testListExpected.add(new Server(3, "ip6"));
      testListExpected.add(new Server(5, "ip7"));
      algo.setServerList(testListExpected); 

      assertEquals(algo.WeightDependentNext(),testListExpected.get(3));
    }




}
