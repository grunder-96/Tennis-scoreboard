<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Tennis scoreboard</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/normalize.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
    </head>
    <body>
        <%@ include file="header.jspf" %>
        <main class="main">
            <div class="container">
                <div class="button-container">
                    <a class="button" href="${pageContext.request.contextPath}/new-match">new match</a>
                    <a class="button" href="${pageContext.request.contextPath}/matches">completed matches</a>
                </div>
            </div>
        </main>
        <%@ include file="footer.jspf" %>
    </body>
</html>