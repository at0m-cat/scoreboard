package matveyodintsov.scoreboard.servlet.match;

import matveyodintsov.scoreboard.model.Match;
import matveyodintsov.scoreboard.model.Player;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import matveyodintsov.scoreboard.repository.match.MatchLocalRepository;
import matveyodintsov.scoreboard.repository.player.PlayerPersistenceRepository;
import matveyodintsov.scoreboard.service.match.MatchService;
import matveyodintsov.scoreboard.service.player.PlayerService;
import matveyodintsov.scoreboard.service.ServiceFactory;
import matveyodintsov.scoreboard.util.AppConst;

import java.io.IOException;

@WebServlet("/new-match")
public class MatchRegisterServlet extends HttpServlet {

    private PlayerService playerService;
    private MatchService gameLocalService;

    @Override
    public void init() throws ServletException {
        this.playerService = ServiceFactory.getPlayerService(new PlayerPersistenceRepository());
        this.gameLocalService = ServiceFactory.getMatchService(new MatchLocalRepository());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(AppConst.Route.REG_JSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String p1 = request.getParameter("p1");
        String p2 = request.getParameter("p2");
        if (p1 == null || p2 == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("message", AppConst.Message.INVALID_PARAM);
            request.getRequestDispatcher(AppConst.Route.ERROR_JSP).forward(request, response);
        }

        if (p1.equals(p2)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("message", AppConst.Message.PLAYER_THEMSELVES);
            request.getRequestDispatcher(AppConst.Route.ERROR_JSP).forward(request, response);
            return;
        }

//        TODO refactoring method getOrCreate -> createOrGet!

        Player firstPlayer = playerService.getOrCreatePlayer(p1);
        Player secondPlayer = playerService.getOrCreatePlayer(p2);

        Match match = gameLocalService.createAndSaveMatchRegistration(firstPlayer, secondPlayer);

        response.sendRedirect(AppConst.Route.MATCH_SCORE_SERVLET + "?uuid=" + match.getUuid());
    }
}