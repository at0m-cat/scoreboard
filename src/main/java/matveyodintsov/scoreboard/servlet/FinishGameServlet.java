package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.repository.GameRepository;
import matveyodintsov.scoreboard.repository.PlayerRepository;
import matveyodintsov.scoreboard.service.GameService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import matveyodintsov.scoreboard.service.PlayerService;


import java.io.IOException;

@WebServlet("/finish-game")
public class FinishGameServlet extends HttpServlet {

    private GameService gameService;
    private PlayerService playerService;

    @Override
    public void init() throws ServletException {
        this.gameService = new GameService(new GameRepository());
        this.playerService = new PlayerService(new PlayerRepository());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String uuid = request.getParameter("uuid");
        session.removeAttribute("currentGame");
        session.removeAttribute("uuid");

        GameService localGames = (GameService) session.getAttribute("localGames");
        Game currentGame = localGames.getByKey(uuid);

        if (currentGame != null) {
            gameService.save(currentGame);
            localGames.delete(currentGame);

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

        response.sendRedirect("match-score?uuid=" + uuid);
    }
}