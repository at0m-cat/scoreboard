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
        <%@include file="../js/search.js" %>
    </script>
</head>
<body>
<header>
    <h1>Tennis Scoreboard</h1>
    <form method="get" action="/">
        <button type="submit" class="header-button">Main</button>
    </form>
</header>
<main>

    <c:choose>
        <c:when test="${not empty matches}">
            <table>
                <thead>
                <tr>
                    <th>Player 1</th>
                    <th>Player 2</th>
                    <th>Winner</th>
                    <th>Game Date</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="match" items="${matches}">
                    <tr onclick="redirectToMatch('${match.uuid}')" style="cursor: pointer;">
                        <td>${match.firstPlayer.name}</td>
                        <td>${match.secondPlayer.name}</td>
                        <td>${match.winner.name}</td>
                        <td>${match.gameDate}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <section class="pagination">
                <ul>
                    <li>
                        <button onclick="goToPage(1, ${totalPages})" class="btn" ${currentPage == 1 ? 'disabled' : ''}>
                            First
                        </button>
                    </li>
                    <li>
                        <button onclick="goToPage(${currentPage - 1}, ${totalPages})"
                                class="btn" ${currentPage == 1 ? 'disabled' : ''}>Prev
                        </button>
                    </li>
                    <li><span id="currentPageDisplay">Page ${currentPage} of ${totalPages}</span></li>
                    <li>
                        <button onclick="goToPage(${currentPage + 1}, ${totalPages})"
                                class="btn" ${currentPage == totalPages ? 'disabled' : ''}>Next
                        </button>
                    </li>
                    <li>
                        <button onclick="goToPage(${totalPages}, ${totalPages})"
                                class="btn" ${currentPage == totalPages ? 'disabled' : ''}>Last
                        </button>
                    </li>
                </ul>
            </section>
            <section class="pagination search">
                <form onsubmit="return search(event)">
                    <input type="text" id="playerNameInput" placeholder="Enter Player Name" required>
                    <button type="submit" class="btn">Find Games</button>
                </form>
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
                <p>Not a single match has been played :(</p>
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