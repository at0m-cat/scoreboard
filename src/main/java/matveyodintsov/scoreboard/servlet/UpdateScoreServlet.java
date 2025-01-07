package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.util.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

@WebServlet("/update-score")
public class UpdateScoreServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String playerId = request.getParameter("player");
        String reset = request.getParameter("reset");

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            if ("reset".equals(reset)) {
                session.createQuery("UPDATE Game SET score = 0").executeUpdate();
            } else if (playerId != null) {
                Game game = session.get(Game.class, Integer.parseInt(playerId));
                if (game != null) {
                }
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new ServletException(e);
        } finally {
            session.close();
        }

        response.sendRedirect("scoreboard");
    }
}