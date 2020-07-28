package repositories;

import entities.InfoUsuarioMessageEntity;
import entities.MessageEntity;
import entities.SolicitacaoDataHoraMessageEntity;
import entities.TextoMessageEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageRepositoryTest {
    MessageEntity expectedTextoMessageEntity = new MessageEntity((byte) 0x0A ,
            (byte)0x10,
            (byte)0xA1,
            new byte[]{0x48,0x65,0x6C,0x6C,0x6F,0x20,0x57,0x6F,0x72,0x6C,0x64},
            (byte)0xDC,
            (byte)0x0D);

    MessageEntity expectedInfoUsuarioMessageEntity = new MessageEntity((byte) 0x0A ,
            (byte)0x15,
            (byte)0xA2,
            new byte[]{0x20,0x7A,(byte)0xC3,0x0C,0x4D,0x69,0x63,0x68,0x65,0x6C,0x20,0x52,0x65,0x69,0x70,0x73},
            (byte)0x16,
            (byte)0x0D);

    MessageEntity expectedSolicitacaoDataHoraMessageEntity = new MessageEntity((byte) 0x0A ,
            (byte)0x16,
            (byte)0xA3,
            new byte[]{0x41,0x6D,0x65,0x72,0x69,0x63,0x61,0x2F,0x53,0x61,0x6F,0x5F,0x50,0x61,0x75,0x6C,0x6F},
            (byte)0xCD,
            (byte)0x0D);

    @Test
    void saveMessage() {
        MessageRepository messageRepository = new MessageRepository();

        //CREATE OBJECTS
        TextoMessageEntity textoMessageEntity =  new TextoMessageEntity().fromMessageEntity(expectedTextoMessageEntity);
        textoMessageEntity.setTexto(expectedTextoMessageEntity.getData());

        InfoUsuarioMessageEntity  infoUsuarioMessageEntity =  new InfoUsuarioMessageEntity().fromMessageEntity(expectedInfoUsuarioMessageEntity);
        infoUsuarioMessageEntity.setInfoUsuarioEntity(expectedInfoUsuarioMessageEntity.getData());

        SolicitacaoDataHoraMessageEntity  solicitacaoDataHoraMessageEntity =  new SolicitacaoDataHoraMessageEntity()
                .fromMessageEntity(expectedSolicitacaoDataHoraMessageEntity);
        solicitacaoDataHoraMessageEntity.setTimezone(expectedSolicitacaoDataHoraMessageEntity.getData());


        //SAVE IN DATABASE
        messageRepository.saveMessage(textoMessageEntity);
        messageRepository.saveMessage(infoUsuarioMessageEntity);
        messageRepository.saveMessage(solicitacaoDataHoraMessageEntity);


        //RETRIEVE FROM DATABASE
        //AND COMPARE RESULTS
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            assertEquals(textoMessageEntity.toString() , session.get(TextoMessageEntity.class, textoMessageEntity.getId()).toString());
            assertEquals(infoUsuarioMessageEntity.toString(), session.get(InfoUsuarioMessageEntity.class, infoUsuarioMessageEntity.getId()).toString());
            assertEquals(solicitacaoDataHoraMessageEntity.toString(), session.get(SolicitacaoDataHoraMessageEntity.class,solicitacaoDataHoraMessageEntity.getId()).toString());

        } catch (Exception e) {
            fail();
        }




    }
}