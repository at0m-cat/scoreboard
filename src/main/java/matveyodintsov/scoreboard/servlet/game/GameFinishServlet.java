package matveyodintsov.scoreboard.servlet.game;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.repository.game.GameLocalRepository;
import matveyodintsov.scoreboard.repository.game.GamePersistenceRepository;
import matveyodintsov.scoreboard.repository.player.PlayerPersistenceRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import matveyodintsov.scoreboard.service.factory.ServiceFactory;
import matveyodintsov.scoreboard.service.game.GameService;
import matveyodintsov.scoreboard.service.player.PlayerService;
import matveyodintsov.scoreboard.util.AppConst;


import java.io.IOException;

@WebServlet("/finish-game")
public class GameFinishServlet extends HttpServlet {

    private PlayerService playerService;
    private GameService gameLocalService;
    private GameService gamePersistenceService;

    @Override
    public void init() throws ServletException {
        this.playerService = ServiceFactory.getPlayerService(new PlayerPersistenceRepository());
        this.gamePersistenceService = ServiceFactory.getGameService(new GamePersistenceRepository());
        this.gameLocalService = ServiceFactory.getGameService(new GameLocalRepository());
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

        response.sendRedirect(AppConst.Route.MATCH_SERVLET + "?uuid=" + uuid);
    }
}