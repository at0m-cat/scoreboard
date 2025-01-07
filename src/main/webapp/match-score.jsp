<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Табло теннисного матча</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header>
    <h1>Табло теннисного матча</h1>
</header>

<table>
    <tr>
        <th>ID Игры</th>
        <th>Игрок 1</th>
        <th>Игрок 2</th>
        <th>Счет</th>
        <th>Дата</th>
    </tr>
    <c:forEach var="game" items="${games}">
        <tr>
            <td>${game.id}</td>
            <td>${game.firstPlayer.name}</td>
            <td>${game.secondPlayer.name}</td>
            <td>${game.firstPlayer.score} : ${game.secondPlayer.score}</td>
            <td>${game.gameDate}</td>
        </tr>
    </c:forEach>
</table>

<form method="post" action="update-score">
    <button type="submit" name="player" value="Player1">+1 Игрок 1</button>
    <button type="submit" name="player" value="Player2">+1 Игрок 2</button>
    <button type="submit" name="reset" value="reset">Сбросить счет</button>
</form>
</body>
</html>