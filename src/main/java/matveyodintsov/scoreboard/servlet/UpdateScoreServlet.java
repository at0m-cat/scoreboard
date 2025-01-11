package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.model.Game;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/update-score")
public class UpdateScoreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/game-control.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Game currentGame = (Game) session.getAttribute("currentGame");

        if (currentGame == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.setAttribute("message", "Match does not exist.");
            request.getRequestDispatcher("error").forward(request, response);
            return;
        }

        String player = request.getParameter("player");
        String action = request.getParameter("action");

        if ("firstPlayer".equals(player)) {
            if ("increment".equals(action)) {
                currentGame.setFirstPlayerScore(currentGame.getFirstPlayerScore() + 1);
            } else if ("decrement".equals(action)) {
                currentGame.setFirstPlayerScore((Math.max(0, currentGame.getFirstPlayerScore() - 1)));
            }
        } else if ("secondPlayer".equals(player)) {
            if ("increment".equals(action)) {
                currentGame.setSecondPlayerScore(currentGame.getSecondPlayerScore() + 1);
            } else if ("decrement".equals(action)) {
                currentGame.setSecondPlayerScore((Math.max(0, currentGame.getSecondPlayerScore() - 1)));
            }
        }
//
//        session.setAttribute("currentGame", currentGame);
//        response.sendRedirect("update-score");

        response.setContentType("application/json");
        response.getWriter().write(String.format("{\"firstPlayerScore\":%d,\"secondPlayerScore\":%d}",
                currentGame.getFirstPlayerScore(),
                currentGame.getSecondPlayerScore()));
    }
}