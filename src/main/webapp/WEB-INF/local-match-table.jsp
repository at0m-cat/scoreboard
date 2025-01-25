<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Local matches</title>
    <style>
        <%@include file="../css/style.css" %>
    </style>
    <script>
        <%@include file="../js/pagination.js" %>
    </script>
</head>
<body>
<header>
    <h1>Last matches</h1>
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
                    <th>Register Game Date</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="match" items="${matches}">
                    <tr>
                        <td>${match.firstPlayer.name}</td>
                        <td>${match.secondPlayer.name}</td>
                        <td>${match.gameDate}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <section>
                <p>There are no current matches :(</p>
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