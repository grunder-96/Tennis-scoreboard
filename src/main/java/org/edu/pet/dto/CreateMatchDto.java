package org.edu.pet.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.edu.pet.validation.hibernate.NotSame;

@Value
@AllArgsConstructor(staticName = "of")
@NotSame(firstFieldPath = "firstPlayer.name", secondFieldPath = "secondPlayer.name")
public class CreateMatchDto {

    @Valid
    @NotNull
    PlayerDto firstPlayer;

    @Valid
    @NotNull
    PlayerDto secondPlayer;
}