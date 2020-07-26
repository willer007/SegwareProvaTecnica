package handlers;

import crccalc.CrcCalculator;
import entities.InvalidMessageEntity;
import entities.MessageEntity;
import factories.AckFactory;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MessageHandler_INVALID implements IMessageHandler<InvalidMessageEntity> {

    @Override
    public InvalidMessageEntity handleMessage(MessageEntity message, OutputStream out) throws IOException {
        IOUtils.write(AckFactory.createAckA0().toByteArray(), out);
        InvalidMessageEntity invalidMessageEntity = new InvalidMessageEntity().fromMessageEntity(message);
        invalidMessageEntity.setTexto("Erro no servidor");
        return  invalidMessageEntity;
    }
}
