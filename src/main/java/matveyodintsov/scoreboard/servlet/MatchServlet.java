package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.service.GameService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/match-score")
public class MatchServlet extends HttpServlet {

    private GameService gameService;

    @Override
    public void init() throws ServletException {
        this.gameService = new GameService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");

        try {
            Game game = gameService.getGameByUuid(uuid);

            if (game == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                request.setAttribute("message", "Match not found");
                request.getRequestDispatcher("error").forward(request, response);
            } else {
                request.setAttribute("game", game);
                getServletContext().getRequestDispatcher("/WEB-INF/single-match.jsp").forward(request, response);
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("message",e.getMessage());
            request.getRequestDispatcher("error").forward(request, response);
        }
    }
}