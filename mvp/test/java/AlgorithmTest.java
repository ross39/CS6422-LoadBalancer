package test.java;
/*
 * Authors Cheuk Wei Lin, Meiqi Huang,Ross Heaney, Ruyun Sun, Yiqiu Wang
 */

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.ArrayList;
import main.java.Algorithm;
import main.java.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

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
  void testSetServerList() {

    ArrayList<Server> testListExpected = new ArrayList<>();

    testListExpected.add(new Server(1, "ip1"));

    testListExpected.add(new Server(1, "ip2"));

    algo.setServerList(testListExpected);

    assertArrayEquals(algo.getServerList().toArray(), testListExpected.toArray());
  }

  @Test
  void testRandomSelectNext() {

    ArrayList<Server> testListExpected = new ArrayList<>();

    testListExpected.add(new Server(1, "ip1"));

    testListExpected.add(new Server(2, "ip2"));

    testListExpected.add(new Server(3, "ip3"));

    testListExpected.add(new Server(4, "ip4"));

    algo.setServerList(testListExpected);

    // create a list to store the random sequence of list
    ArrayList<Server> testRandomSelectList = new ArrayList<>();

    // add server in random sequence
    for (int i = 0; i < testListExpected.size(); i++) {

      testRandomSelectList.add(algo.RandomSelectNext());
    }
    // test the sequence of list
    assertNotEquals(testListExpected, testRandomSelectList);
  }

  @Test
  void testRoundRobinNext() {

    // Round robin works by assigning time slices to each process in equal portions
    // and in circular order, handling all processes without priority

    ArrayList<Server> testListExpected = new ArrayList<>();

    testListExpected.add(new Server(1, "ip2"));

    testListExpected.add(new Server(2, "ip4"));

    testListExpected.add(new Server(3, "ip6"));

    testListExpected.add(new Server(5, "ip7"));

    algo.setServerList(testListExpected);

    // test the sequence of list
    for (int i = 0; i < testListExpected.size(); i++) {

      assertEquals(algo.RoundRobinNext(), testListExpected.get(i));
    }
  }

  @Test
  void testWeightDependentNext() {
    // Test weighted round robin

    ArrayList<Server> testListExpected = new ArrayList<>();

    testListExpected.add(new Server(1, "ip2"));

    testListExpected.add(new Server(2, "ip4"));

    testListExpected.add(new Server(3, "ip6"));

    testListExpected.add(new Server(5, "ip7"));

    algo.setServerList(testListExpected);

    // test the sequence of list
    for (int i = testListExpected.size() - 1; i > -1; i--) {

      assertEquals(algo.WeightDependentNext(), testListExpected.get(i));
    }
  }

  @AfterEach
  void CleanFile() {

    for (int i = 0; i < 8; i++) {

      File file = new File("ip" + i);

      file.delete();
    }
  }
}
