package org.edu.pet.repository;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.edu.pet.model.entity.Player;
import org.edu.pet.util.TransactionUtil;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerRepositoryImpl extends SharedRepositoryImpl<Player> implements PlayerRepository {

    private static final PlayerRepositoryImpl INSTANCE = new PlayerRepositoryImpl();

    @Override
    public Optional<Player> findByPlayerName(String playerName) {
        return TransactionUtil.executeInTransaction(
                session -> session.createQuery("select p from Player p  where p.name = :name", Player.class)
                    .setParameter("name", playerName)
                    .uniqueResultOptional(),
                session
        );
    }

    public static PlayerRepositoryImpl getInstance() {
        return INSTANCE;
    }
}