package converters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ByteArrayToHexStringConverterTest {

    ByteArrayToHexStringConverter byteArrayToHexStringConverter = new ByteArrayToHexStringConverter();
    String expectedHexString = "48656C6C6F20576F726C64".toLowerCase();
    byte[] expectedByteArray = new byte[]{0x48,0x65,0x6C,0x6C,0x6F,0x20,0x57,0x6F,0x72,0x6C,0x64};

    @Test
    void convertToDatabaseColumn() {

        String actualHexString = byteArrayToHexStringConverter.convertToDatabaseColumn(expectedByteArray);
        assertEquals(expectedHexString, actualHexString);
    }

    @Test
    void convertToEntityAttribute() {
        byte[] actualByteArray = byteArrayToHexStringConverter.convertToEntityAttribute(expectedHexString);
        assertArrayEquals(expectedByteArray, actualByteArray);

    }
}