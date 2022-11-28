package test.java;
/*
 * Authors Cheuk Wei Lin, Meiqi Huang,Ross Heaney, Ruyun Sun, Yiqiu Wang
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import main.java.ServerDetails;
import org.junit.jupiter.api.Test;

public class ServerDetailsTest {

  /*
   * Test class for ServerDetails.java
   */
  private ServerDetails sd;

  // Sanity check
  @Test
  void testGetWeight() {

    sd = new ServerDetails(2, "ip2");

    assertEquals(sd.getWeight(), 2);
  }

  // Sanity check
  @Test
  void testGetAddress() {

    sd = new ServerDetails(4, "ip3");

    assertEquals(sd.getAddress(), "ip3");
  }

  // We have overidden the compareTo so lets test it!
  @Test
  void testCompareToThrowsException() {

    sd = new ServerDetails(4, "ip3");

    // Test that it throws an exception if given null
    try {

      sd.compareTo(null);

      fail("This method failed to throw an exception");

    } catch (NullPointerException e) {

    }
  }
}
