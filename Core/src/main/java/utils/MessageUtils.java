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
        if( IOUtils.read(client.getInputStream(), message.getMessageINIT()) == 0) {
            return false;
        }


        //IS VALID MESSAGE
        if (message.getMessageINIT()[0] == 10) {

            //READ BYTES
            if( IOUtils.read(client.getInputStream(), message.getMessageBYTES()) == 0) {
                return false;
            }

            //READ FRAME
            if( IOUtils.read(client.getInputStream(), message.getMessageFRAME()) == 0) {
                return false;
            }


            //READ DATA
            message.setMessageDATA(new byte[message.getMessageBYTES()[0] - 5]);
            if( IOUtils.read(client.getInputStream(), message.getMessageDATA()) != message.getMessageDATA().length ) {
                return false;
            }


            //READ CRC
            if( IOUtils.read(client.getInputStream(), message.getMessageCRC()) == 0) {
                return false;
            }


            //READ END
            if( IOUtils.read(client.getInputStream(), message.getMessageEND()) == 0) {
                return false;
            }


            return true;

        }

        return false;
    }

    public boolean checkMessageIntegrity(CrcCalculator calculator, MessageEntity message){

        long calcCrc = calcMessageCRC8(calculator, message);
        //VERIFY INTEGRITY
        if ((byte) calcCrc == message.getMessageCRC()[0])
            return true;
        else
            return false;

    }

    public long calcMessageCRC8(CrcCalculator calculator, MessageEntity message){

        //CALCULATE CRC8
        byte[] crcBytes =ArrayUtils.addAll (message.getMessageBYTES(), message.getMessageFRAME());
        crcBytes = ArrayUtils.addAll(crcBytes, message.getMessageDATA());
       return calculator.Calc(crcBytes, 0, crcBytes.length);

    }


    public long calcMessageCRC8(MessageEntity message){

        CrcCalculator calculator = new CrcCalculator(Crc8.Crc8);
        byte[] crcBytes = ArrayUtils.addAll (message.getMessageBYTES(), message.getMessageFRAME());
        crcBytes = ArrayUtils.addAll(crcBytes, message.getMessageDATA());
        return calculator.Calc(crcBytes, 0, crcBytes.length);

    }


}
