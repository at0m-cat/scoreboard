package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.repository.GameRepository;
import matveyodintsov.scoreboard.service.BaseGameService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/matches")
public class ScoreboardServlet extends HttpServlet {

    private BaseGameService gameService;

    @Override
    public void init() throws ServletException {
        this.gameService = new BaseGameService(new GameRepository());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("games", gameService.getAll());
        request.getRequestDispatcher("WEB-INF/match-score.jsp").forward(request, response);
    }
}