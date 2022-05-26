package com.icebear.stream.dao;

import com.icebear.stream.entity.db.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;


@Repository
// Repository: annotate it is a component and also a Data Access Object
public class RegisterDao {

    @Autowired
    private SessionFactory sessionFactory;

    public boolean register(User user) {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            // put operations under transaction,
            // so if error occurs, we can still rollback to keep the consistency of data.
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (PersistenceException | IllegalStateException ex) {
            // if hibernate throws this exception, it means the user already be register
            ex.printStackTrace();
            // rollback: undo the previous operation.
            session.getTransaction().rollback();
            return false;
        } finally {
            if (session != null){
                session.close();
            }
        }
        return true;
    }
}


