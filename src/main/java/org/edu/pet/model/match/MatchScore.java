package org.edu.pet.model.match;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchScore {

    private MatchPlayer firstPlayer;

    private MatchPlayer secondPlayer;

    private MatchPlayer winner;
}