package entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageEntityTest {
    byte[] expectedMessageByteArray = {0x0A,0x10,(byte)0xA1,0x48,0x65,0x6C,0x6C,0x6F,0x20,0x57,0x6F,0x72,0x6C,0x64,(byte)0xDC,0x0D};
    MessageEntity messageEntity =
            new MessageEntity((byte)0x0A,
                                (byte)0x10,
                                (byte)0xA1 ,
                                new byte[]{0x48, 0x65, 0x6C, 0x6C, 0x6F, 0x20, 0x57, 0x6F, 0x72, 0x6C, 0x64},
                                (byte)0xDC,
                                (byte)0x0D);


    @Test
    void toByteArray() {
        assertArrayEquals(expectedMessageByteArray,messageEntity.toByteArray());
    }

}