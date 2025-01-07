<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Game Registration</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<header>
    <h1>Game Registration</h1>
</header>

<main>
    <form method="post" action="register-game">
        <div class="form-group">
            <label for="p1">Player 1</label>
            <input type="text" id="p1" name="p1" placeholder="Enter Player 1 Name" required>
        </div>
        <div class="form-group">
            <label for="p2">Player 2</label>
            <input type="text" id="p2" name="p2" placeholder="Enter Player 2 Name" required>
        </div>
        <button type="submit" class="btn">Register Game</button>
    </form>
</main>

</body>
</html>