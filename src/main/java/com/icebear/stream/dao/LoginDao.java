package com.icebear.stream.dao;

import com.icebear.stream.entity.db.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDao {

    @Autowired
    private SessionFactory sessionFactory;

    // Verify if the given user_Id and password are correct. Returns the user_name when it passes
    public String verifyLogin(String userId, String password) {
        String name = "";

        //https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
        // try with resource can automatically close the resource.
        // so we can avoid writing finally{session.closed()}
        try (Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, userId);
            if(user != null && user.getPassword().equals((password))) {
                // both passwords are encrypted
                name = user.getFirstName();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return name;
    }
}
