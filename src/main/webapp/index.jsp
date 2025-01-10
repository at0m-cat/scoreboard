<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tennis Game</title>
    <style><%@include file="css/style.css"%></style>
</head>
<body>
<header>
    <h1>Tennis Game</h1>
</header>
<main>
    <section>
        <form method="get" action="new-match">
            <button type="submit" class="btn">Register Game</button>
        </form>
        <br>
        <form method="get" action="players">
            <button type="submit" class="btn">All players</button>
        </form>
        <br>
        <form method="get" action="scoreboard">
            <button type="submit" class="btn">All matches</button>
        </form>
    </section>
</main>
</body>
</html>