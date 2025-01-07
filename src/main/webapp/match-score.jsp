<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tennis Scoreboard</title>
    <link rel="stylesheet" href="css/style.css">
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
                        <td>${game.firstPlayer.name}</td>
                        <td>${game.secondPlayer.name}</td>
                        <td>${game.firstPlayer.score} : ${game.secondPlayer.score}</td>
                        <td>${game.gameDate}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <%--    <form action="match-reg.jsp">--%>
            <%--        <button type="submit" class="btn">Go to Match Registration</button>--%>
            <%--    </form>--%>

            <section class="button-container">
                <form method="get" action="match-reg.jsp">
                    <button type="submit" class="btn">Go to Match Registration</button>
                </form>
                <br>
                <form method="get" action="index.jsp">
                    <button type="submit" class="btn">Start page</button>
                </form>
            </section>
        </c:when>
        <c:otherwise>
            <section>
                <form>
                    <h3>Not a single game has been played :(</h3>
                </form>
                <br>
                <form method="get" action="match-reg.jsp">
                    <button type="submit" class="btn">Go to Match Registration</button>
                </form>
            </section>
        </c:otherwise>
    </c:choose>


</main>
</body>
</html>