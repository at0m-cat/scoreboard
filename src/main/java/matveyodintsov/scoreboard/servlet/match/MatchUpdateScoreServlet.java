package matveyodintsov.scoreboard.servlet.match;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import matveyodintsov.scoreboard.model.Match;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import matveyodintsov.scoreboard.repository.match.MatchLocalRepository;
import matveyodintsov.scoreboard.service.game.MatchService;
import matveyodintsov.scoreboard.service.factory.ServiceFactory;
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
        Match currentMatch = gameLocalService.getByKey(uuidParam);
        if (currentMatch == null || uuidParam == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.setAttribute("message", AppConst.Message.GAME_NOT_EXIST);
            request.getRequestDispatcher(AppConst.Route.ERROR_JSP).forward(request, response);
            return;
        }

        // todo: написать калькулятор ведения игры!

        String player = request.getParameter("player");
        String action = request.getParameter("action");
        if ("firstPlayer".equals(player)) {
            if ("increment".equals(action)) {
                currentMatch.setFirstPlayerScore(currentMatch.getFirstPlayerScore() + 1);
            } else if ("decrement".equals(action)) {
                currentMatch.setFirstPlayerScore(Math.max(0, currentMatch.getFirstPlayerScore() - 1));
            }
        } else if ("secondPlayer".equals(player)) {
            if ("increment".equals(action)) {
                currentMatch.setSecondPlayerScore(currentMatch.getSecondPlayerScore() + 1);
            } else if ("decrement".equals(action)) {
                currentMatch.setSecondPlayerScore(Math.max(0, currentMatch.getSecondPlayerScore() - 1));
            }
        }

        response.setContentType("application/json");
        response.getWriter().write(String.format("{\"firstPlayerScore\":%d,\"secondPlayerScore\":%d}",
                currentMatch.getFirstPlayerScore(),
                currentMatch.getSecondPlayerScore()));
    }
}