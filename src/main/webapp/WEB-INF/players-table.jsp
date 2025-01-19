<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Players</title>
    <style>
        <%@include file="../css/style.css" %>
    </style>
    <script>
        <%@include file="../js/redirect.js" %>
        <%@include file="../js/pagination.js" %>
        <%@include file="../js/search.js" %>

    </script>
</head>
<body>
<header>
    <h1>Players</h1>
</header>
<main>
    <c:choose>
        <c:when test="${not empty players}">
            <table>
                <thead>
                <tr>
                    <th>Player</th>
                    <th>Matches</th>
                    <th>Wins</th>
                    <th>Losses</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="player" items="${players}">
                    <tr onclick="redirectToPlayerInfo('${player.name}')" style="cursor: pointer;">
                        <td>${player.name}</td>
                        <td>${player.totalMatches}</td>
                        <td>${player.totalWins}</td>
                        <td>${player.totalLosses}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <section class="pagination" id="pagination_bar">
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
            <section class="pagination search">
                <form onsubmit="return search(event)">
                    <input type="text" id="playerNameInput" placeholder="Enter Player Name" required>
                    <button type="submit" class="btn">Find Player</button>
                </form>
            </section>
            <section class="button-container">
                <form method="get" action="/">
                    <button type="submit" class="btn">Start page</button>
                </form>
                <form method="get" action="matches">
                    <button type="submit" class="btn">All matches</button>
                </form>
                <form method="get" action="new-match">
                    <button type="submit" class="btn">Go to Match Registration</button>
                </form>
            </section>
        </c:when>
        <c:otherwise>
            <p>Players are not registered :(</p>
            <br>
            <section class="button-container">
                <form method="get" action="new-match">
                    <button type="submit" class="btn">Go to Match Registration</button>
                </form>
            </section>
        </c:otherwise>
    </c:choose>
</main>
</body>
</html>