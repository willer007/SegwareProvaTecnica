package utils;

import crccalc.Crc8;
import crccalc.CrcCalculator;
import entities.MessageEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageUtilsTest {

    MessageEntity expectedTrueTextoMessageEntity = new MessageEntity((byte) 0x0A ,
            (byte)0x10,
            (byte)0xA1,
            new byte[]{0x48,0x65,0x6C,0x6C,0x6F,0x20,0x57,0x6F,0x72,0x6C,0x64},
            (byte)0xDC,
            (byte)0x0D);

    MessageEntity expectedFalseTextoMessageEntity = new MessageEntity((byte) 0x0A ,
            (byte)0x10,
            (byte)0xA1,
            new byte[]{0x48,0x65,0x00,0x6C,0x6F,0x20,0x57,0x6F,0x72,0x6C,0x64},
            (byte)0xDC,
            (byte)0x0D);

    MessageUtils messageUtils = new MessageUtils();
    CrcCalculator calculator = new CrcCalculator(Crc8.Crc8);

    @Test
    void checkMessageIntegrity() {
        assertTrue(messageUtils.checkMessageIntegrity(calculator, expectedTrueTextoMessageEntity));
        assertFalse(messageUtils.checkMessageIntegrity(calculator, expectedFalseTextoMessageEntity));
    }


}