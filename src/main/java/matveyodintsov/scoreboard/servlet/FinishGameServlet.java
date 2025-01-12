package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.repository.PlayerRepository;
import matveyodintsov.scoreboard.service.FinishedGamePersistenceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import matveyodintsov.scoreboard.service.OngoingGameService;
import matveyodintsov.scoreboard.service.BasePlayerService;
import matveyodintsov.scoreboard.util.PathContainer;


import java.io.IOException;

@WebServlet("/finish-game")
public class FinishGameServlet extends HttpServlet {

//    private GameService gameService;
    private BasePlayerService playerService;
    private OngoingGameService ongoingGameService;
    private FinishedGamePersistenceService finishedGamePersistenceService;
    private String scoreboardServlet;

    @Override
    public void init() throws ServletException {
//        this.gameService = new GameService(new GameRepository());
        this.playerService = new BasePlayerService(new PlayerRepository());
        this.ongoingGameService = OngoingGameService.getInstance();
        this.finishedGamePersistenceService = FinishedGamePersistenceService.getInstance();
        this.scoreboardServlet = PathContainer.redirectToSingleMatchServlet();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String uuid = request.getParameter("uuid");
        session.removeAttribute("currentGame");
        session.removeAttribute("uuid");

        Game currentGame = ongoingGameService.getByKey(uuid);

        if (currentGame != null) {
            finishedGamePersistenceService.save(currentGame);
            ongoingGameService.delete(currentGame);

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

        response.sendRedirect(scoreboardServlet + "?uuid=" + uuid);
    }
}