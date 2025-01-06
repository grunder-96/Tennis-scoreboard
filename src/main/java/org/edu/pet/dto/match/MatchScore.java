package org.edu.pet.dto.match;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MatchScore {

    private PlayerScore firstPlayerScore;

    private PlayerScore secondPlayerScore;
}