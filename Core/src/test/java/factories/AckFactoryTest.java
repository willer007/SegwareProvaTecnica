package factories;

import entities.MessageEntity;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AckFactoryTest {
    MessageEntity ack0MessageEntity = new MessageEntity((byte) 0x0A,(byte) 0x05,(byte) 0xA0,null, (byte) 0x28,  (byte) 0x0D);
    MessageEntity ack3MessageEntity = new MessageEntity((byte) 0x0A,(byte) 0x0B,(byte) 0xA3,new byte[]{0x11,0x06,0x14,0x11,0x2B,0x0F}, (byte) 0xFE,  (byte) 0x0D);

    @Test
    void createAckA0() {
        assertArrayEquals(ack0MessageEntity.toByteArray(),AckFactory.createAckA0().toByteArray());
    }

    @Test
    void createAckA3() {

        assertArrayEquals(ack3MessageEntity.toByteArray(),AckFactory.createAckA3(ZonedDateTime.parse("2020-06-17T17:43:15.252+05:30[America/Sao_Paulo]")).toByteArray());
    }
}