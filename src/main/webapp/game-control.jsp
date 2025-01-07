<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Game Control</title>
    <link rel="stylesheet" href="css/style.css">
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
                <td>${currentGame.firstPlayer.score}</td>
                <td>
                    <form method="post" action="update-score">
                        <input type="hidden" name="player" value="firstPlayer">
                        <button type="submit" name="action" value="increment">+1</button>
                        <button type="submit" name="action" value="decrement">-1</button>
                    </form>
                </td>
            </tr>
            <tr>
                <td>${currentGame.secondPlayer.name}</td>
                <td>${currentGame.secondPlayer.score}</td>
                <td>
                    <form method="post" action="update-score">
                        <input type="hidden" name="player" value="secondPlayer">
                        <button type="submit" name="action" value="increment">+1</button>
                        <button type="submit" name="action" value="decrement">-1</button>
                    </form>
                </td>
            </tr>
            </tbody>
        </table>
        <form method="post" action="finish-game">
            <button type="submit" class="btn">Finish Game</button>
        </form>
    </c:if>
    <c:if test="${empty currentGame}">
        <p>No active game. Please register a new game.</p>
        <form method="post" action="match-reg.jsp">
            <button type="submit" class="btn">Register Game</button>
        </form>
    </c:if>
</main>
</body>
</html>