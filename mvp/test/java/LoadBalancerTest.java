package test.java;
/*
 * Authors Cheuk Wei Lin, Meiqi Huang,Ross Heaney, Ruyun Sun, Yiqiu Wang
 */

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

import main.java.Algorithm;
import main.java.LoadBalancer;
import main.java.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoadBalancerTest{
    /*
     * Test class for LoadBalancer.java 
     */

     //Can use reflection to get around private constructor

    @Test
    void test(){

        LoadBalancer loadBalancer = LoadBalancer.getInstance();

        ArrayList<Server> testListExpected = new ArrayList<>();

        testListExpected.add(new Server(1, "ip1"));

        testListExpected.add(new Server(2, "ip2"));

        testListExpected.add(new Server(3, "ip3"));

        testListExpected.add(new Server(4, "ip4"));

        loadBalancer.setServer_list(testListExpected);

        // test default algorithm
        assertEquals( loadBalancer.getAlgorithm_choice(), Algorithm.WeightDependentNext);

        // test the sequence of list is WeightRoundRobin
        for (int i = testListExpected.size()-1 ; i > -1 ; i--){

            assertEquals( loadBalancer.getNext(), testListExpected.get(i));

        }

    }

    @AfterEach
    void CleanFile(){

        for (int i = 1 ; i < 5 ; i++){

            File file = new File("ip"+i);

            file.delete();

        }


    }


}