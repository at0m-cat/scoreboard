package matveyodintsov.scoreboard.repository;

import matveyodintsov.scoreboard.model.Player;
import matveyodintsov.scoreboard.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class PlayerRepository {

    public Player findPlayerByName(String playerName) {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        Query<Player> query = hibernateSession.createQuery("FROM Player WHERE name = :name", Player.class);
        query.setParameter("name", playerName);

        return query.uniqueResultOptional().orElse(null);
    }

    public void save(Player player) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(player);
        session.getTransaction().commit();
        session.close();
    }

}
