package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.model.Player;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import matveyodintsov.scoreboard.repository.GameLocalRepository;
import matveyodintsov.scoreboard.repository.PlayerPersistenceRepository;
import matveyodintsov.scoreboard.service.GameService;
import matveyodintsov.scoreboard.service.PlayerService;
import matveyodintsov.scoreboard.service.SingletonServiceFactory;
import matveyodintsov.scoreboard.util.PathContainer;

import java.io.IOException;

@WebServlet("/new-match")
public class RegisterGameServlet extends HttpServlet {

    private PlayerService playerService;
    private GameService gameLocalService;
    private String errorPage;
    private String gameRegPage;
    private String gameScoreUpdate;

    @Override
    public void init() throws ServletException {
        this.playerService = new PlayerService(new PlayerPersistenceRepository());
        this.gameLocalService = SingletonServiceFactory.getInstance(new GameService(new GameLocalRepository())).getService();
        this.errorPage = PathContainer.redirectToErrorPage();
        this.gameRegPage = PathContainer.redirectToGameRegPage();
        this.gameScoreUpdate = PathContainer.redirectToGameScoreUpdateServlet();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(gameRegPage).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String p1 = request.getParameter("p1");
        String p2 = request.getParameter("p2");

        if (p1 == null || p2 == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("message", "Invalid parameters");
            request.getRequestDispatcher(errorPage).forward(request, response);
        }

        if (p1.equals(p2)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("message", "Player cannot play against themselves.");
            request.getRequestDispatcher(errorPage).forward(request, response);
            return;
        }

        Player firstPlayer;
        Player secondPlayer;

        if (playerService.isPlayer(p1)) {
            firstPlayer = playerService.getByKey(p1);
        } else {
            firstPlayer = new Player(p1);
            playerService.save(firstPlayer);
        }

        if (playerService.isPlayer(p2)) {
            secondPlayer = playerService.getByKey(p2);
        } else {
            secondPlayer = new Player(p2);
            playerService.save(secondPlayer);
        }

        Game game = new Game(firstPlayer, secondPlayer);
        gameLocalService.save(game);

        response.sendRedirect(gameScoreUpdate + "?uuid=" + game.getUuid());
    }
}