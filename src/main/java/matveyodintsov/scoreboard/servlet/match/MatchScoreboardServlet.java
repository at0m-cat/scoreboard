package matveyodintsov.scoreboard.servlet.match;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import matveyodintsov.scoreboard.repository.match.MatchPersistenceRepository;
import matveyodintsov.scoreboard.service.match.MatchService;
import matveyodintsov.scoreboard.service.ServiceFactory;
import matveyodintsov.scoreboard.util.AppConst;

import java.io.IOException;

@WebServlet("/matches")
public class MatchScoreboardServlet extends HttpServlet {

    private MatchService gamePersistenceService;

    @Override
    public void init() throws ServletException {
        this.gamePersistenceService = ServiceFactory.getMatchService(new MatchPersistenceRepository());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("filter_by_player_name");
        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        if (page < 1) {
            page = 1;
        }
        try {
            int maxPage = Math.toIntExact(gamePersistenceService.getMaxPageNum(name));
            request.setAttribute("playerNameInput", name);
            request.setAttribute("matches", gamePersistenceService.findAllWithPageAndName(name, page));
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