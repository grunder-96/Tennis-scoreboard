package org.edu.pet.service;

import org.edu.pet.dto.CreateMatchDto;
import org.edu.pet.model.match.MatchPlayer;
import org.edu.pet.model.match.MatchScore;
import org.edu.pet.model.entity.Player;
import org.edu.pet.exception.OngoingMatchNotFoundException;
import org.edu.pet.mapper.PlayerMapper;

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

        MatchPlayer firstPlayerInfo = PlayerMapper.INSTANCE.toMatchPlayer(firstPlayer);
        MatchPlayer secondPlayerInfo = PlayerMapper.INSTANCE.toMatchPlayer(secondPlayer);

        MatchScore matchScore = MatchScore.builder()
                .firstPlayer(firstPlayerInfo)
                .secondPlayer(secondPlayerInfo)
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