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
       try {

           //CREATE SPECIFIC MESSAGE
           SolicitacaoDataHoraMessageEntity solicitacaoDataHoraMessageEntity =
                   new SolicitacaoDataHoraMessageEntity().fromMessageEntity(message);

           solicitacaoDataHoraMessageEntity.setTimezone(message.getData());

           //PROCESS DATE
           Instant now = Instant.now();
           ZonedDateTime date = now.atZone(ZoneId.of(solicitacaoDataHoraMessageEntity.getTimezone()));

           //WRITE RESPONSE
           IOUtils.write(AckFactory.createAckA3(date).toByteArray(), out);

           return solicitacaoDataHoraMessageEntity;

       }catch (Exception e){

           //IF FAIL RETURN ACK0
           IOUtils.write(AckFactory.createAckA0().toByteArray(), out);

           SolicitacaoDataHoraMessageEntity solicitacaoDataHoraMessageEntity =
                   new SolicitacaoDataHoraMessageEntity().fromMessageEntity(message);
           solicitacaoDataHoraMessageEntity.setTimezone(message.getData());

           return solicitacaoDataHoraMessageEntity;

       }
    }
}
