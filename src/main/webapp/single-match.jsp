<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Match ${name}</title>
    <style>
        <%@include file="css/style.css" %>
    </style>
</head>
<body>
<header>
    <h1>Match ${name}</h1>
</header>
<main>
    <div class="match-card">
        <h2>Match ID: ${game.id}</h2>
        <p><strong>UUID:</strong> ${game.uuid}</p>
        <div class="players">
            <div class="player">
                <h3>${game.firstPlayer.name}</h3>
                <p>Score: <span class="score">${game.firstPlayerScore}</span></p>
            </div>
            <div class="versus">VS</div>
            <div class="player">
                <h3>${game.secondPlayer.name}</h3>
                <p>Score: <span class="score">${game.secondPlayerScore}</span></p>
            </div>
        </div>
        <p><strong>Date:</strong> ${game.gameDate}</p>
        <p><strong>Status:</strong> Completed</p>
    </div>
    <br>
    <section class="button-container">
        <form method="get" action="new-match">
            <button type="submit" class="btn">Register New Match</button>
        </form>
        <form method="get" action="/">
            <button type="submit" class="btn">Back to Start</button>
        </form>
    </section>
</main>
</body>
</html>