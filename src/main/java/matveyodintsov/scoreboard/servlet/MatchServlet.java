package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.repository.GameRepository;
import matveyodintsov.scoreboard.service.BaseGameService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/match-score")
public class MatchServlet extends HttpServlet {

    private BaseGameService gameService;

    @Override
    public void init() throws ServletException {
        this.gameService = new BaseGameService(new GameRepository());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");

        try {
            Game game = gameService.getByKey(uuid);

            if (game == null) {
                // todo: изменить, бросать ошибку

                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                request.setAttribute("message", "Match not found");
                request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
            } else {
                request.setAttribute("game", game);
                getServletContext().getRequestDispatcher("/WEB-INF/single-match.jsp").forward(request, response);
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("message",e.getMessage());
            request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
        }
    }
}