package handlers;

import entities.MessageEntity;
import entities.TextoMessageEntity;
import factories.AckFactory;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.OutputStream;

public class MessageHandler_MENSAGEM_TEXTO implements IMessageHandler<TextoMessageEntity>{


    @Override
    public TextoMessageEntity handleMessage(MessageEntity message, OutputStream out) throws IOException {
        IOUtils.write(AckFactory.createAckA0().toByteArray(), out);
        TextoMessageEntity textoMessageEntity = new TextoMessageEntity().fromMessageEntity(message);
        textoMessageEntity.setTexto(new String(message.getData()));
        return  textoMessageEntity;
    }
}
