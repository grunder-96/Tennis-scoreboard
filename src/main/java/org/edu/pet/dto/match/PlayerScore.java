package org.edu.pet.dto.match;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.edu.pet.entity.Player;

@Data
@AllArgsConstructor
@Builder
public class PlayerScore {

    private Player player;

    @Builder.Default
    private int sets = 0;

    @Builder.Default
    private int games = 0;

    @Builder.Default
    private Points points = Points.lOVE;
}