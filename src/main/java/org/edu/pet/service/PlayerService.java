package org.edu.pet.service;

import org.edu.pet.dto.PlayerDto;
import org.edu.pet.model.entity.Player;
import org.edu.pet.mapper.PlayerMapper;
import org.edu.pet.repository.PlayerRepository;

import java.util.Optional;

public class PlayerService {

    private final PlayerRepository playerRepository = PlayerRepository.getInstance();

    public Player findOrSave(PlayerDto playerDto) {
        Optional<Player> maybePlayer = playerRepository.findByPlayerName(playerDto.getName());

        return maybePlayer.orElseGet(() -> playerRepository.save(
                PlayerMapper.INSTANCE.fromPlayerDto(playerDto))
        );
    }
}