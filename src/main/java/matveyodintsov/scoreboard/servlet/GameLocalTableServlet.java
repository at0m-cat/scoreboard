package matveyodintsov.scoreboard.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import matveyodintsov.scoreboard.repository.GameLocalRepository;
import matveyodintsov.scoreboard.service.GameService;
import matveyodintsov.scoreboard.service.SingletonServiceFactory;
import matveyodintsov.scoreboard.util.AppConst;

import java.io.IOException;

@WebServlet("/local")
public class GameLocalTableServlet extends HttpServlet {

    GameService gameLocalService;

    @Override
    public void init() throws ServletException {
        this.gameLocalService = SingletonServiceFactory.getInstance(new GameService(new GameLocalRepository())).getService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        if (page < 1) {
            page = 1;
        }

        try {
            int maxPage = Math.toIntExact(gameLocalService.getMaxPageNum(null));
            request.setAttribute("games", gameLocalService.findAllWithPageAndName(null, page));
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", maxPage);
            request.getRequestDispatcher(AppConst.Route.LOCAL_GAME_TABLE_JSP).forward(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("message", AppConst.Message.PAGE_NOT_FOUND);
            request.getRequestDispatcher(AppConst.Route.ERROR_JSP).forward(request, response);
        }
    }
}
