package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.model.Player;
import matveyodintsov.scoreboard.util.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/register-game")
public class RegisterGameServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String player1Name = request.getParameter("p1");
        String player2Name = request.getParameter("p2");

        if (player1Name == null || player1Name.isEmpty() || player2Name == null || player2Name.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Player names are required.");
            return;
        }

        Player player1 = new Player(null, player1Name, 0);
        Player player2 = new Player(null, player2Name, 0);
        Game game = new Game(null, player1, player2, LocalDate.now());

        HttpSession session = request.getSession();
        session.setAttribute("currentGame", game);

        response.sendRedirect("game-control.jsp");
    }
}