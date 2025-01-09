package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.model.Player;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import matveyodintsov.scoreboard.service.PlayerService;

import java.io.IOException;
import java.util.stream.Stream;

@WebServlet("/new-match")
public class RegisterGameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("match-reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlayerService playerService = new PlayerService();

        String p1 = request.getParameter("p1");
        String p2 = request.getParameter("p2");

        if (p1.equals(p2)) {
            request.setAttribute("message", "Player cannot play against themselves.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }


        if (playerService.isPlayer(p1)){
            request.setAttribute("message", "Player already exists.");
            request.getRequestDispatcher("error").forward(request, response);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        if (playerService.isPlayer(p2)) {
            request.setAttribute("message", "Player already exists.");
            request.getRequestDispatcher("error").forward(request, response);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Player firstPlayer = new Player(p1);
        Player secondPlayer = new Player(p2);
        Game game = new Game(firstPlayer, secondPlayer);

        HttpSession session = request.getSession();
        session.setAttribute("currentGame", game);

        response.sendRedirect("update-score");
    }
}