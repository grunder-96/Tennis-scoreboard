package org.edu.pet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.edu.pet.model.match.MatchScore;
import org.edu.pet.service.MatchScoreCalculationService;
import org.edu.pet.service.OngoingMatchesService;
import org.edu.pet.util.JspHelper;
import org.edu.pet.util.RequestParamUtil;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreController extends HttpServlet {

    private static final String UUID_PARAM_NAME = "uuid";
    private static final String MATCH_SCORE_PARAM_NAME = "matchScore";
    private static final String ID_WINNER_PARAM_NAME = "winnerId";

    private final OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
    private final MatchScoreCalculationService calculationService = new MatchScoreCalculationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UUID uuid = RequestParamUtil.convertParamValue(req, UUID_PARAM_NAME, UUID::fromString);
        MatchScore matchScore = ongoingMatchesService.get(uuid);

        req.setAttribute(MATCH_SCORE_PARAM_NAME, matchScore);
        req.getRequestDispatcher(JspHelper.getPath("matchScore")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UUID uuid = RequestParamUtil.convertParamValue(req, UUID_PARAM_NAME, UUID::fromString);
        MatchScore matchScore = ongoingMatchesService.get(uuid);
        int winnerId = RequestParamUtil.convertParamValue(req, ID_WINNER_PARAM_NAME, Integer::parseInt);

        calculationService.calculate(matchScore, winnerId);

        if (matchScore.getWinner() == null) {
            resp.sendRedirect("%s/match-score?uuid=%s".formatted(req.getContextPath(), uuid));
            return;
        }

        resp.sendRedirect("%s/".formatted(req.getContextPath()));
    }
}