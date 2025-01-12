package matveyodintsov.scoreboard.repository;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Optional;
import java.util.UUID;

public class GamePersistenceRepository extends BaseHibernateRepository<Game> {

    public GamePersistenceRepository() {
        super(Game.class);
    }

    @Override
    public Game getByKey(String uuid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Game> query = session.createQuery("from Game where uuid = :uuid");
        query.setParameter("uuid", UUID.fromString(uuid));
        Optional<Game> game = Optional.ofNullable(query.uniqueResult());
        session.close();
        return game.orElse(null);
    }

}

