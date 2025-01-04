package org.edu.pet.mapper;

import org.edu.pet.dto.PlayerDto;
import org.edu.pet.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerMapper {

    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);
    
    Player fromPlayerDto(PlayerDto playerDto);
}