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
                <div class="form-container">
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
                                    <p><c:out value="${sessionScope.score.firstPlayerScore.player.name}"/></p>
                                </td>
                                <td>
                                    <p><c:out value="${sessionScope.score.firstPlayerScore.sets}"/></p>
                                </td>
                                <td>
                                    <p><c:out value="${sessionScope.score.firstPlayerScore.games}"/></p>
                                </td>
                                <td>
                                    <p><c:out value="${sessionScope.score.firstPlayerScore.points.value}"/></p>
                                </td>
                                <td>
                                    <form class="score-increase-form" action="/match-score" method="POST">
                                        <input type="hidden" name="id" value="${sessionScope.firstPlayerScore.player.id}">
                                        <button class="score-increase-form-button" type="submit"></button>
                                    </form>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <p><c:out value="${sessionScope.score.secondPlayerScore.player.name}"/></p>
                                </td>
                                <td>
                                    <p><c:out value="${sessionScope.score.secondPlayerScore.sets}"/></p>
                                </td>
                                <td>
                                    <p><c:out value="${sessionScope.score.secondPlayerScore.games}"/></p>
                                </td>
                                <td>
                                    <p><c:out value="${sessionScope.score.secondPlayerScore.points.value}"/></p>
                                </td>
                                <td>
                                    <form class="score-increase-form" action="/match-score" method="POST">
                                        <input type="hidden" name="id" value="${sessionScope.secondPlayerScore.player.id}">
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