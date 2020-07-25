package factories;

import entities.InfoUsuarioDataEntity;
import entities.MessageEntity;
import utils.MessageUtils;

public class MessageFactory {

    private MessageUtils messageUtils = new MessageUtils();

    public MessageEntity buildMessage(byte frame, byte[] data){
        MessageEntity messageEntity = new MessageEntity(
                (byte) 0x0A,
                (byte)(5 + data.length),
                (byte) frame,
                 data,
                (byte) 0x00,
                (byte) 0x0D);

        messageEntity.setMessageCRC( new byte[]{(byte) messageUtils.calcMessageCRC8(messageEntity)});

        return messageEntity;

    }

    public MessageEntity buildTextMessage(String text){

        byte [] data =  text.getBytes();

        return buildMessage((byte) 0xA1, data );

    }

    public MessageEntity buildInfoUsuarioMessage(InfoUsuarioDataEntity infoUsuarioDataEntity){

        return buildMessage((byte) 0xA2,infoUsuarioDataEntity.toByteArray());
    }


    public MessageEntity buildSolicitacaoDataHoraMessage(String text){
        byte [] data =  text.getBytes();

        return buildMessage((byte) 0xA3,data);
    }
}


