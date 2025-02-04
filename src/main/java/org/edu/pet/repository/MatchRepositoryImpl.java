package org.edu.pet.repository;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.edu.pet.model.entity.Match;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchRepositoryImpl extends SharedRepositoryImpl<Match> implements MatchRepository {

    private static final MatchRepositoryImpl INSTANCE = new MatchRepositoryImpl();

    public static MatchRepositoryImpl getInstance() {
        return INSTANCE;
    }
}