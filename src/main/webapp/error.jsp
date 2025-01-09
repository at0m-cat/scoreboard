<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<header>
    <h1><%=response.getStatus()%> Error</h1>
</header>
<main>
    <section>
        <form>
            <p>${message}</p>
        </form>
        <br>
        <form method="get" action="/">
            <button type="submit" class="btn">Go to main page</button>
        </form>
    </section>
</main>
</body>
</html>