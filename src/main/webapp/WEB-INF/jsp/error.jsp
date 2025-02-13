<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Error</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/normalize.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
    </head>
    <body>
        <%@ include file="header.jspf" %>

        <main class="main">
            <div class="container">
                <div class="column-content-container">
                    <h1>HTTP Status ${requestScope['jakarta.servlet.error.status_code']}</h1>
                    <h3>${requestScope['jakarta.servlet.error.message']}</h3>
                </div>
            </div>
        </main>

        <%@ include file="footer.jspf" %>
    </body>
</html>
