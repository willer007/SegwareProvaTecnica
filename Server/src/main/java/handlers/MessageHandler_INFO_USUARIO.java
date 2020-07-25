package handlers;

import crccalc.CrcCalculator;
import entities.InfoUsuarioDataEntity;
import entities.MessageEntity;
import factories.AckFactory;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;



public class MessageHandler_INFO_USUARIO implements IMessageHandler {

    @Override
    public MessageEntity handleMessage(MessageEntity message, OutputStream out) throws IOException {
        InfoUsuarioDataEntity infoUsuarioData = new InfoUsuarioDataEntity(message.getMessageDATA());
        System.out.println(Byte.toUnsignedInt(infoUsuarioData.getDataIDADE()));
        System.out.println(Byte.toUnsignedInt(infoUsuarioData.getDataPESO()));
        System.out.println(Byte.toUnsignedInt(infoUsuarioData.getDataALTURA()));
        System.out.println(Byte.toUnsignedInt(infoUsuarioData.getDataTAMANHO_NOME()));
        System.out.println(new String(infoUsuarioData.getDataNOME()));

        IOUtils.write(AckFactory.createAckA0().toByteArray(), out);


        return  message;
    }
}
