package matveyodintsov.scoreboard;

import matveyodintsov.scoreboard.model.Player;
import matveyodintsov.scoreboard.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();
        Player player = new Player(0, "Vasya Pupkin", 1);
        session.save(player);
        transaction.commit();

        session.createQuery("FROM Player", Player.class).list()
                .forEach(p -> System.out.println("Player: " + p.getName() + ", Scores: " + p.getScore()));

        session.close();
        HibernateUtil.shutdown();
    }
}