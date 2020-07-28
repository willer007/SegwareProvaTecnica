package crccalc;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CrcCalculatorTest {
    CrcCalculator calculator = new CrcCalculator(Crc8.Crc8);
    @Test
    void calc() {
        byte[] crcBytes = new byte[] {0x09,0x01,0x31,0x32,0x33,0x34};
        long calculateCrc = calculator.Calc(crcBytes, 0, crcBytes.length);
        assertEquals((byte)calculateCrc ,(byte) 0xC6);
    }
}