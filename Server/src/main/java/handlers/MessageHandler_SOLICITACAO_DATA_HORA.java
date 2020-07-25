package handlers;

import entities.MessageEntity;
import factories.AckFactory;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.TimeZone;

public class MessageHandler_SOLICITACAO_DATA_HORA  implements IMessageHandler {
    @Override
    public MessageEntity handleMessage(MessageEntity message, OutputStream out) throws IOException {



        Instant now = Instant.now();
        ZonedDateTime date = now.atZone(ZoneId.of(new String(message.getMessageDATA())));
        IOUtils.write(AckFactory.createAckA3(date).toByteArray(), out);



        return message;
    }
}
