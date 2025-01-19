<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Player Info - ${player.name}</title>
    <style>
        <%@include file="../css/style.css" %>
    </style>
</head>
<body>
<header>
    <h1>Player Info - ${player.name}</h1>
    <form method="get" action="/">
        <button type="submit" class="header-button">Main</button>
    </form>
</header>
<main>
    <div class="player-card">
        <h2>${player.name}</h2>
        <p><strong>ID:</strong> ${player.id}</p>
        <br>
        <p><strong>Matches:</strong> ${player.totalMatches}</p>
        <p><strong>Wins:</strong> ${player.totalWins}</p>
        <p><strong>Losses:</strong> ${player.totalLosses}</p>
    </div>

    <section class="button-container">
        <form method="get" action="new-match">
            <button type="submit" class="btn">Register New Match</button>
        </form>
        <form method="get" action="players">
            <button type="submit" class="btn">All Players</button>
        </form>
        <form method="get" action="/">
            <button type="submit" class="btn">Back to Start</button>
        </form>
    </section>
</main>
</body>
</html>