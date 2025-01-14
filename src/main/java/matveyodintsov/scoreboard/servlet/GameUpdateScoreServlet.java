package matveyodintsov.scoreboard.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import matveyodintsov.scoreboard.model.Game;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import matveyodintsov.scoreboard.repository.GameLocalRepository;
import matveyodintsov.scoreboard.service.GameService;
import matveyodintsov.scoreboard.service.SingletonServiceFactory;
import matveyodintsov.scoreboard.util.StringContainer;

import java.io.IOException;

@WebServlet("/match-score")
public class GameUpdateScoreServlet extends HttpServlet {

    private GameService gameLocalService;

    @Override
    public void init() throws ServletException {
        this.gameLocalService = SingletonServiceFactory
                .getInstance(new GameService(new GameLocalRepository())).getService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");
        if (uuid == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("message", StringContainer.Message.ERROR_UUID);
            req.getRequestDispatcher(StringContainer.Route.ERROR_JSP).forward(req, resp);
        }

        try {
            Game currentGame = gameLocalService.getByKey(uuid);
            if (currentGame != null) {
                req.setAttribute("currentGame", currentGame);
                getServletContext().getRequestDispatcher(StringContainer.Route.GAME_CONTROL_JSP).forward(req, resp);
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                req.setAttribute("message", StringContainer.Message.GAME_NOT_FOUND);
                req.getRequestDispatcher(StringContainer.Route.ERROR_JSP).forward(req, resp);
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher(StringContainer.Route.ERROR_JSP).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuidParam = request.getParameter("uuid");
        Game currentGame = gameLocalService.getByKey(uuidParam);
        if (currentGame == null || uuidParam == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.setAttribute("message", StringContainer.Message.GAME_NOT_EXIST);
            request.getRequestDispatcher(StringContainer.Route.ERROR_JSP).forward(request, response);
            return;
        }

        String player = request.getParameter("player");
        String action = request.getParameter("action");
        if ("firstPlayer".equals(player)) {
            if ("increment".equals(action)) {
                currentGame.setFirstPlayerScore(currentGame.getFirstPlayerScore() + 1);
            } else if ("decrement".equals(action)) {
                currentGame.setFirstPlayerScore(Math.max(0, currentGame.getFirstPlayerScore() - 1));
            }
        } else if ("secondPlayer".equals(player)) {
            if ("increment".equals(action)) {
                currentGame.setSecondPlayerScore(currentGame.getSecondPlayerScore() + 1);
            } else if ("decrement".equals(action)) {
                currentGame.setSecondPlayerScore(Math.max(0, currentGame.getSecondPlayerScore() - 1));
            }
        }

        response.setContentType("application/json");
        response.getWriter().write(String.format("{\"firstPlayerScore\":%d,\"secondPlayerScore\":%d}",
                currentGame.getFirstPlayerScore(),
                currentGame.getSecondPlayerScore()));
    }
}