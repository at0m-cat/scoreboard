<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Players</title>
    <link rel="stylesheet" href="css/style.css">
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
                </tr>
                </thead>
                <tbody>
                <c:forEach var="player" items="${players}">
                    <tr>
                        <td>${player.name}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <section class="button-container">
                <form method="get" action="new-match">
                    <button type="submit" class="btn">Go to Match Registration</button>
                </form>
                <br>
                <form method="get" action="/">
                    <button type="submit" class="btn">Start page</button>
                </form>
            </section>
        </c:when>
        <c:otherwise>
            <section>
                <form>
                    <p>Players are not registered :(</p>
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