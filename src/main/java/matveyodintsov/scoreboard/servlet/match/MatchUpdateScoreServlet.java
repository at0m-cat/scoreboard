package matveyodintsov.scoreboard.servlet.match;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import matveyodintsov.scoreboard.model.Match;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import matveyodintsov.scoreboard.model.Scoreboard;
import matveyodintsov.scoreboard.repository.match.MatchLocalRepository;
import matveyodintsov.scoreboard.service.MatchScoreService;
import matveyodintsov.scoreboard.service.match.MatchService;
import matveyodintsov.scoreboard.service.ServiceFactory;
import matveyodintsov.scoreboard.util.AppConst;

import java.io.IOException;

@WebServlet("/match-score")
public class MatchUpdateScoreServlet extends HttpServlet {

    private MatchService gameLocalService;

    @Override
    public void init() throws ServletException {
        this.gameLocalService = ServiceFactory.getMatchService(new MatchLocalRepository());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");
        if (uuid == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("message", AppConst.Message.ERROR_UUID);
            req.getRequestDispatcher(AppConst.Route.ERROR_JSP).forward(req, resp);
        }
        try {
            Match currentMatch = gameLocalService.getByKey(uuid);
            if (currentMatch != null) {
                req.setAttribute("currentMatch", currentMatch);
                getServletContext().getRequestDispatcher(AppConst.Route.GAME_CONTROL_JSP).forward(req, resp);
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                req.setAttribute("message", AppConst.Message.GAME_NOT_FOUND);
                req.getRequestDispatcher(AppConst.Route.ERROR_JSP).forward(req, resp);
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher(AppConst.Route.ERROR_JSP).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuidParam = request.getParameter("uuid");
        String playerNumberParam = request.getParameter("playerNumber");
        if (uuidParam == null || playerNumberParam == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("message", AppConst.Message.GAME_NOT_EXIST);
            request.getRequestDispatcher(AppConst.Route.ERROR_JSP).forward(request, response);
            return;
        }
        try {
            Match currentMatch = gameLocalService.getByKey(uuidParam);
            if (currentMatch == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                request.setAttribute("message", AppConst.Message.GAME_NOT_FOUND);
                request.getRequestDispatcher(AppConst.Route.ERROR_JSP).forward(request, response);
            }

            int playerNumber = Integer.parseInt(playerNumberParam);
            MatchScoreService matchScoreService = new MatchScoreService(currentMatch);
            matchScoreService.play(playerNumber);
            Scoreboard scoreboard = currentMatch.getScoreboard();

            String jsonResponse = String.format(
                    "{\"scoreboard\":{\"firstPlayerScore\":\"%s\",\"secondPlayerScore\":\"%s\",\"firstPlayerGames\":%d,\"secondPlayerGames\":%d,\"firstPlayerSets\":%d,\"secondPlayerSets\":%d},\"winner\":\"%s\"}",
                    scoreboard.getFirstPlayerScore(),
                    scoreboard.getSecondPlayerScore(),
                    scoreboard.getFirstPlayerGameScore(),
                    scoreboard.getSecondPlayerGameScore(),
                    scoreboard.getFirstPlayerSetScore(),
                    scoreboard.getSecondPlayerSetScore(),
                    currentMatch.getWinner() != null ? currentMatch.getWinner().getName() : "none"
            );
            response.getWriter().write(jsonResponse);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            request.setAttribute("message", AppConst.Message.PAGE_NOT_FOUND);
            request.getRequestDispatcher(AppConst.Route.ERROR_JSP).forward(request, response);
        }
    }
}