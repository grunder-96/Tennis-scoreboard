package org.edu.pet.mapper;

import org.edu.pet.dto.PlayerDto;
import org.edu.pet.model.entity.Player;
import org.edu.pet.model.match.MatchPlayer;
import org.edu.pet.model.match.PlayerScore;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(imports = PlayerScore.class)
public interface PlayerMapper {

    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    @Mapping(target = "id", ignore = true)
    Player fromPlayerDto(PlayerDto playerDto);

    Player fromMatchPlayer(MatchPlayer matchPlayer);

    @Mapping(target = "playerScore", expression = "java(new PlayerScore())")
    MatchPlayer toMatchPlayer(Player player);
}