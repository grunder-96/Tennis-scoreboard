package org.edu.pet.repository;

import org.edu.pet.model.entity.Player;
import org.edu.pet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

public class PlayerRepositoryImpl implements PlayerRepository {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Optional<Player> findByPlayerName(String playerName) {
        Session session = sessionFactory.getCurrentSession();

        Transaction transaction = session.beginTransaction();

        Optional<Player> maybePlayer = session
                .createQuery("select p from Player p  where p.name = :name", Player.class)
                .setParameter("name", playerName)
                .uniqueResultOptional();

        session.getTransaction().commit();

        return maybePlayer;
    }

    @Override
    public Player save(Player player) {
        Session session = sessionFactory.getCurrentSession();

        Transaction transaction = session.beginTransaction();

        session.persist(player);

        session.getTransaction().commit();

        return player;
    }
}