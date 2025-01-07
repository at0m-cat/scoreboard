package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.model.Game;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/update-score")
public class UpdateScoreServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Game currentGame = (Game) session.getAttribute("currentGame");

        if (currentGame == null) {
            response.sendRedirect("game-control.jsp");
            return;
        }

        String player = request.getParameter("player");
        String action = request.getParameter("action");

        if ("firstPlayer".equals(player)) {
            if ("increment".equals(action)) {
                currentGame.getFirstPlayer().setScore(currentGame.getFirstPlayer().getScore() + 1);
            } else if ("decrement".equals(action)) {
                currentGame.getFirstPlayer().setScore(Math.max(0, currentGame.getFirstPlayer().getScore() - 1));
            }
        } else if ("secondPlayer".equals(player)) {
            if ("increment".equals(action)) {
                currentGame.getSecondPlayer().setScore(currentGame.getSecondPlayer().getScore() + 1);
            } else if ("decrement".equals(action)) {
                currentGame.getSecondPlayer().setScore(Math.max(0, currentGame.getSecondPlayer().getScore() - 1));
            }
        }

        session.setAttribute("currentGame", currentGame);
        response.sendRedirect("game-control.jsp");
    }
}