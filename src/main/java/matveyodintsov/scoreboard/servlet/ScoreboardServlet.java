package matveyodintsov.scoreboard.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import matveyodintsov.scoreboard.service.GamePersistenceSingletonService;
import matveyodintsov.scoreboard.util.PathContainer;

import java.io.IOException;

@WebServlet("/matches")
public class ScoreboardServlet extends HttpServlet {

    private GamePersistenceSingletonService gamePersistenceService;
    private String scoreboardPage;

    @Override
    public void init() throws ServletException {
        this.gamePersistenceService = GamePersistenceSingletonService.getInstance();
        this.scoreboardPage = PathContainer.redirectToScoreboardPage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("games", gamePersistenceService.getAll());
        request.getRequestDispatcher(scoreboardPage).forward(request, response);
    }
}