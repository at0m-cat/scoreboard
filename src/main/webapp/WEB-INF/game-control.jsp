<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Game Control</title>
    <style>
        <%@include file="../css/style.css" %>
    </style>
    <script> <%@include file="../js/updateScore.js"%></script>
</head>
<body>
<header>
    <h1>Game Control</h1>
</header>
<main>
    <c:if test="${not empty currentGame}">
        <table>
            <thead>
            <tr>
                <th>Player</th>
                <th>Score</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${currentGame.firstPlayer.name}</td>
                <td id="firstPlayerScore">${currentGame.firstPlayerScore}</td>
                <td>
                    <section class="button-container">
                        <button type="button" onclick="updateScore('firstPlayer', 'increment')">+1</button>
                        <button type="button" onclick="updateScore('firstPlayer', 'decrement')">-1</button>
                    </section>
                </td>
            </tr>
            <tr>
                <td>${currentGame.secondPlayer.name}</td>
                <td id="secondPlayerScore">${currentGame.secondPlayerScore}</td>
                <td>
                    <section class="button-container">
                        <button type="button" onclick="updateScore('secondPlayer', 'increment')">+1</button>
                        <button type="button" onclick="updateScore('secondPlayer', 'decrement')">-1</button>
                    </section>
                </td>
            </tr>
            </tbody>
        </table>
        <section class="button-container">
            <form method="post" action="finish-game">
                <input type="hidden" name="uuid" value="${currentGame.uuid}">
                <button type="submit" class="btn">Finish Game</button>
            </form>
        </section>
    </c:if>
    <c:if test="${empty currentGame}">
        <p>No active game. Please register a new game.</p>
        <section class="button-container">
            <form method="get" action="new-match">
                <button type="submit" class="btn">Register Game</button>
            </form>
        </section>
    </c:if>
</main>
</body>
</html>