package org.edu.pet.mapper;

import org.edu.pet.model.entity.Match;
import org.edu.pet.model.match.MatchScore;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = PlayerMapper.class)
public interface MatchMapper {

    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(source = "firstPlayer", target = "player1"),
        @Mapping(source = "secondPlayer", target = "player2")
    })
    Match fromMatchScore(MatchScore matchScore);
}