<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tennis Scoreboard</title>
    <style>
        <%@include file="../css/style.css" %>
    </style>
    <script>
        <%@include file="../js/redirect.js" %>
        <%@include file="../js/pagination.js" %>
    </script>
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
                    <th>Player 1</th>
                    <th>Player 2</th>
                    <th>Scores</th>
                    <th>Game Date</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="game" items="${games}">
                    <tr onclick="redirectToMatch('${game.uuid}')" style="cursor: pointer;">
                        <td>${game.firstPlayer.name}</td>
                        <td>${game.secondPlayer.name}</td>
                        <td>${game.firstPlayerScore} : ${game.secondPlayerScore}</td>
                        <td>${game.gameDate}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <section class="pagination">
                <ul>
                    <li>
                        <button onclick="goToPage(1, ${totalPages})" class="btn" ${currentPage == 1 ? 'disabled' : ''}>First</button>
                    </li>
                    <li>
                        <button onclick="goToPage(${currentPage - 1}, ${totalPages})" class="btn" ${currentPage == 1 ? 'disabled' : ''}>Prev</button>
                    </li>
                    <li><span id="currentPageDisplay">Page ${currentPage} of ${totalPages}</span></li>
                    <li>
                        <button onclick="goToPage(${currentPage + 1}, ${totalPages})" class="btn" ${currentPage == totalPages ? 'disabled' : ''}>Next</button>
                    </li>
                    <li>
                        <button onclick="goToPage(${totalPages}, ${totalPages})" class="btn" ${currentPage == totalPages ? 'disabled' : ''}>Last</button>
                    </li>
                </ul>
            </section>
            <section class="button-container">
                <form method="get" action="/">
                    <button type="submit" class="btn">Start page</button>
                </form>
                <form method="get" action="players">
                    <button type="submit" class="btn">All players</button>
                </form>
                <form method="get" action="new-match">
                    <button type="submit" class="btn">Go to Match Registration</button>
                </form>
            </section>
        </c:when>
        <c:otherwise>
            <form>
                <p>Not a single game has been played :(</p>
            </form>
            <br>
            <section class="button-container">
                <form method="get" action="/">
                    <button type="submit" class="btn">Start page</button>
                </form>
                <form method="get" action="new-match">
                    <button type="submit" class="btn">Go to Match Registration</button>
                </form>
            </section>
        </c:otherwise>
    </c:choose>
</main>
</body>
</html>