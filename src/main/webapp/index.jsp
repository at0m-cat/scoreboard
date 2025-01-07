<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tennis Game</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header>
    <h1>Tennis Game</h1>
</header>
<main>
    <section class="button-container">
        <form method="get" action="match-reg.jsp">
            <button type="submit" class="btn">Register Game</button>
        </form>
        <br>
        <form method="get" action="scoreboard">
            <button type="submit" class="btn">Scoreboard</button>
        </form>
    </section>
</main>
</body>
</html>