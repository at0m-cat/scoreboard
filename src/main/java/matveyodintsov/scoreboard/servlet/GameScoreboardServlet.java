package matveyodintsov.scoreboard.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import matveyodintsov.scoreboard.repository.GamePersistenceRepository;
import matveyodintsov.scoreboard.service.GameService;
import matveyodintsov.scoreboard.util.AppConst;

import java.io.IOException;

@WebServlet("/matches")
public class GameScoreboardServlet extends HttpServlet {

    private GameService gamePersistenceService;

    @Override
    public void init() throws ServletException {
        this.gamePersistenceService = new GameService(new GamePersistenceRepository());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        if (page < 1) {
            page = 1;
        }
        request.setAttribute("games", gamePersistenceService.getPage(page));
        request.getRequestDispatcher(AppConst.Route.SCOREBOARD_JSP).forward(request, response);
    }
}