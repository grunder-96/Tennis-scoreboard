package org.edu.pet.repository;

import org.edu.pet.model.entity.Match;
import org.edu.pet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MatchRepository {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Match save(Match match) {
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        session.persist(match);

        session.getTransaction().commit();

        return match;
    }
}
