package test.java;
/*
 * Authors Cheuk Wei Lin, Meiqi Huang,Ross Heaney, Ruyun Sun, Yiqiu Wang
 */

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

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
        //This was wrong as I was trying to compare two arrays. Yes they contain the same stuff but their object ID's are different
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


}
