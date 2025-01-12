package matveyodintsov.scoreboard.repository;

import matveyodintsov.scoreboard.model.Player;
import matveyodintsov.scoreboard.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Optional;

public class PlayerPersistenceRepository extends BaseHibernateRepository<Player> {

    public PlayerPersistenceRepository() {
        super(Player.class);
    }

    @Override
    public Player getByKey(String playerName) {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        Query<Player> query = hibernateSession.createQuery("FROM Player WHERE name = :name", Player.class);
        query.setParameter("name", playerName);
        Optional<Player> player = Optional.ofNullable(query.uniqueResult());
        hibernateSession.close();
        return player.orElse(null);
    }
}
