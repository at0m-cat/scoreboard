package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.repository.GameLocalRepository;
import matveyodintsov.scoreboard.repository.GamePersistenceRepository;
import matveyodintsov.scoreboard.repository.PlayerPersistenceRepository;
import matveyodintsov.scoreboard.service.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import matveyodintsov.scoreboard.util.StringContainer;


import java.io.IOException;

@WebServlet("/finish-game")
public class GameFinishServlet extends HttpServlet {

    private PlayerService playerService;
    private GameService gameLocalService;
    private GameService gamePersistenceService;

    @Override
    public void init() throws ServletException {
        this.playerService = new PlayerService(new PlayerPersistenceRepository());
        this.gamePersistenceService = new GameService(new GamePersistenceRepository());
        this.gameLocalService = SingletonServiceFactory.getInstance(new GameService(new GameLocalRepository())).getService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        request.removeAttribute("uuid");

        Game currentGame = gameLocalService.getByKey(uuid);
        if (currentGame != null) {
            gamePersistenceService.save(currentGame);
            gameLocalService.delete(currentGame);

            currentGame.getFirstPlayer().setTotalMatches(currentGame.getFirstPlayer().getTotalMatches() + 1);
            currentGame.getSecondPlayer().setTotalMatches(currentGame.getSecondPlayer().getTotalMatches() + 1);
            if (currentGame.getWinner().equals("firstPlayer")) {
                currentGame.getFirstPlayer().setTotalWins(currentGame.getFirstPlayer().getTotalWins() + 1);
            }
            if (currentGame.getWinner().equals("secondPlayer")) {
                currentGame.getSecondPlayer().setTotalWins(currentGame.getSecondPlayer().getTotalWins() + 1);
            }

            playerService.save(currentGame.getFirstPlayer());
            playerService.save(currentGame.getSecondPlayer());
        }

        response.sendRedirect(StringContainer.Route.MATCH_SERVLET + "?uuid=" + uuid);
    }
}