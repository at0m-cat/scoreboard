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
            <button type="submit" class="btn">New match</button>
        </form>
        <form method="get" action="players">
            <button type="submit" class="btn">Players</button>
        </form>
    </section>
    <br>
    <section class="button-container">
        <form method="get" action="matches">
            <button type="submit" class="btn">Finished matches</button>
        </form>
    </section>
    <br>
    <section class="button-container">
        <form method="get" action="local">
            <button type="submit" class="btn">Current matches</button>
        </form>
    </section>

</main>
</body>
</html>