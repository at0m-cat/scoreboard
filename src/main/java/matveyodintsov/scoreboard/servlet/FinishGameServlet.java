package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.util.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

@WebServlet("/finish-game")
public class FinishGameServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Game currentGame = (Game) session.getAttribute("currentGame");

        if (currentGame != null) {
            Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = hibernateSession.beginTransaction();

            try {
                hibernateSession.save(currentGame.getFirstPlayer());
                hibernateSession.save(currentGame.getSecondPlayer());
                hibernateSession.save(currentGame);

                transaction.commit();
                session.removeAttribute("currentGame");

            } catch (Exception e) {
                transaction.rollback();
                throw new ServletException("Error saving game to database.", e);

            } finally {
                hibernateSession.close();
            }
        }

        response.sendRedirect("scoreboard");
    }
}