package enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrameEnumTest {

    FrameEnum frameEnum;
    @Test
    void parseByteToFrameEnum() {

        assertEquals((byte)0xA0, FrameEnum.parseByteToFrameEnum((byte) 0xA0).byteValue);
        assertEquals((byte)0xA1, FrameEnum.parseByteToFrameEnum((byte) 0xA1).byteValue);
        assertEquals((byte)0xA2, FrameEnum.parseByteToFrameEnum((byte) 0xA2).byteValue);
        assertEquals((byte)0xA3, FrameEnum.parseByteToFrameEnum((byte) 0xA3).byteValue);
        assertEquals((byte)0x00, FrameEnum.parseByteToFrameEnum((byte) 0x00).byteValue);

    }
}