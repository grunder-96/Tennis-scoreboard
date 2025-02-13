package org.edu.pet;

import org.edu.pet.model.match.*;
import org.edu.pet.model.match.enums.RegularPointEnum;
import org.edu.pet.service.MatchScoreCalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.edu.pet.model.match.enums.RegularPointEnum.*;
import static org.junit.jupiter.api.Assertions.*;

public class MatchScoreCalculationTest {

    private MatchScoreCalculationService service = new MatchScoreCalculationService();

    private MatchScore matchScore;

    private MatchPlayer firstPlayer;
    private MatchPlayer secondPlayer;

    @BeforeEach
    public void setUp() {
        firstPlayer = MatchPlayer.builder()
                .id(1)
                .build();

        secondPlayer = MatchPlayer.builder()
                .id(2)
                .build();

        matchScore = MatchScore.builder()
                .firstPlayer(firstPlayer)
                .secondPlayer(secondPlayer)
                .build();
    }

    @Test
    @DisplayName("If player 1 wins a point at a score of 40-40, the game does not end")
    void shouldNotEndGameWhenScoreIsDeuceAndPlayerWinsPoint() {
        firstPlayer.setPlayerScore(configurePlayerScore(0, 1, FORTY));
        secondPlayer.setPlayerScore(configurePlayerScore(0, 2, FORTY));

        service.calculate(matchScore, firstPlayer.getId());

        assertEquals(1, firstPlayer.getPlayerScore().getGames());
    }

    @Test
    @DisplayName("if player 1 wins a point at a score of 40-0, then he/she wins the game")
    void shouldEndGameWithFortyZeroScore() {
        firstPlayer.setPlayerScore(configurePlayerScore(0, 1, lOVE));
        secondPlayer.setPlayerScore(configurePlayerScore(0, 2, FORTY));

        service.calculate(matchScore, secondPlayer.getId());

        assertEquals(3, secondPlayer.getPlayerScore().getGames());
    }

    @Test
    @DisplayName("At a score of 6-6, a tiebreak starts instead of a regular game")
    void shouldBeTiebreakWhenGameScoreIsSixSix() {
        firstPlayer.setPlayerScore(configurePlayerScore(0, 6, lOVE));
        secondPlayer.setPlayerScore(configurePlayerScore(0, 5, FORTY));

        service.calculate(matchScore, secondPlayer.getId());

        assertAll(
                () -> assertEquals(TiebreakPoints.class, firstPlayer.getPlayerScore().getPoints().getClass()),
                () -> assertEquals(TiebreakPoints.class, secondPlayer.getPlayerScore().getPoints().getClass()),
                () -> assertEquals(firstPlayer.getPlayerScore().getPoints().view(), "0"),
                () -> assertEquals(firstPlayer.getPlayerScore().getPoints().view(), "0")
        );
    }

    @Test
    void shouldEndMatchWhenPlayerWinsTwoSets() {
        firstPlayer.setPlayerScore(configurePlayerScore(1, 6, new TiebreakPoints(7)));
        secondPlayer.setPlayerScore(configurePlayerScore(1, 6, new TiebreakPoints(6)));

        service.calculate(matchScore, firstPlayer.getId());

        assertAll(
                () -> assertTrue(Objects.nonNull(matchScore.getWinner())),
                () -> assertEquals(2, firstPlayer.getPlayerScore().getSets())
        );
    }

    @Test
    @DisplayName("The score should become 40-40 if a player wins a point at 40-AD")
    void shouldResetScoreToDeuceAfterAdvantage() {
        firstPlayer.setPlayerScore(configurePlayerScore(1, 4, ADVANTAGE));
        secondPlayer.setPlayerScore(configurePlayerScore(1, 2, FORTY));

        service.calculate(matchScore, secondPlayer.getId());

        assertAll(
                () -> assertEquals(firstPlayer.getPlayerScore().getPoints().current(), FORTY.getPointValue()),
                () -> assertEquals(secondPlayer.getPlayerScore().getPoints().current(), FORTY.getPointValue())
        );
    }

    @Test
    void shouldNotEndSetWhenGameScoreIsSixFive() {
        firstPlayer.setPlayerScore(configurePlayerScore(1, 5, THIRTY));
        secondPlayer.setPlayerScore(configurePlayerScore(0, 5, FORTY));

        service.calculate(matchScore, secondPlayer.getId());

        assertAll(
                () -> assertEquals(firstPlayer.getPlayerScore().getGames(), 5),
                () -> assertEquals(secondPlayer.getPlayerScore().getGames(), 6),
                () -> assertEquals(firstPlayer.getPlayerScore().getSets(), 1),
                () -> assertEquals(secondPlayer.getPlayerScore().getSets(), 0)
        );
    }

    PlayerScore configurePlayerScore(int sets, int games, RegularPointEnum points) {
        return PlayerScore.builder()
                .sets(sets)
                .games(games)
                .points(new RegularPoints(points.getPointValue()))
                .build();
    }

    PlayerScore configurePlayerScore(int sets, int games, TiebreakPoints points) {
        return PlayerScore.builder()
                .sets(sets)
                .games(games)
                .points(points)
                .build();
    }
}