package org.edu.pet.mapper;

import org.edu.pet.dto.MatchResponseDto;
import org.edu.pet.model.entity.Match;
import org.edu.pet.model.match.MatchScore;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = PlayerMapper.class)
public interface MatchMapper {

    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(source = "firstPlayer", target = "player1"),
        @Mapping(source = "secondPlayer", target = "player2")
    })
    Match fromMatchScore(MatchScore matchScore);

    @Mappings({
        @Mapping(source = "player1.name", target = "firstPlayerName"),
        @Mapping(source = "player2.name", target = "secondPlayerName"),
        @Mapping(source = "winner.name", target = "winner")
    })
    MatchResponseDto fromMatch(Match match);

    List<MatchResponseDto> fromMatches(List<Match> matches);
}