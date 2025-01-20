package matveyodintsov.scoreboard;

import matveyodintsov.scoreboard.model.Match;
import matveyodintsov.scoreboard.model.Player;
import matveyodintsov.scoreboard.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

        Player player1 = new Player("Novak Djokovic");
        Player player2 = new Player("Rafael Nadal");

        session.save(player1);
        session.save(player2);

        Match match = new Match(player1, player2);
        session.save(match);

        transaction.commit();

        session.createQuery("FROM Match", Match.class).list().forEach(g -> {
            System.out.println("Game ID: " + g.getId());
            System.out.println("First Player: " + g.getFirstPlayer().getName());
            System.out.println("Second Player: " + g.getSecondPlayer().getName());
            System.out.println("Date: " + g.getGameDate());
        });

        session.close();
        HibernateUtil.shutdown();
    }
}