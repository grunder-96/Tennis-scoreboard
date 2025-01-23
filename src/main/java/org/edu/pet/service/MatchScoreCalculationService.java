package org.edu.pet.service;

import org.edu.pet.model.match.*;
import org.edu.pet.model.match.enums.RallyResult;
import org.edu.pet.model.match.enums.RegularPointEnum;

import java.util.Map;

import static org.edu.pet.model.match.enums.RallyResult.LOSE_POINT;
import static org.edu.pet.model.match.enums.RallyResult.WON_POINT;

public class MatchScoreCalculationService {

    private static final int WIN_SETS_FOR_VICTORY = 2;
    private static final int MIN_GAMES_TO_SET_OVER = 6;
    private static final int MIN_POINTS_TO_TIEBREAK_OVER = 7;
    private static final int REGULAR_DIFFERENCE_TO_OVER = 2;
    private static final int GAMES_DIFFERENCE_TO_TIEBREAK_OVER = 1;

    public void calculate(MatchScore matchScore, int winnerId) {
        Map<RallyResult, MatchPlayer> rallyResultMap = formRallyResultMap(matchScore, winnerId);

        PlayerScore pointWinnerScore = rallyResultMap.get(WON_POINT).getPlayerScore();
        PlayerScore pointLoserScore = rallyResultMap.get(LOSE_POINT).getPlayerScore();

        Points winnerPoints = pointWinnerScore.getPoints();
        Points loserPoints = pointLoserScore.getPoints();

        awardPoint(winnerPoints, loserPoints);

        if (isGameOver(winnerPoints, loserPoints)) {
            pointWinnerScore.setGames(pointWinnerScore.getGames() + 1);
            resetPoints(pointWinnerScore, pointLoserScore);
        }

        if (isSetOver(pointWinnerScore, pointLoserScore)) {
            pointWinnerScore.setSets(pointWinnerScore.getSets() + 1);
            resetGames(pointWinnerScore, pointLoserScore);
        }

        if (isMatchFinished(pointWinnerScore, pointLoserScore)) {
            matchScore.setWinner(rallyResultMap.get(WON_POINT));
        }
    }

    private void awardPoint(Points winnerPoints, Points loserPoints) {
        if (isSpecificRegularPoint(winnerPoints, RegularPointEnum.FORTY)) {
            handleForty(winnerPoints, loserPoints);
            return;
        }

        incrementPointScore(winnerPoints);
    }

    private void handleForty(Points winnerPoints, Points loserPoints) {
        if (isSpecificRegularPoint(loserPoints, RegularPointEnum.FORTY)) {
            incrementPointScore(winnerPoints);
            return;
        }

        if (isSpecificRegularPoint(loserPoints, RegularPointEnum.ADVANTAGE)) {
            loserPoints.setCurrent(RegularPointEnum.FORTY.getPointValue());
            return;
        }

        winnerPoints.setCurrent(RegularPointEnum.WON.getPointValue());
    }

    private boolean isRegularPoints(Points playerPoints) {
        return playerPoints instanceof RegularPoints;
    }

    private boolean isSpecificRegularPoint(Points playerPoints, RegularPointEnum regularPoint) {
        return isRegularPoints(playerPoints)
                && playerPoints.current() == regularPoint.getPointValue();
    }

    private void incrementPointScore(Points playerPoints) {
        int nextPoint = playerPoints.next();
        playerPoints.setCurrent(nextPoint);
    }

    private boolean isGameOver(Points firstPlayerPoints, Points secondPlayerPoints) {
    return isSpecificRegularPoint(firstPlayerPoints, RegularPointEnum.WON)
            || isSpecificRegularPoint(secondPlayerPoints, RegularPointEnum.WON)
            || isTiebreakOver(firstPlayerPoints, secondPlayerPoints);
    }

    private boolean isTiebreakOver(Points firstPlayerPoints, Points secondPlayerPoints) {
        return Math.abs(firstPlayerPoints.current() - secondPlayerPoints.current()) >= REGULAR_DIFFERENCE_TO_OVER
                && Math.max(firstPlayerPoints.current(), secondPlayerPoints.current()) >= MIN_POINTS_TO_TIEBREAK_OVER;
    }

    private void resetPoints(PlayerScore firstPlayerScore, PlayerScore secondPlayerScore) {
        if (isTiebreak(firstPlayerScore, secondPlayerScore) && isRegularPoints(firstPlayerScore.getPoints())) {
            firstPlayerScore.setPoints(new TiebreakPoints());
            secondPlayerScore.setPoints(new TiebreakPoints());
            return;
        }

        firstPlayerScore.setPoints(new RegularPoints());
        secondPlayerScore.setPoints(new RegularPoints());
    };

    private boolean isTiebreak(PlayerScore firstPlayerScore, PlayerScore secondPlayerScore) {
        return firstPlayerScore.getGames() == MIN_GAMES_TO_SET_OVER &&
                secondPlayerScore.getGames() == MIN_GAMES_TO_SET_OVER;
    }

    private boolean isSetOver(PlayerScore firstPlayerScore, PlayerScore secondPlayerScore) {
        return isRegularSetOver(firstPlayerScore, secondPlayerScore)
                || isSetWithTiebreakOver(firstPlayerScore, secondPlayerScore);
    }

    private boolean isRegularSetOver(PlayerScore firstPlayerScore, PlayerScore secondPlayerScore) {
        return Math.max(firstPlayerScore.getGames(), secondPlayerScore.getGames()) >= MIN_GAMES_TO_SET_OVER
                && Math.abs(firstPlayerScore.getGames() - secondPlayerScore.getGames()) >= REGULAR_DIFFERENCE_TO_OVER;
    }

    private boolean isSetWithTiebreakOver(PlayerScore firstPlayerScore, PlayerScore secondPlayerScore) {
        return Math.min(firstPlayerScore.getGames(), secondPlayerScore.getGames()) >= MIN_GAMES_TO_SET_OVER
                && Math.abs(firstPlayerScore.getGames() - secondPlayerScore.getGames()) >= GAMES_DIFFERENCE_TO_TIEBREAK_OVER;
    }

    private void resetGames(PlayerScore firstPlayerScore, PlayerScore secondPlayerScore) {
        firstPlayerScore.setGames(0);
        secondPlayerScore.setGames(0);
        resetPoints(firstPlayerScore, secondPlayerScore);
    }

    private boolean isMatchFinished(PlayerScore firstPlayerScore, PlayerScore secondPlayerScore) {
        return Math.max(firstPlayerScore.getSets(), secondPlayerScore.getSets()) >= WIN_SETS_FOR_VICTORY;
    }

    private Map<RallyResult, MatchPlayer> formRallyResultMap(MatchScore matchScore, int winnerId) {
        MatchPlayer maybePontWinner = matchScore.getFirstPlayer();
        MatchPlayer maybePontLoser = matchScore.getSecondPlayer();

        boolean isFound = maybePontWinner.getId() == winnerId;

        if (maybePontLoser.getId() == winnerId) {
            MatchPlayer temp = maybePontWinner;
            maybePontWinner = maybePontLoser;
            maybePontLoser = temp;
            isFound = true;
        }

        if (isFound) {
            return Map.of(WON_POINT, maybePontWinner, LOSE_POINT, maybePontLoser);
        }

        throw new IllegalArgumentException("An incorrect id was provided for this match.");
    }
}