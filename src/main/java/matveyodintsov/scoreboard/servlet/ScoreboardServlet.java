package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.util.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.hibernate.Session;

import java.io.IOException;
import java.util.List;

@WebServlet("/scoreboard")
public class ScoreboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Game> games = session.createQuery("FROM Game", Game.class).list();
        session.close();

        request.setAttribute("games", games);
        request.getRequestDispatcher("match-score.jsp").forward(request, response);
    }
}