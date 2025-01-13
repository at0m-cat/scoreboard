package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.model.Game;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import matveyodintsov.scoreboard.repository.GameLocalRepository;
import matveyodintsov.scoreboard.service.GameService;
import matveyodintsov.scoreboard.service.SingletonServiceFactory;
import matveyodintsov.scoreboard.util.PathContainer;

import java.io.IOException;

@WebServlet("/match-score")
public class GameUpdateScoreServlet extends HttpServlet {

    private String errorPage;
    private String gameControlPage;
    private GameService gameLocalService;

    @Override
    public void init() throws ServletException {
        this.gameLocalService = SingletonServiceFactory.getInstance(new GameService(new GameLocalRepository())).getService();
        this.errorPage = PathContainer.redirectToErrorPage();
        this.gameControlPage = PathContainer.redirectToGameControlPage();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("uuid") == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("message", "Missing required parameter 'uuid'.");
            req.getRequestDispatcher(errorPage).forward(req, resp);
        }

        String uuid = req.getParameter("uuid");
        try {
            Game currentGame = gameLocalService.getByKey(uuid);
            if (currentGame != null) {
                req.setAttribute("currentGame", currentGame);
                getServletContext().getRequestDispatcher(gameControlPage).forward(req, resp);
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                req.setAttribute("message", "Match does not exist.");
                req.getRequestDispatcher(errorPage).forward(req, resp);
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher(errorPage).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuidParam = request.getParameter("uuid");
        Game currentGame = gameLocalService.getByKey(uuidParam);

        if (currentGame == null || uuidParam == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.setAttribute("message", "Match does not exist.");
            request.getRequestDispatcher(errorPage).forward(request, response);
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