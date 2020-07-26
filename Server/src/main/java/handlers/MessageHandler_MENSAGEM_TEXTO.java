package handlers;

import crccalc.CrcCalculator;
import entities.MessageEntity;
import factories.AckFactory;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MessageHandler_MENSAGEM_TEXTO implements IMessageHandler{


    @Override
    public MessageEntity handleMessage(MessageEntity message, OutputStream out) throws IOException {
        IOUtils.write(AckFactory.createAckA0().toByteArray(), out);
        return  message;
    }
}
