<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Табло теннисного матча</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 20px;
            text-align: center;
        }
        table {
            margin: 0 auto;
            border-collapse: collapse;
            width: 50%;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        h1 {
            color: #333;
        }
        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<h1>Табло теннисного матча</h1>

<%
    HashMap<String, Integer> scores = (HashMap<String, Integer>) session.getAttribute("scores");
    if (scores == null) {
        scores = new HashMap<>();
        scores.put("Player1", 0);
        scores.put("Player2", 0);
        session.setAttribute("scores", scores);
    }
%>

<table>
    <tr>
        <th>Игрок</th>
        <th>Счет</th>
    </tr>
    <tr>
        <td>Игрок 1</td>
        <td><%= scores.get("Player1") %></td>
    </tr>
    <tr>
        <td>Игрок 2</td>
        <td><%= scores.get("Player2") %></td>
    </tr>
</table>

<form method="post" action="update-score.jsp">
    <button type="submit" name="player" value="Player1">+1 Игрок 1</button>
    <button type="submit" name="player" value="Player2">+1 Игрок 2</button>
    <button type="submit" name="reset" value="reset">Сбросить счет</button>
</form>
</body>
</html>