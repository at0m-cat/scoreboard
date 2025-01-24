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
    <script> <%@include file="../js/loadingIndicator.js"%></script>
    <c:if test="${not empty currentMatch}">
        <script>
            const gameUuid = '${currentMatch.uuid}';
        </script>
    </c:if>
</head>
<body>
<header>
    <h1>Game Control</h1>
    <form method="get" action="/">
        <button type="submit" class="header-button">Main</button>
    </form>
</header>

<div id="loadingIndicator" style="display: none;">
    <main>
        <p>Wait for it...</p>
    </main>
</div>

<main id="content" style="display: none;">
    <c:if test="${not empty currentMatch}">
        <table>
            <thead>
            <tr>
                <th>Player</th>
                <th>Sets</th>
                <th>Game</th>
                <th>Score</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${currentMatch.firstPlayer.name}</td>
                <td id="firstPlayerSets">${currentMatch.scoreboard.firstPlayerSetScore}</td>
                <td id="firstPlayerGames">${currentMatch.scoreboard.firstPlayerGameScore}</td>
                <td id="firstPlayerScore">${currentMatch.scoreboard.firstPlayerScore}</td>
                <td>
                    <section class="button-container">
                        <button type="button" data-player="${currentMatch.firstPlayer.name}" player-number="0" onclick="updateScore(this)">+1</button>
                    </section>
                </td>
            </tr>
            <tr>
                <td>${currentMatch.secondPlayer.name}</td>
                <td id="secondPlayerSets">${currentMatch.scoreboard.secondPlayerSetScore}</td>
                <td id="secondPlayerGames">${currentMatch.scoreboard.secondPlayerGameScore}</td>
                <td id="secondPlayerScore">${currentMatch.scoreboard.secondPlayerScore}</td>
                <td>
                    <section class="button-container">
                        <button type="button" data-player="${currentMatch.secondPlayer.name}" player-number="1" onclick="updateScore(this)">+1</button>
                    </section>
                </td>
            </tr>
            </tbody>
        </table>
        <section class="button-container">
            <form method="post" action="finish-game">
                <input type="hidden" name="uuid" value="${currentMatch.uuid}">
                <button type="submit" class="btn">Finish Game</button>
            </form>
        </section>
    </c:if>
    <c:if test="${empty currentMatch}">
        <p>No active match. Please register a new match.</p>
        <section class="button-container">
            <form method="get" action="new-match">
                <button type="submit" class="btn">Register Game</button>
            </form>
        </section>
    </c:if>
</main>
</body>
</html>