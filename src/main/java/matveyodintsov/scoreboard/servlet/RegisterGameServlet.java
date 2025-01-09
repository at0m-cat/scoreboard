package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.model.Player;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/new-match")
public class RegisterGameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("match-reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String player1Name = request.getParameter("p1");
        String player2Name = request.getParameter("p2");

        if (player1Name == null || player1Name.isEmpty() || player2Name == null || player2Name.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Player names are required.");
            return;
        }

        Player player1 = new Player(player1Name);
        Player player2 = new Player(player2Name);
        Game game = new Game(player1, player2);

        HttpSession session = request.getSession();
        session.setAttribute("currentGame", game);

        response.sendRedirect("update-score");
    }
}