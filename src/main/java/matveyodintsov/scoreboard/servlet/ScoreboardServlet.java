package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.service.GameService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/matches")
public class ScoreboardServlet extends HttpServlet {

    private GameService gameService;

    @Override
    public void init() throws ServletException {
        this.gameService = new GameService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("games", gameService.getGames());
        request.getRequestDispatcher("WEB-INF/match-score.jsp").forward(request, response);
    }
}