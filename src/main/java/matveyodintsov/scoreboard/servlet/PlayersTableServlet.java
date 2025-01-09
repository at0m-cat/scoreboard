package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.model.Player;
import matveyodintsov.scoreboard.repository.PlayerRepository;
import matveyodintsov.scoreboard.service.PlayerService;
import matveyodintsov.scoreboard.util.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.hibernate.Session;

import java.io.IOException;
import java.util.List;

@WebServlet("/players")
public class PlayersTableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlayerService playerService = new PlayerService();
        request.setAttribute("players", playerService.getPlayers());
        request.getRequestDispatcher("players-table.jsp").forward(request, response);
    }
}