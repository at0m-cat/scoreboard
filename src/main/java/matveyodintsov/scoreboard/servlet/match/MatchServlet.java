package matveyodintsov.scoreboard.servlet.match;

import matveyodintsov.scoreboard.model.Match;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import matveyodintsov.scoreboard.repository.match.MatchPersistenceRepository;
import matveyodintsov.scoreboard.service.match.MatchService;
import matveyodintsov.scoreboard.service.ServiceFactory;
import matveyodintsov.scoreboard.util.AppConst;

import java.io.IOException;

@WebServlet("/match")
public class MatchServlet extends HttpServlet {

    private MatchService gamePersistenceService;

    @Override
    public void init() throws ServletException {
        this.gamePersistenceService = ServiceFactory.getMatchService(new MatchPersistenceRepository());
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
            Match match = gamePersistenceService.getByKey(uuid);
            if (match == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                request.setAttribute("message", AppConst.Message.GAME_NOT_FOUND);
                request.getRequestDispatcher(AppConst.Route.ERROR_JSP).forward(request, response);
            } else {
                request.setAttribute("match", match);
                getServletContext().getRequestDispatcher(AppConst.Route.MATCH_JSP).forward(request, response);
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("message",e.getMessage());
            request.getRequestDispatcher(AppConst.Route.ERROR_JSP).forward(request, response);
        }
    }
}