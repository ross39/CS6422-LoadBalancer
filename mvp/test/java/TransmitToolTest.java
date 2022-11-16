package test.java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TransmitToolTest {

    @Test
    void processValidMessage() {
        TransmitTool transmitTool = new TransmitTool();
        String token = transmitTool.createToken(2, "Abc123456");
        Request R1 = new Request(2,"10.0.0.1", "10.0.0.2 Batch1/Stream1", token);
        transmitTool.processMessage(R1);

        assertNotNull(transmitTool.json);
        assertEquals(true, transmitTool.pass);

    }

    @Test
    void processInvalidMessage() {
        TransmitTool transmitTool = new TransmitTool();
        Request R2 = new Request(2,"10.0.0.1", "10.0.0.2 Batch1/Stream1", "Abc123");
        transmitTool.processMessage(R2);

        assertNull(transmitTool.json);
        assertEquals(false, transmitTool.pass);

    }

    @Test
    void sendToServer() {

    }
}
