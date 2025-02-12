<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Create new match</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/normalize.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
    </head>
    <body>
        <%@ include file="header.jspf" %>
        <main class="main">
            <div class="container">
                <div class="content-container">
                    <form class="form" action="${pageContext.request.contextPath}/new-match"method="POST">
                        <div class="form-group">
                            <label for="firstPlayerId">First player name:
                                <input type="text" name="firstPlayerName" id="firstPlayerId" value="${firstPlayerName}">
                                <c:set var="commonPlayerAnnotations" value="${violations['']}"></c:set>
                                <c:if test="${violations.containsKey('firstPlayer.name') || not empty commonPlayerAnnotations}">
                                    <c:set var="firstPlayerAnnotations" value="${violations['firstPlayer.name']}"></c:set>
                                    <c:choose>
                                        <c:when test="${not empty firstPlayerAnnotations['NotBlank']}">
                                            <div class="error">${firstPlayerAnnotations['NotBlank']}</div>
                                        </c:when>
                                        <c:when test="${not empty commonPlayerAnnotations['NotSame']}">
                                            <div class="error">${commonPlayerAnnotations['NotSame']}</div>
                                        </c:when>
                                        <c:when test="${not empty firstPlayerAnnotations['CorrectName']}">
                                            <div class="error">${firstPlayerAnnotations['CorrectName']}</div>
                                        </c:when>
                                        <c:when test="${not empty firstPlayerAnnotations['Size']}">
                                            <div class="error">${firstPlayerAnnotations['Size']}</div>
                                        </c:when>
                                    </c:choose>
                                </c:if>
                            </label>
                        </div>
                        <div class="form-group">
                            <label for="secondPlayerId">Second player name:
                                <input type="text" name="secondPlayerName" id="secondPlayerId" value="${secondPlayerName}">
                                <c:if test="${violations.containsKey('secondPlayer.name') || not empty commonPlayerAnnotations}">
                                    <c:set var="secondPlayerAnnotations" value="${violations['secondPlayer.name']}"></c:set>
                                    <c:choose>
                                        <c:when test="${not empty secondPlayerAnnotations['NotBlank']}">
                                            <div class="error">${secondPlayerAnnotations['NotBlank']}</div>
                                        </c:when>
                                        <c:when test="${not empty commonPlayerAnnotations['NotSame']}">
                                            <div class="error">${commonPlayerAnnotations['NotSame']}</div>
                                        </c:when>
                                        <c:when test="${not empty secondPlayerAnnotations['CorrectName']}">
                                            <div class="error">${secondPlayerAnnotations['CorrectName']}</div>
                                        </c:when>
                                        <c:when test="${not empty secondPlayerAnnotations['Size']}">
                                            <div class="error">${secondPlayerAnnotations['Size']}</div>
                                        </c:when>
                                    </c:choose>
                                </c:if>
                            </label>
                        </div>
                        <button class="submit" type="submit">Start</button>
                    </form>
                </div>
            </div>
        </main>
        <%@ include file="footer.jspf" %>
    </body>
</html>