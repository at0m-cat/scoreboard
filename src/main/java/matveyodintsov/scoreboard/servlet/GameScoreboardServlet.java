package matveyodintsov.scoreboard.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import matveyodintsov.scoreboard.repository.GamePersistenceRepository;
import matveyodintsov.scoreboard.service.GameService;
import matveyodintsov.scoreboard.util.StringContainer;

import java.io.IOException;

@WebServlet("/matches")
public class GameScoreboardServlet extends HttpServlet {

    private GameService gamePersistenceService;

    @Override
    public void init() throws ServletException {
        this.gamePersistenceService = new GameService(new GamePersistenceRepository());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("games", gamePersistenceService.getAll());
        request.getRequestDispatcher(StringContainer.redirectToScoreboardPage).forward(request, response);
    }
}