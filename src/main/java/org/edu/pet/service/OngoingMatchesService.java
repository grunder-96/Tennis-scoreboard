package org.edu.pet.service;

import org.edu.pet.dto.CreateMatchDto;
import org.edu.pet.dto.match.MatchScore;
import org.edu.pet.dto.match.PlayerScore;
import org.edu.pet.entity.Player;
import org.edu.pet.exception.OngoingMatchNotFoundException;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {

    private static final ConcurrentHashMap<UUID, MatchScore> ONGOING_MATCHES = new ConcurrentHashMap<>();
    
    private final PlayerService playerService = new PlayerService();

    public UUID createMatch(CreateMatchDto createMatchDto) {
        Player firstPlayer = playerService.findOrSave(createMatchDto.getFirstPlayer());
        Player secondPlayer = playerService.findOrSave(createMatchDto.getSecondPlayer());

        UUID uuid = UUID.randomUUID();

        MatchScore matchScore = MatchScore.builder()
                .firstPlayerScore(
                        PlayerScore.builder()
                                .player(firstPlayer)
                                .build()
                )
                .secondPlayerScore(
                        PlayerScore.builder()
                                .player(secondPlayer)
                                .build()
                )
                .build();

        ONGOING_MATCHES.put(uuid, matchScore);

        return uuid;
    }

    public MatchScore getMatchScore(UUID uuid) {
        return Optional.ofNullable(ONGOING_MATCHES.get(uuid))
                .orElseThrow(() -> new OngoingMatchNotFoundException(
                        "Ongoing match with uuid %s not found".formatted(uuid)
                ));
    }
}