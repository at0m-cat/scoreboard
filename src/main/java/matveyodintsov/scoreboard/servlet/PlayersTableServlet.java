package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.repository.PlayerRepository;
import matveyodintsov.scoreboard.service.BasePlayerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/players")
public class PlayersTableServlet extends HttpServlet {

    private BasePlayerService playerService;

    @Override
    public void init() throws ServletException {
        this.playerService = new BasePlayerService(new PlayerRepository());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("players", playerService.getAll());
        request.getRequestDispatcher("WEB-INF/players-table.jsp").forward(request, response);
    }
}