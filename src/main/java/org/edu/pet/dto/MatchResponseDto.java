package org.edu.pet.dto;

import lombok.Value;

@Value
public class MatchResponseDto {

    String firstPlayerName;
    String secondPlayerName;
    String winner;
}