package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.model.Player;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import matveyodintsov.scoreboard.repository.PlayerRepository;
import matveyodintsov.scoreboard.service.PlayerService;

import java.io.IOException;

@WebServlet("/new-match")
public class RegisterGameServlet extends HttpServlet {

    private PlayerService playerService;

    @Override
    public void init() throws ServletException {
        this.playerService = new PlayerService(new PlayerRepository());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/match-reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String p1 = request.getParameter("p1");
        String p2 = request.getParameter("p2");

        if (p1.equals(p2)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("message", "Player cannot play against themselves.");
            request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
            return;
        }

        Player firstPlayer;
        Player secondPlayer;

        if (playerService.isPlayer(p1)) {
            firstPlayer = playerService.getPlayer(p1);
        } else {
            firstPlayer = new Player(p1);
            playerService.save(firstPlayer);
        }

        if (playerService.isPlayer(p2)) {
            secondPlayer = playerService.getPlayer(p2);
        } else {
            secondPlayer = new Player(p2);
            playerService.save(secondPlayer);
        }

        Game game = new Game(firstPlayer, secondPlayer);

        HttpSession session = request.getSession();
        session.setAttribute("currentGame", game);

        response.sendRedirect("update-score");
    }
}