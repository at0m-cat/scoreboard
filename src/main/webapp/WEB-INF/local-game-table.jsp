<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Local games</title>
    <style>
        <%@include file="../css/style.css" %>
    </style>
    <script>
        <%@include file="../js/pagination.js" %>
    </script>
</head>
<body>
<header>
    <h1>Local games</h1>
    <form method="get" action="/">
        <button type="submit" class="header-button">Main</button>
    </form>
</header>
<main>

    <c:choose>
        <c:when test="${not empty games}">
            <table>
                <thead>
                <tr>
                    <th>Player 1</th>
                    <th>Player 2</th>
                    <th>Register Game Date</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="game" items="${games}">
                    <tr>
                        <td>${game.firstPlayer.name}</td>
                        <td>${game.secondPlayer.name}</td>
                        <td>${game.gameDate}</td>
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
        </c:when>
        <c:otherwise>
            <section>
                <p>There are no current games :(</p>
                <br>
            </section>
        </c:otherwise>
    </c:choose>
    <section class="button-container">
        <form method="get" action="/">
            <button type="submit" class="btn">Start page</button>
        </form>
        <form method="get" action="new-match">
            <button type="submit" class="btn">Go to Match Registration</button>
        </form>
    </section>

</main>
</body>
</html>