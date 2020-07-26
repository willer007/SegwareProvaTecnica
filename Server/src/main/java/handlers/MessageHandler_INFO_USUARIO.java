package handlers;

import entities.InfoUsuarioEntity;
import entities.InfoUsuarioMessageEntity;
import entities.MessageEntity;
import factories.AckFactory;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.OutputStream;



public class MessageHandler_INFO_USUARIO implements IMessageHandler<InfoUsuarioMessageEntity> {

    @Override
    public InfoUsuarioMessageEntity handleMessage(MessageEntity message, OutputStream out) throws IOException {

        IOUtils.write(AckFactory.createAckA0().toByteArray(), out);
        InfoUsuarioMessageEntity infoUsuarioMessageEntity = new InfoUsuarioMessageEntity().fromMessageEntity(message);
        infoUsuarioMessageEntity.setInfoUsuarioEntity( new InfoUsuarioEntity(message.getData()));
        return  infoUsuarioMessageEntity;
    }
}
