<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.lang.Math"%>
<html>
    <head>
        <title>Matches</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/normalize.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
    </head>
    <body>
        <%@ include file="header.jspf" %>
        <main class="main">
            <div class="container">
                    <div class="content-container">
                        <div class="matches-container">
                            <form class="name-filter-form" method="GET">
                                <input type="text" name="filter_by_player_name" placeholder="Enter player name"
                                    value="${not empty pageDto.namePattern ? pageDto.namePattern : ""}" required pattern="\S+">
                            </form>
                            <table class="matches-table">
                                <thread>
                                    <tr>
                                        <th>Player One</th>
                                        <th>Player Two</th>
                                        <th>Winner</th>
                                    </tr>
                                </thread>
                                <tdoby>
                                    <c:forEach var="match" items="${pageDto.matches}">
                                        <tr>
                                            <td>
                                                <p><c:out value="${match.firstPlayerName}"/></p>
                                            </td>
                                            <td>
                                                <p><c:out value="${match.secondPlayerName}"/></p>
                                            </td>
                                            <td>
                                                <p><c:out value="${match.winner}"/></p>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tdoby>
                            </table>
                            <c:if test="${pageDto.totalPages > 1}">
                                <div class="pagination">
                                    <!-- Стрелка влево (предыдущая страница) -->
                                    <c:if test="${pageDto.currentPage > 1}">
                                        <c:choose>
                                            <c:when test="${not empty pageDto.namePattern}">
                                                <a href="?page=${pageDto.currentPage - 1}&filter_by_player_name=${pageDto.namePattern}">←</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="?page=${pageDto.currentPage - 1}">←</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>

                                    <!-- Отображение трех страниц -->
                                    <c:choose>
                                        <c:when test="${pageDto.currentPage == 1}">
                                            <!-- Если текущая страница первая, показываем 1, 2, 3 -->
                                            <c:forEach begin="1" end="${Math.min(3, pageDto.totalPages)}" var="i">
                                                <c:choose>
                                                    <c:when test="${pageDto.currentPage == i}">
                                                        <span>${i}</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:choose>
                                                            <c:when test="${not empty pageDto.namePattern}">
                                                                <a href="?page=${i}&filter_by_player_name=${pageDto.namePattern}">${i}</a>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <a href="?page=${i}">${i}</a>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:when test="${pageDto.currentPage == pageDto.totalPages}">
                                            <!-- Если текущая страница последняя, показываем totalPages-2, totalPages-1, totalPages -->
                                            <c:forEach begin="${Math.max(1, pageDto.totalPages - 2)}" end="${pageDto.totalPages}" var="i">
                                                <c:choose>
                                                    <c:when test="${pageDto.currentPage == i}">
                                                        <span>${i}</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:choose>
                                                            <c:when test="${not empty pageDto.namePattern}">
                                                                <a href="?page=${i}&filter_by_player_name=${pageDto.namePattern}">${i}</a>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <a href="?page=${i}">${i}</a>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <!-- Иначе показываем currentPage-1, currentPage, currentPage+1 -->
                                            <c:forEach begin="${pageDto.currentPage - 1}" end="${pageDto.currentPage + 1}" var="i">
                                                <c:choose>
                                                    <c:when test="${pageDto.currentPage == i}">
                                                        <span>${i}</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:choose>
                                                            <c:when test="${not empty pageDto.namePattern}">
                                                                <a href="?page=${i}&filter_by_player_name=${pageDto.namePattern}">${i}</a>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <a href="?page=${i}">${i}</a>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>

                                    <!-- Стрелка вправо (следующая страница) -->
                                    <c:if test="${pageDto.currentPage < pageDto.totalPages}">
                                        <c:choose>
                                            <c:when test="${not empty pageDto.namePattern}">
                                                <a href="?page=${pageDto.currentPage + 1}&filter_by_player_name=${pageDto.namePattern}">→</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="?page=${pageDto.currentPage + 1}">→</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
        </main>
        <%@ include file="footer.jspf" %>
    </body>
</html>