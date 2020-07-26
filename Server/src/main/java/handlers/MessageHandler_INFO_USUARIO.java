package handlers;

import entities.InfoUsuarioEntity;
import entities.MessageEntity;
import factories.AckFactory;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.OutputStream;



public class MessageHandler_INFO_USUARIO implements IMessageHandler {

    @Override
    public MessageEntity handleMessage(MessageEntity message, OutputStream out) throws IOException {
        IOUtils.write(AckFactory.createAckA0().toByteArray(), out);
        return  message;
    }
}
