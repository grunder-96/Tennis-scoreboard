package org.edu.pet.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.edu.pet.exception.OngoingMatchNotFoundException;
import org.edu.pet.exception.ParamNotFoundException;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;

@WebFilter(urlPatterns = "/*")
public class ExceptionFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(req, res);
        } catch(IllegalArgumentException | ParamNotFoundException e) {
            res.sendError(SC_BAD_REQUEST, e.getMessage());
        } catch(OngoingMatchNotFoundException e) {
            res.sendError(SC_NOT_FOUND, e.getMessage());
        }
    }
}