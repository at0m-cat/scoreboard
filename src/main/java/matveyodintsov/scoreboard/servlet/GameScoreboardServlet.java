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

        try {
            int maxPage = Math.toIntExact(gamePersistenceService.getMaxPageNum());
            request.setAttribute("games", gamePersistenceService.findAllWithPage(page));
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", maxPage);
            request.getRequestDispatcher(AppConst.Route.SCOREBOARD_JSP).forward(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("message", AppConst.Message.PAGE_NOT_FOUND);
            request.getRequestDispatcher(AppConst.Route.ERROR_JSP).forward(request, response);
        }
    }
}