package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.model.Game;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import matveyodintsov.scoreboard.repository.GamePersistenceRepository;
import matveyodintsov.scoreboard.service.GameService;
import matveyodintsov.scoreboard.util.AppConst;

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
            request.setAttribute("message", AppConst.Message.ERROR_UUID);
            request.getRequestDispatcher(AppConst.Route.ERROR_JSP).forward(request, response);
        }

        try {
            Game game = gamePersistenceService.getByKey(uuid);
            if (game == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                request.setAttribute("message", AppConst.Message.GAME_NOT_FOUND);
                request.getRequestDispatcher(AppConst.Route.ERROR_JSP).forward(request, response);
            } else {
                request.setAttribute("game", game);
                getServletContext().getRequestDispatcher(AppConst.Route.MATCH_JSP).forward(request, response);
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("message",e.getMessage());
            request.getRequestDispatcher(AppConst.Route.ERROR_JSP).forward(request, response);
        }
    }
}