package org.edu.pet.repository;

import org.edu.pet.model.entity.Player;
import org.edu.pet.util.TransactionUtil;

import java.util.Optional;

public class PlayerRepositoryImpl extends SharedRepositoryImpl<Player> implements PlayerRepository {

    @Override
    public Optional<Player> findByPlayerName(String playerName) {
        return TransactionUtil.executeInTransaction(
                session -> session.createQuery("select p from Player p  where p.name = :name", Player.class)
                    .setParameter("name", playerName)
                    .uniqueResultOptional(),
                session
        );
    }
}