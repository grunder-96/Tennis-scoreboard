package org.edu.pet.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.edu.pet.exception.ParamNotFoundException;

import java.util.function.Function;

@UtilityClass
public class RequestParamUtil {

    public String getParamValue(HttpServletRequest req, String paramName) {
        String paramString = req.getParameter(paramName);

        if (paramString == null) {
            throw new ParamNotFoundException("parameter %s not found".formatted(paramName));
        }

        return paramString.strip();
    }

    public <T> T convertParamValue(HttpServletRequest req, String paramName, Function<String, T> convertFunction) {

        String paramValue = getParamValue(req, paramName);

        try {
            return convertFunction.apply(paramValue);
        } catch(Exception e) {
            throw new IllegalArgumentException("An error occurred during the converting process %s parameter".formatted(paramName));
        }
    }
}