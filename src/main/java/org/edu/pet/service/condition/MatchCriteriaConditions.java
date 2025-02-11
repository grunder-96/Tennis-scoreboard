package org.edu.pet.service.condition;

import lombok.experimental.UtilityClass;
import org.edu.pet.model.entity.Match;

@UtilityClass
public class MatchCriteriaConditions {

    public CriteriaCondition<Match> hasParticipantWithNamePattern(String namePattern) {
        return (root, cb) -> namePattern == null || namePattern.isBlank() ?
            null : cb.or(
                cb.like(cb.lower(root.get("player1").get("name")), "%%%s%%".formatted(namePattern).toLowerCase()),
                cb.like(cb.lower(root.get("player2").get("name")), "%%%s%%".formatted(namePattern).toLowerCase())
            );
    }
}