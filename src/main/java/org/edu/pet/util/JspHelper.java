package org.edu.pet.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JspHelper {

    private final String JSP_FORMAT = "/WEB-INF/jsp/%s.jsp";

    public String getPath(String jspName) {
        return String.format(JSP_FORMAT, jspName);
    }
}