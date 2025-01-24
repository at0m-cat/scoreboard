package matveyodintsov.scoreboard.servlet.match;

import matveyodintsov.scoreboard.model.Match;
import matveyodintsov.scoreboard.repository.match.MatchLocalRepository;
import matveyodintsov.scoreboard.repository.match.MatchPersistenceRepository;
import matveyodintsov.scoreboard.repository.player.PlayerPersistenceRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import matveyodintsov.scoreboard.repository.scoreboard.ScoreboardPersistenceRepository;
import matveyodintsov.scoreboard.service.ServiceFactory;
import matveyodintsov.scoreboard.service.match.MatchService;
import matveyodintsov.scoreboard.service.player.PlayerService;
import matveyodintsov.scoreboard.service.scoreboard.ScoreboardService;
import matveyodintsov.scoreboard.util.AppConst;


import java.io.IOException;

@WebServlet("/finish-game")
public class MatchFinishServlet extends HttpServlet {

    private PlayerService playerPersistenceService;
    private ScoreboardService scoreboardService;
    private MatchService matchLocalService;
    private MatchService matchPersistenceService;

    @Override
    public void init() throws ServletException {
        this.playerPersistenceService = ServiceFactory.getPlayerService(new PlayerPersistenceRepository());
        this.scoreboardService = ServiceFactory.getScoreboardService(new ScoreboardPersistenceRepository());
        this.matchPersistenceService = ServiceFactory.getMatchService(new MatchPersistenceRepository());
        this.matchLocalService = ServiceFactory.getMatchService(new MatchLocalRepository());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        request.removeAttribute("uuid");

        Match currentMatch = matchLocalService.getByKey(uuid);
        if (currentMatch != null) {

            currentMatch.getFirstPlayer().setTotalMatches(currentMatch.getFirstPlayer().getTotalMatches() + 1);
            currentMatch.getSecondPlayer().setTotalMatches(currentMatch.getSecondPlayer().getTotalMatches() + 1);

            if (currentMatch.getWinner().equals(currentMatch.getFirstPlayer())) {
                currentMatch.getFirstPlayer().setTotalWins(currentMatch.getFirstPlayer().getTotalWins() + 1);
                currentMatch.getSecondPlayer().setTotalLosses(currentMatch.getSecondPlayer().getTotalLosses() + 1);
            }
            if (currentMatch.getWinner().equals(currentMatch.getSecondPlayer())) {
                currentMatch.getSecondPlayer().setTotalWins(currentMatch.getFirstPlayer().getTotalWins() + 1);
                currentMatch.getFirstPlayer().setTotalLosses(currentMatch.getFirstPlayer().getTotalLosses() + 1);
            }

            playerPersistenceService.save(currentMatch.getFirstPlayer());
            playerPersistenceService.save(currentMatch.getSecondPlayer());
            scoreboardService.save(currentMatch.getScoreboard());
            matchPersistenceService.save(currentMatch);

            matchLocalService.delete(currentMatch);
        }

        response.sendRedirect(AppConst.Route.MATCH_SERVLET + "?uuid=" + uuid);
    }
}