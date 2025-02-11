package org.edu.pet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.edu.pet.dto.PageDto;
import org.edu.pet.dto.SearchCriteriaDto;
import org.edu.pet.exception.ParamNotFoundException;
import org.edu.pet.service.FinishedMatchesPersistenceService;
import org.edu.pet.util.JspHelper;
import org.edu.pet.util.PaginationUtil;
import org.edu.pet.util.RequestParamUtil;

import java.io.IOException;

@WebServlet("/matches")
public class MatchesController extends HttpServlet {

    private static final String PAGE_VALUE_PARAM = "page";
    private static final String NAME_FILTER_PARAM = "filter_by_player_name";

    private final FinishedMatchesPersistenceService persistenceService = new FinishedMatchesPersistenceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int pageNumber;
        String namePattern;

        try {
            pageNumber = RequestParamUtil.convertParamValue(req, PAGE_VALUE_PARAM, Integer::parseInt);
        } catch(ParamNotFoundException e) {
            pageNumber = PaginationUtil.DEFAULT_PAGE_NUMBER;
        }

        try {
            namePattern = RequestParamUtil.getParamValue(req, NAME_FILTER_PARAM);
        } catch(ParamNotFoundException e) {
            namePattern = null;
        }

        SearchCriteriaDto searchCriteriaDto = SearchCriteriaDto.of(pageNumber, namePattern);

        PageDto pageDto = persistenceService.findMatchesBy(searchCriteriaDto);

        req.setAttribute("pageDto", pageDto);

        req.getRequestDispatcher(JspHelper.getPath("matches")).forward(req, resp);
    }
}