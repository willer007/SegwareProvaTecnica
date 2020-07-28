package converters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ByteArrayToStringConverterTest {

    ByteArrayToStringConverter byteArrayToStringConverter = new ByteArrayToStringConverter();
    byte[] expectedByteArray = new byte[]{0x48,0x65,0x6C,0x6C,0x6F,0x20,0x57,0x6F,0x72,0x6C,0x64};
    String expectedString = "Hello World";

    @Test
    void convertToDatabaseColumn() {
        String actualString = byteArrayToStringConverter.convertToDatabaseColumn(expectedByteArray);
        assertEquals(expectedString,actualString);

    }

    @Test
    void convertToEntityAttribute() {
        byte[] actualByteArray = byteArrayToStringConverter.convertToEntityAttribute(expectedString);
        assertArrayEquals(expectedByteArray,actualByteArray);
    }
}