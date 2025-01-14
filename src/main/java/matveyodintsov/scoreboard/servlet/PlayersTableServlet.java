package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.repository.PlayerPersistenceRepository;
import matveyodintsov.scoreboard.service.PlayerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import matveyodintsov.scoreboard.util.StringContainer;

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
        request.setAttribute("players", playerService.getAll());
        request.getRequestDispatcher(StringContainer.Route.PLAYERS_TABLE_JSP).forward(request, response);
    }
}