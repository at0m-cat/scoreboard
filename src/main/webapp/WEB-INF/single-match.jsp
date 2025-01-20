<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Match details</title>
    <style>
        <%@include file="../css/style.css" %>
    </style>
    <script>
        <%@include file="../js/redirect.js" %>
    </script>
</head>
<body>
<header>
    <h1>Match details</h1>
    <form method="get" action="/">
        <button type="submit" class="header-button">Main</button>
    </form>
</header>
<main>
    <div class="match-card">
        <h2>Match ID: ${match.id}</h2>
        <p><strong>UUID:</strong> ${match.uuid}</p>
        <div class="players">
            <div class="player ${match.winner == 'firstPlayer' ? 'winner' : ''}" onclick="redirectToPlayerInfo('${match.firstPlayer.name}')">
                <h3>${match.firstPlayer.name}</h3>
                <p>Score: <span class="score">${match.firstPlayerScore}</span></p>
            </div>
            <div class="versus">VS</div>
            <div class="player ${match.winner == 'secondPlayer' ? 'winner' : ''}" onclick="redirectToPlayerInfo('${match.secondPlayer.name}')">
                <h3>${match.secondPlayer.name}</h3>
                <p>Score: <span class="score">${match.secondPlayerScore}</span></p>
            </div>
        </div>
        <p><strong>Date:</strong> ${match.gameDate}</p>
        <p><strong>Status:</strong> Completed</p>
    </div>
    <br>
    <section class="button-container">
        <form method="get" action="new-match">
            <button type="submit" class="btn">Register New Match</button>
        </form>
        <form method="get" action="matches">
            <button type="submit" class="btn">All matches</button>
        </form>
        <form method="get" action="/">
            <button type="submit" class="btn">Back to Start</button>
        </form>
    </section>
</main>
</body>
</html>