package matveyodintsov.scoreboard.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import matveyodintsov.scoreboard.model.Player;
import matveyodintsov.scoreboard.repository.PlayerRepository;
import matveyodintsov.scoreboard.service.BasePlayerService;

import java.io.IOException;

@WebServlet("/player")
public class PlayerInfoServlet extends HttpServlet {

    private BasePlayerService playerService;

    @Override
    public void init() throws ServletException {
        this.playerService = new BasePlayerService(new PlayerRepository());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        try {
            Player player = playerService.getByKey(name);

            if (player == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                req.setAttribute("message", "Match not found");
                req.getRequestDispatcher("error").forward(req, resp);
            } else {
                req.setAttribute("player", player);
                getServletContext().getRequestDispatcher("/WEB-INF/player-info.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("message",e.getMessage());
            req.getRequestDispatcher("error").forward(req, resp);
        }

    }
}
