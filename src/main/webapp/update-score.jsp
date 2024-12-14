<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.HashMap" %>
<%
  HashMap<String, Integer> scores = (HashMap<String, Integer>) session.getAttribute("scores");
  if (scores == null) {
    scores = new HashMap<>();
    scores.put("Player1", 0);
    scores.put("Player2", 0);
    session.setAttribute("scores", scores);
  }

  String player = request.getParameter("player");
  if (player != null && scores.containsKey(player)) {
    scores.put(player, scores.get(player) + 1);
  }

  String reset = request.getParameter("reset");
  if ("reset".equals(reset)) {
    scores.put("Player1", 0);
    scores.put("Player2", 0);
  }

  session.setAttribute("scores", scores);

  response.sendRedirect("match-score.jsp");
%>