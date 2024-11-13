<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Tennis scoreboard</title>
        <link rel="stylesheet" href="../../css/main.css">
    </head>
    <body>
        <div class="container">
            <div class="scoreboard_wrapper shadow">
                <div class="scoreboard_title_wrapper">
                    <h1>COURT No. 18</h1>
                </div>
                <div class="buttons-wrapper">
                    <form action="/new-match">
                        <button type="submit" class="button shadow">New match</button>
                    </form>
                    <form action="/matches">
                        <button type="submit" class="button shadow">Completed matches</button>
                    </form>
                </div>
            </div>
            <div class="poles-wrapper">
                <div class="poles shadow"></div>
                <div class="poles shadow"></div>
            </div>
        </div>
    </body>
</html>