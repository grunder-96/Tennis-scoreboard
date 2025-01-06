package org.edu.pet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.edu.pet.dto.match.MatchScore;
import org.edu.pet.exception.ParamNotFoundException;
import org.edu.pet.service.OngoingMatchesService;
import org.edu.pet.util.JspHelper;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreController extends HttpServlet {

    private static final String UUID_PARAM_NAME = "uuid";

    private final OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UUID uuid = getUuidFromParam(req);

        MatchScore matchScore = ongoingMatchesService.getMatchScore(uuid);

        req.getSession().setAttribute("score", matchScore);
        req.getRequestDispatcher(JspHelper.getPath("matchScore")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


    private String getParamValue(HttpServletRequest req, String paramName) {
        String paramString = req.getParameter(paramName);

        if (paramString == null || paramString.isBlank()) {
            throw new ParamNotFoundException("parameter %s not found or its value is blank".formatted(paramName));
        }

        return paramString.strip();
    }

    private UUID getUuidFromParam(HttpServletRequest req) {
        String paramString = getParamValue(req, UUID_PARAM_NAME);

        UUID uuid;

        try {
            uuid = UUID.fromString(paramString);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The provided parameter is not a UUID");
        }

        return uuid;
    }
}