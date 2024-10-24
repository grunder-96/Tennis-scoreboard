package org.edu.pet.util;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.edu.pet.entity.Match;
import org.edu.pet.entity.Player;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {

    @Getter
    private SessionFactory sessionFactory;

    static {
        sessionFactory = buildSessionFactory();
    }

    private SessionFactory buildSessionFactory() {
        try {
            return new Configuration()
                    .addAnnotatedClass(Player.class)
                    .addAnnotatedClass(Match.class)
                    .buildSessionFactory();
        } catch (HibernateException e) {
            throw new RuntimeException("Error initializing database connection");
        }
    }

    public void closeSessionFactory() {
        sessionFactory.close();
    }
}