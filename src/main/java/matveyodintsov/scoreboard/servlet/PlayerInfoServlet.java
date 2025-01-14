package matveyodintsov.scoreboard.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import matveyodintsov.scoreboard.model.Player;
import matveyodintsov.scoreboard.repository.PlayerPersistenceRepository;
import matveyodintsov.scoreboard.service.PlayerService;
import matveyodintsov.scoreboard.util.StringContainer;

import java.io.IOException;

@WebServlet("/player")
public class PlayerInfoServlet extends HttpServlet {

    private PlayerService playerService;

    @Override
    public void init() throws ServletException {
        this.playerService = new PlayerService(new PlayerPersistenceRepository());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("message", StringContainer.Message.PLAYER_NAME_EMPTY);
            request.getRequestDispatcher(StringContainer.Route.ERROR_JSP).forward(request, response);
        }

        try {
            Player player = playerService.getByKey(name);

            if (player == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                request.setAttribute("message", StringContainer.Message.PLAYER_NOT_FOUND);
                request.getRequestDispatcher(StringContainer.Route.ERROR_JSP).forward(request, response);
            } else {
                request.setAttribute("player", player);
                getServletContext().getRequestDispatcher(StringContainer.Route.PLAYER_INFO_JSP).forward(request, response);
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher(StringContainer.Route.ERROR_JSP).forward(request, response);
        }
    }
}
