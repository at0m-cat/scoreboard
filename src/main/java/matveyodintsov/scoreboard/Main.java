package matveyodintsov.scoreboard;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.model.Player;
import matveyodintsov.scoreboard.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

        Player player1 = new Player(null, "Novak Djokovic", 1);
        Player player2 = new Player(null, "Rafael Nadal", 2);

        session.save(player1);
        session.save(player2);

        Game game = new Game(null, player1, player2, LocalDate.now());
        session.save(game);

        transaction.commit();

        session.createQuery("FROM Game", Game.class).list().forEach(g -> {
            System.out.println("Game ID: " + g.getId());
            System.out.println("First Player: " + g.getFirstPlayer().getName());
            System.out.println("Second Player: " + g.getSecondPlayer().getName());
            System.out.println("Date: " + g.getGameDate());
        });

        session.close();
        HibernateUtil.shutdown();
    }
}