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
<%--            <section class="pagination">--%>
<%--                <ul>--%>
<%--                    <li>--%>
<%--                        <button onclick=" " class="btn">First</button>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <button onclick=" " class="btn" id="prevPage" disabled>Prev</button>--%>
<%--                    </li>--%>
<%--                    <li><span id="currentPageDisplay">Page 1</span></li>--%>
<%--                    <li>--%>
<%--                        <button onclick=" " class="btn" id="nextPage">Next</button>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <button onclick=" " class="btn">Last</button>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </section>--%>
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