package factories;

import entities.InfoUsuarioEntity;
import entities.MessageEntity;
import utils.MessageUtils;

public class MessageFactorySingleton {

    private static MessageUtils messageUtils = new MessageUtils();

    public static MessageEntity buildMessage(byte frame, byte[] data){
        MessageEntity messageEntity = new MessageEntity(
                (byte) 0x0A,
                (byte)(5 + data.length),
                (byte) frame,
                 data,
                (byte) 0x00,
                (byte) 0x0D);

        messageEntity.setCrc( new byte[]{(byte) messageUtils.calcMessageCRC8(messageEntity)});

        return messageEntity;

    }

    public static MessageEntity buildTextMessage(String text){

        byte [] data =  text.getBytes();

        return buildMessage((byte) 0xA1, data );

    }

    public static MessageEntity buildInfoUsuarioMessage(InfoUsuarioEntity infoUsuarioEntity){

        return buildMessage((byte) 0xA2, infoUsuarioEntity.toByteArray());
    }


    public static MessageEntity buildSolicitacaoDataHoraMessage(String text){
        byte [] data =  text.getBytes();

        return buildMessage((byte) 0xA3,data);
    }
}


