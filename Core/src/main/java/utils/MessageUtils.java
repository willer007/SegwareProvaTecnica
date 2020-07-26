package utils;

import crccalc.Crc8;
import crccalc.CrcCalculator;
import entities.MessageEntity;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.net.Socket;

public class MessageUtils {
    public boolean readMessage(Socket client, MessageEntity message) throws IOException {


        //READ INIT
        if( IOUtils.read(client.getInputStream(), message.getInit()) == 0) {
            return false;
        }


        //IS VALID MESSAGE
        if (message.getInit()[0] == 10) {

            //READ BYTES
            if( IOUtils.read(client.getInputStream(), message.getBytes()) == 0) {
                return false;
            }

            //READ FRAME
            if( IOUtils.read(client.getInputStream(), message.getFrame()) == 0) {
                return false;
            }


            //READ DATA
            message.setData(new byte[message.getBytes()[0] - 5]);
            if( IOUtils.read(client.getInputStream(), message.getData()) != message.getData().length ) {
                return false;
            }


            //READ CRC
            if( IOUtils.read(client.getInputStream(), message.getCrc()) == 0) {
                return false;
            }


            //READ END
            if( IOUtils.read(client.getInputStream(), message.getEnd()) == 0) {
                return false;
            }


            return true;

        }

        return false;
    }

    public boolean checkMessageIntegrity(CrcCalculator calculator, MessageEntity message){

        long calcCrc = calcMessageCRC8(calculator, message);
        //VERIFY INTEGRITY
        if ((byte) calcCrc == message.getCrc()[0])
            return true;
        else
            return false;

    }

    public long calcMessageCRC8(CrcCalculator calculator, MessageEntity message){

        //CALCULATE CRC8
        byte[] crcBytes =ArrayUtils.addAll (message.getBytes(), message.getFrame());
        crcBytes = ArrayUtils.addAll(crcBytes, message.getData());
       return calculator.Calc(crcBytes, 0, crcBytes.length);

    }


    public long calcMessageCRC8(MessageEntity message){

        CrcCalculator calculator = new CrcCalculator(Crc8.Crc8);
        byte[] crcBytes = ArrayUtils.addAll (message.getBytes(), message.getFrame());
        crcBytes = ArrayUtils.addAll(crcBytes, message.getData());
        return calculator.Calc(crcBytes, 0, crcBytes.length);

    }


}
