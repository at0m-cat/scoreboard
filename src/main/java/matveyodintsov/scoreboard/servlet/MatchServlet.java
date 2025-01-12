package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.model.Game;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import matveyodintsov.scoreboard.service.FinishedGamePersistenceService;
import matveyodintsov.scoreboard.util.PathContainer;

import java.io.IOException;

@WebServlet("/match")
public class MatchServlet extends HttpServlet {

    private FinishedGamePersistenceService gameService;
    private String errorPage;
    private String singleGamePage;

    @Override
    public void init() throws ServletException {
        this.gameService = FinishedGamePersistenceService.getInstance();
        this.errorPage = PathContainer.redirectToErrorPage();
        this.singleGamePage = PathContainer.redirectToSingleGamePage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("uuid") == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("message", "Missing required parameter 'uuid'.");
            request.getRequestDispatcher(errorPage).forward(request, response);
        }

        String uuid = request.getParameter("uuid");
        try {
            Game game = gameService.getByKey(uuid);
            if (game == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                request.setAttribute("message", "Match not found");
                request.getRequestDispatcher(errorPage).forward(request, response);
            } else {
                request.setAttribute("game", game);
                getServletContext().getRequestDispatcher(singleGamePage).forward(request, response);
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("message",e.getMessage());
            request.getRequestDispatcher(errorPage).forward(request, response);
        }
    }
}