package org.edu.pet.model.match;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchPlayer {

    private int id;

    private String name;

    private PlayerScore playerScore;
}