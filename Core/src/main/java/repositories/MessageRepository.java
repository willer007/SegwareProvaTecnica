package repositories;

import entities.MessageEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtils;

public class MessageRepository <T>{

    public boolean saveMessage(T messageEntity){
        Transaction transaction = null;

        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.save(messageEntity);
            // commit transaction
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
