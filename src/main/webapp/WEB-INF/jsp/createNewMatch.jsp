<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new match</title>
</head>
<body>
    <form action="/new-match" method="POST">
        <label for="firstPlayerId">
            First player name:<input type="text" name="firstPlayerName" id="firstPlayerId">
        </label>
        <label for="secondPlayerId">
            Second player name:<input type="text" name="secondPlayerName" id="secondPlayerId">
        </label>
        <button type="submit">Start</button>
    </form>
</body>
</html>
