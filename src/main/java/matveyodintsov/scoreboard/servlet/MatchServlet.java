package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.service.GameService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/match")
public class MatchServlet extends HttpServlet {

    private GameService gameService;

    @Override
    public void init() throws ServletException {
        this.gameService = new GameService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");

        if (uuid == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.setAttribute("message", "Invalid UUID");
            request.getRequestDispatcher("error").forward(request, response);
            return;
        }

        Game game = gameService.getGameByUuid(uuid);

        if (game == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.setAttribute("message", "Match not found");
            request.getRequestDispatcher("error").forward(request, response);
        } else {
            request.setAttribute("games", List.of(game));
            getServletContext().getRequestDispatcher("/match-score.jsp").forward(request, response);
        }

    }
}