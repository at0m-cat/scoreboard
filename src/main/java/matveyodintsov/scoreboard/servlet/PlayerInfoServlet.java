package matveyodintsov.scoreboard.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import matveyodintsov.scoreboard.model.Player;
import matveyodintsov.scoreboard.repository.PlayerPersistenceRepository;
import matveyodintsov.scoreboard.service.PlayerService;
import matveyodintsov.scoreboard.util.PathContainer;

import java.io.IOException;

@WebServlet("/player")
public class PlayerInfoServlet extends HttpServlet {

    private PlayerService playerService;
    private String errorPage;
    private String playerInfoPage;

    @Override
    public void init() throws ServletException {
        this.playerService = new PlayerService(new PlayerPersistenceRepository());
        this.errorPage = PathContainer.redirectToErrorPage();
        this.playerInfoPage = PathContainer.redirectToPlayerPage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("name") == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("message", "Name cannot be empty");
            request.getRequestDispatcher(errorPage).forward(request, response);
        }

        String name = request.getParameter("name");
        try {
            Player player = playerService.getByKey(name);

            if (player == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                request.setAttribute("message", "Player not found");
                request.getRequestDispatcher(errorPage).forward(request, response);
            } else {
                request.setAttribute("player", player);
                getServletContext().getRequestDispatcher(playerInfoPage).forward(request, response);
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("message",e.getMessage());
            request.getRequestDispatcher(errorPage).forward(request, response);
        }

    }
}
