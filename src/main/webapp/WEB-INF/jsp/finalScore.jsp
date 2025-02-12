<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Final score</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/normalize.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
    </head>
    <body>
        <%@ include file="header.jspf" %>

        <main class="main">
            <div class="container">
                <div class="column-content-container">
                    <c:set var="winner" value="${matchScore.winner}" />
                    <c:set var="loser" value="${(winner.name == matchScore.firstPlayer.name) ?
                        matchScore.secondPlayer : matchScore.firstPlayer}" />
                    <h1>Match is finished!</h1>
                    <br>
                    <h3>${winner.name} defeats ${loser.name} with a score of ${winner.playerScore.sets}-${loser.playerScore.sets} in sets.</h3>
                </div>
            </div>
        </main>

        <%@ include file="footer.jspf" %>
    </body>
</html>
