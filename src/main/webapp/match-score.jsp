<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tennis Scoreboard</title>
    <style><%@include file="css/style.css"%></style>
</head>
<body>
<header>
    <h1>Tennis Scoreboard</h1>
</header>
<main>

    <c:choose>
        <c:when test="${not empty games}">
            <table>
                <thead>
                <tr>
                    <th>ID Game</th>
                    <th>UUID Game</th>
                    <th>Player 1</th>
                    <th>Player 2</th>
                    <th>Scores</th>
                    <th>Game Date</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="game" items="${games}">
                    <tr>
                        <td>${game.id}</td>
                        <td>${game.uuid}</td>
                        <td>${game.firstPlayer.name}</td>
                        <td>${game.secondPlayer.name}</td>
                        <td>${game.firstPlayerScore} : ${game.secondPlayerScore}</td>
                        <td>${game.gameDate}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <section class="button-container">
                <form method="get" action="new-match">
                    <button type="submit" class="btn">Go to Match Registration</button>
                </form>
                <form method="get" action="/">
                    <button type="submit" class="btn">Start page</button>
                </form>
            </section>
        </c:when>
        <c:otherwise>
            <section>
                <form>
                    <p>Not a single game has been played :(</p>
                </form>
                <br>
                <form method="get" action="new-match">
                    <button type="submit" class="btn">Go to Match Registration</button>
                </form>
            </section>
        </c:otherwise>
    </c:choose>
</main>
</body>
</html>