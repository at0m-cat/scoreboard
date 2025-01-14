package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.model.Game;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import matveyodintsov.scoreboard.repository.GamePersistenceRepository;
import matveyodintsov.scoreboard.service.GameService;
import matveyodintsov.scoreboard.util.StringContainer;

import java.io.IOException;

@WebServlet("/match")
public class GameServlet extends HttpServlet {

    private GameService gamePersistenceService;

    @Override
    public void init() throws ServletException {
        this.gamePersistenceService = new GameService(new GamePersistenceRepository());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        if (uuid == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("message", StringContainer.msgErrorUUID);
            request.getRequestDispatcher(StringContainer.redirectToErrorPage).forward(request, response);
        }

        try {
            Game game = gamePersistenceService.getByKey(uuid);
            if (game == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                request.setAttribute("message", "Match not found");
                request.getRequestDispatcher(StringContainer.redirectToErrorPage).forward(request, response);
            } else {
                request.setAttribute("game", game);
                getServletContext().getRequestDispatcher(StringContainer.redirectToSingleGamePage).forward(request, response);
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("message",e.getMessage());
            request.getRequestDispatcher(StringContainer.redirectToErrorPage).forward(request, response);
        }
    }
}