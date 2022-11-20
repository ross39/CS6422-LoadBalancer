package test.java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.java.Request;
import main.java.TransmitTool;

import java.io.File;

class TransmitToolTest {

    @Test
    void generateNewFile() {

        TransmitTool transmitTool = new TransmitTool();

        String token = transmitTool.createToken(2, "Abc123456");
        String token2 = transmitTool.createToken(3, "Abc123456");
        String token3 = transmitTool.createToken(10, "Abc123456");

        Request R1 = new Request(2,"10.0.0.1", "10.0.0.2 Batch1/Stream1", token);
        Request R2 = new Request(3,"10.0.0.1", "10.0.0.20 Batch2/Stream6", token2);
        Request R3 = new Request(10,"10.0.0.1", "10.0.0.50 Batch1/Stream9", token3);

        transmitTool.processMessage(R1);
        transmitTool.processMessage(R2);
        transmitTool.processMessage(R3);

        transmitTool.sendToServer();

        File f1 = new File("2.json");
        File f2 = new File("3.json");
        File f3 = new File("10.json");

        assertEquals(true, f1.exists());
        assertEquals(true, f2.exists());
        assertEquals(true, f3.exists());

    }
}
