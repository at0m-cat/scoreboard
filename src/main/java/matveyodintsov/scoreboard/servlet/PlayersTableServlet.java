package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.repository.PlayerPersistenceRepository;
import matveyodintsov.scoreboard.service.PlayerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import matveyodintsov.scoreboard.util.AppConst;

import java.io.IOException;

@WebServlet("/players")
public class PlayersTableServlet extends HttpServlet {

    private PlayerService playerService;

    @Override
    public void init() throws ServletException {
        this.playerService = new PlayerService(new PlayerPersistenceRepository());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        if (page < 1) {
            page = 1;
        }

        try {
            int maxPage = Math.toIntExact(playerService.getMaxPageNum());
            request.setAttribute("players", playerService.findAllWithPage(page));
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", maxPage);
            request.getRequestDispatcher(AppConst.Route.PLAYERS_TABLE_JSP).forward(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("message", AppConst.Message.PAGE_NOT_FOUND);
            request.getRequestDispatcher(AppConst.Route.ERROR_JSP).forward(request, response);
        }
    }
}