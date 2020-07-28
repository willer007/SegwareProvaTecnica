package converters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ByteToIntConverterTest {

    ByteToIntConverter byteToIntConverter = new ByteToIntConverter();
    int expectedInt = 32;
    byte expectedByte = 0x20;


    @Test
    void convertToDatabaseColumn() {
        int actualInt = byteToIntConverter.convertToDatabaseColumn(expectedByte);
        assertEquals(expectedInt,actualInt);
    }

    @Test
    void convertToEntityAttribute() {
        byte actualByte = byteToIntConverter.convertToEntityAttribute(expectedInt);
        assertEquals(expectedByte,actualByte);

    }
}