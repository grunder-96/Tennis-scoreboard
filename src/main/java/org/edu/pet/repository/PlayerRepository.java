package org.edu.pet.repository;

import org.edu.pet.model.entity.Player;

import java.util.Optional;

public interface PlayerRepository extends SharedRepository<Player> {

    Optional<Player> findByPlayerName(String playerName);
}