package matveyodintsov.scoreboard.service;

import matveyodintsov.scoreboard.model.Player;
import matveyodintsov.scoreboard.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class PlayerService {

    public Player findPlayerByName(String playerName) {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        Query<Player> query = hibernateSession.createQuery("FROM Player WHERE name = :name", Player.class);
        query.setParameter("name", playerName);

        return query.uniqueResultOptional().orElse(null);
    }

    public boolean isPlayer(String playerName) {
        return findPlayerByName(playerName) != null;
    }
}
