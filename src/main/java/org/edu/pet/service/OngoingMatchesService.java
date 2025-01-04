package org.edu.pet.service;

import org.edu.pet.dto.CreateMatchDto;
import org.edu.pet.entity.Match;
import org.edu.pet.entity.Player;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {

    private static final ConcurrentHashMap<UUID, Match> ONGOING_MATCHES = new ConcurrentHashMap<>();
    
    private final PlayerService playerService = new PlayerService();

    public UUID createMatch(CreateMatchDto createMatchDto) {
        Player firstPlayer = playerService.findOrSave(createMatchDto.getFirstPlayer());
        Player secondPlayer = playerService.findOrSave(createMatchDto.getSecondPlayer());

        UUID uuid = UUID.randomUUID();

        MatchScore
    }
}