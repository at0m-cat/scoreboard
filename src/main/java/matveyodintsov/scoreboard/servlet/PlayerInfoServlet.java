package matveyodintsov.scoreboard.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import matveyodintsov.scoreboard.model.Player;
import matveyodintsov.scoreboard.repository.PlayerRepository;
import matveyodintsov.scoreboard.service.BasePlayerService;
import matveyodintsov.scoreboard.util.PathContainer;

import java.io.IOException;

@WebServlet("/player")
public class PlayerInfoServlet extends HttpServlet {

    private BasePlayerService playerService;
    private String errorPage;

    @Override
    public void init() throws ServletException {
        this.playerService = new BasePlayerService(new PlayerRepository());
        this.errorPage = PathContainer.redirectToErrorPage();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("name") != null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("message", "Name cannot be empty");
            req.getRequestDispatcher(errorPage).forward(req, resp);
        }

        String name = req.getParameter("name");

        try {
            Player player = playerService.getByKey(name);

            if (player == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                req.setAttribute("message", "Player not found");
                req.getRequestDispatcher(errorPage).forward(req, resp);
            } else {
                req.setAttribute("player", player);
                getServletContext().getRequestDispatcher("/WEB-INF/player-info.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("message",e.getMessage());
            req.getRequestDispatcher(errorPage).forward(req, resp);
        }

    }
}
