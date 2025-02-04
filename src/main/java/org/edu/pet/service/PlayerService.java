package org.edu.pet.service;

import org.edu.pet.dto.PlayerDto;
import org.edu.pet.model.entity.Player;
import org.edu.pet.mapper.PlayerMapper;
import org.edu.pet.repository.PlayerRepositoryImpl;

import java.util.Optional;

public class PlayerService {

    private final PlayerRepositoryImpl playerRepositoryImpl = PlayerRepositoryImpl.getInstance();

    public Player findOrSave(PlayerDto playerDto) {
        Optional<Player> maybePlayer = playerRepositoryImpl.findByPlayerName(playerDto.getName());

        return maybePlayer.orElseGet(() -> playerRepositoryImpl.save(
                PlayerMapper.INSTANCE.fromPlayerDto(playerDto))
        );
    }
}