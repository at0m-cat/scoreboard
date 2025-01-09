package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.model.Player;
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
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Player> players = session.createQuery("FROM Player", Player.class).list();
        session.close();

        request.setAttribute("players", players);
        request.getRequestDispatcher("players-table.jsp").forward(request, response);
    }
}