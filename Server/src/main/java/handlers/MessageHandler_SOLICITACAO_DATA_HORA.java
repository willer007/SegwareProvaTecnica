package handlers;

import entities.MessageEntity;
import entities.SolicitacaoDataHoraMessageEntity;
import factories.AckFactory;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class MessageHandler_SOLICITACAO_DATA_HORA  implements IMessageHandler<SolicitacaoDataHoraMessageEntity> {
    @Override
    public SolicitacaoDataHoraMessageEntity handleMessage(MessageEntity message, OutputStream out) throws IOException {
        Instant now = Instant.now();
        ZonedDateTime date = now.atZone(ZoneId.of(new String(message.getData())));
        IOUtils.write(AckFactory.createAckA3(date).toByteArray(), out);

        SolicitacaoDataHoraMessageEntity solicitacaoDataHoraMessageEntity =
                new SolicitacaoDataHoraMessageEntity().fromMessageEntity(message);

        solicitacaoDataHoraMessageEntity.setTimezone(new String(message.getData()));

        return solicitacaoDataHoraMessageEntity;
    }
}
