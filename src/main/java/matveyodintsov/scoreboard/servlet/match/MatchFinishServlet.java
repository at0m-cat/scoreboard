package matveyodintsov.scoreboard.servlet.match;

import matveyodintsov.scoreboard.model.Match;
import matveyodintsov.scoreboard.repository.match.MatchLocalRepository;
import matveyodintsov.scoreboard.repository.match.MatchPersistenceRepository;
import matveyodintsov.scoreboard.repository.player.PlayerPersistenceRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import matveyodintsov.scoreboard.service.factory.ServiceFactory;
import matveyodintsov.scoreboard.service.match.MatchService;
import matveyodintsov.scoreboard.service.player.PlayerService;
import matveyodintsov.scoreboard.util.AppConst;


import java.io.IOException;

@WebServlet("/finish-game")
public class MatchFinishServlet extends HttpServlet {

    private PlayerService playerPersistenceService;
    private MatchService matchLocalService;
    private MatchService matchPersistenceService;

    @Override
    public void init() throws ServletException {
        this.playerPersistenceService = ServiceFactory.getPlayerService(new PlayerPersistenceRepository());
        this.matchPersistenceService = ServiceFactory.getMatchService(new MatchPersistenceRepository());
        this.matchLocalService = ServiceFactory.getMatchService(new MatchLocalRepository());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        request.removeAttribute("uuid");

        Match currentMatch = matchLocalService.getByKey(uuid);
        if (currentMatch != null) {
            matchPersistenceService.save(currentMatch);
            matchLocalService.delete(currentMatch);

            currentMatch.getFirstPlayer().setTotalMatches(currentMatch.getFirstPlayer().getTotalMatches() + 1);
            currentMatch.getSecondPlayer().setTotalMatches(currentMatch.getSecondPlayer().getTotalMatches() + 1);
            if (currentMatch.getWinner().equals("firstPlayer")) {
                currentMatch.getFirstPlayer().setTotalWins(currentMatch.getFirstPlayer().getTotalWins() + 1);
            }
            if (currentMatch.getWinner().equals("secondPlayer")) {
                currentMatch.getSecondPlayer().setTotalWins(currentMatch.getSecondPlayer().getTotalWins() + 1);
            }

            playerPersistenceService.save(currentMatch.getFirstPlayer());
            playerPersistenceService.save(currentMatch.getSecondPlayer());
        }

        response.sendRedirect(AppConst.Route.MATCH_SERVLET + "?uuid=" + uuid);
    }
}