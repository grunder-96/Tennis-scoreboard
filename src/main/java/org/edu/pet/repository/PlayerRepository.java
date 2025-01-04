package org.edu.pet.repository;

import org.edu.pet.entity.Player;
import org.edu.pet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

public class PlayerRepository {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Optional<Player> findByPlayerName(String playerName) {
        Session session = sessionFactory.getCurrentSession();

        Transaction transaction = session.beginTransaction();

        Optional<Player> maybePlayer = session
                .createQuery("from Player where name = :name", Player.class)
                .setParameter("name", playerName)
                .uniqueResultOptional();

        session.getTransaction().commit();

        return maybePlayer;
    }

    public Player save(Player player) {
        Session session = sessionFactory.getCurrentSession();

        Transaction transaction = session.beginTransaction();

        session.persist(player);

        session.getTransaction().commit();

        return player;
    }
}