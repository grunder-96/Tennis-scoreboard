package org.edu.pet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.edu.pet.dto.CreateMatchDto;
import org.edu.pet.dto.PlayerDto;
import org.edu.pet.service.OngoingMatchesService;
import org.edu.pet.util.JspHelper;
import org.edu.pet.validation.NameFormatter;
import org.edu.pet.validation.ValidationErrorFormatter;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchController extends HttpServlet {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    private final OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("createNewMatch")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstPlayerName = new NameFormatter(req.getParameter("firstPlayerName"))
                .removeExtraSpaces()
                .capitalizeFirstLetter()
                .getName();

        String secondPlayerName = new NameFormatter(req.getParameter("secondPlayerName"))
                .removeExtraSpaces()
                .capitalizeFirstLetter()
                .getName();

        CreateMatchDto createMatchDto = CreateMatchDto.of(
                PlayerDto.of(firstPlayerName),
                PlayerDto.of(secondPlayerName)
        );

        Set<ConstraintViolation<CreateMatchDto>> constraintViolations = validator.validate(createMatchDto);

        if (!constraintViolations.isEmpty()) {
            Map<String, Map<String, String>> violations = ValidationErrorFormatter.format(constraintViolations);
            req.setAttribute("violations", violations);
            req.setAttribute("firstPlayerName", firstPlayerName);
            req.setAttribute("secondPlayerName", secondPlayerName);
            req.getRequestDispatcher(JspHelper.getPath("createNewMatch")).forward(req, resp);
        } else {
            UUID uuid = ongoingMatchesService.createMathScore(createMatchDto);
            resp.sendRedirect("%s/match-score?uuid=%s".formatted(req.getContextPath(), uuid.toString()));
        }
    }
}