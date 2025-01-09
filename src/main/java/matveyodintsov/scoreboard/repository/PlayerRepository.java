package matveyodintsov.scoreboard.repository;

import matveyodintsov.scoreboard.model.Player;
import matveyodintsov.scoreboard.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class PlayerRepository {

    public Player findPlayerByName(String playerName) {
            Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
            Query<Player> query = hibernateSession.createQuery("FROM Player WHERE name = :name", Player.class);
            query.setParameter("name", playerName);
            Optional<Player> player = Optional.ofNullable(query.uniqueResult());
            hibernateSession.close();
            return player.orElse(null);
    }

    public Player findByName(String playerName) {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        Query<Player> query = hibernateSession.createQuery("FROM Player WHERE name = :name", Player.class);
        query.setParameter("name", playerName);
        Optional<Player> player = Optional.ofNullable(query.uniqueResult());
        hibernateSession.close();
        return player.orElse(null);
    }

    public List<Player> findAllPlayers() {
            Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
            Query<Player> query = hibernateSession.createQuery("FROM Player", Player.class);
            List<Player> players = query.list();
            hibernateSession.close();
            return players;
    }

    public void save(Player player) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.save(player);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

}
