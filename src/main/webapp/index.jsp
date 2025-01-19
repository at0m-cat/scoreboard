<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tennis Game</title>
    <style>
        <%@include file="css/style.css" %>
    </style>
</head>
<body>
<header>
    <h1>Tennis Game</h1>
</header>
<main>
    <section class="button-container">
        <form method="get" action="new-match">
            <button type="submit" class="btn">Register Game</button>
        </form>
        <form method="get" action="players">
            <button type="submit" class="btn">All players</button>
        </form>
        <form method="get" action="matches">
            <button type="submit" class="btn">All matches</button>
        </form>
    </section>
</main>
</body>
</html>