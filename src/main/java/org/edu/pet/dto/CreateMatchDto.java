package org.edu.pet.dto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.edu.pet.validation.hibernate.NotSame;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@NotSame(firstFieldPath = "firstPlayer.name", secondFieldPath = "secondPlayer.name")
public class CreateMatchDto {

    @Valid
    private PlayerDto firstPlayer;

    @Valid
    private PlayerDto secondPlayer;
}