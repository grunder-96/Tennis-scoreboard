<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Tennis scoreboard</title>
        <link rel="stylesheet" href="../../static/css/normalize.css">
        <link rel="stylesheet" href="../../static/css/style.css">
    </head>
    <body>
        <%@ include file="header.jspf" %>
        <main class="main">
            <div class="container">
                <div class="button-container">
                    <a class="button" href="/new-match">new match</a>
                    <a class="button" href="/matches">completed matches</a>
                </div>
            </div>
        </main>
        <%@ include file="footer.jspf" %>
    </body>
</html>