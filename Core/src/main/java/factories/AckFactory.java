package factories;

import entities.MessageEntity;
import utils.MessageUtils;

import java.time.ZonedDateTime;

public class AckFactory {

    public static MessageEntity createAckA0() {
        return new MessageEntity(
                (byte) 0x0A,
                (byte) 0x05,
                (byte) 0xA0,
                null,
                (byte) 0x28,
                (byte) 0x0D);
    }

    public static MessageEntity createAckA3(ZonedDateTime date) {

        MessageUtils messageUtils = new MessageUtils();

        char[] dateCharArray = String.valueOf(date.getYear()).toCharArray();
        int yearDigitOne = dateCharArray[dateCharArray.length - 2] - 48;
        int yearDigitTwo = dateCharArray[dateCharArray.length - 1] - 48;

        int year = Integer.parseInt("" + yearDigitOne + yearDigitTwo);

        byte[] data = new byte[]{
                (byte) date.getDayOfMonth(),
                (byte) date.getMonthValue(),
                (byte) year,
                (byte) date.getHour(),
                (byte) date.getMinute(),
                (byte) date.getSecond(),
        };

        MessageEntity messageEntity = new MessageEntity(
                (byte) 0x0A,
                (byte)  (5 + data.length),
                (byte) 0xA3,
                data,
                (byte) 0x00,
                (byte) 0x0D);

        messageEntity.setCrc( new byte [] {(byte) messageUtils.calcMessageCRC8(messageEntity)});

        return messageEntity;

    }
}
