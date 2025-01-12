package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.repository.PlayerPersistenceRepository;
import matveyodintsov.scoreboard.service.PlayerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import matveyodintsov.scoreboard.util.PathContainer;

import java.io.IOException;

@WebServlet("/players")
public class PlayersTableServlet extends HttpServlet {

    private PlayerService playerService;
    private String playersTablePage;

    @Override
    public void init() throws ServletException {
        this.playerService = new PlayerService(new PlayerPersistenceRepository());
        this.playersTablePage = PathContainer.redirectToPlayersTablePage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("players", playerService.getAll());
        request.getRequestDispatcher(playersTablePage).forward(request, response);
    }
}