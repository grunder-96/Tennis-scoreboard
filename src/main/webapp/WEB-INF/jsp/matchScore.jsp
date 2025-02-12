<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Match score</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/normalize.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
    </head>
    <body>
        <%@ include file="header.jspf" %>
        <main class="main">
            <div class="container">
                <div class="content-container">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Sets</th>
                                <th>Games</th>
                                <th>Points</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <p><c:out value="${matchScore.firstPlayer.name}"/></p>
                                </td>
                                <td>
                                    <p><c:out value="${matchScore.firstPlayer.playerScore.sets}"/></p>
                                </td>
                                <td>
                                    <p><c:out value="${matchScore.firstPlayer.playerScore.games}"/></p>
                                </td>
                                <td>
                                    <p><c:out value="${matchScore.firstPlayer.playerScore.points.view()}"/></p>
                                </td>
                                <td>
                                    <form class="score-increase-form" action="${pageContext.request.contextPath}/match-score?uuid=${param.uuid}" method="POST">
                                        <input type="hidden" name="winnerId" value="${matchScore.firstPlayer.id}">
                                        <button class="score-increase-form-button" type="submit"></button>
                                    </form>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <p><c:out value="${matchScore.secondPlayer.name}"/></p>
                                </td>
                                <td>
                                    <p><c:out value="${matchScore.secondPlayer.playerScore.sets}"/></p>
                                </td>
                                <td>
                                    <p><c:out value="${matchScore.secondPlayer.playerScore.games}"/></p>
                                </td>
                                <td>
                                    <p><c:out value="${matchScore.secondPlayer.playerScore.points.view()}"/></p>
                                </td>
                                <td>
                                    <form class="score-increase-form" action="${pageContext.request.contextPath}/match-score?uuid=${param.uuid}" method="POST">
                                        <input type="hidden" name="winnerId" value="${matchScore.secondPlayer.id}">
                                        <button class="score-increase-form-button" type="submit"></button>
                                    </form>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </main>
        <%@ include file="footer.jspf" %>
    </body>
</html>