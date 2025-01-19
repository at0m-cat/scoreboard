package matveyodintsov.scoreboard.repository;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.*;

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

    @Override
    public List<Game> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Game> query = session.createQuery("from Game");
        List<Game> games = query.list();
        session.close();
        if (games.isEmpty()) {
            return Collections.emptyList();
        } else {
            return games;
        }
    }

    @Override
    public long countWithName(String playerName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql;
            Query<Long> query;

            if (playerName == null || playerName.trim().isEmpty()) {
                hql = "select count(*) from Game";
                query = session.createQuery(hql, Long.class);
            } else {
                hql = "select count(*) from Game g where g.firstPlayer.name = :playerName or g.secondPlayer.name = :playerName";
                query = session.createQuery(hql, Long.class);
                query.setParameter("playerName", playerName.trim());
            }

            return query.uniqueResult();
        }
    }

    @Override
    public List<Game> findAllWithPageAndName(String playerName, int offset, int limit) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql;
            Query<Game> query;

            if (playerName == null || playerName.trim().isEmpty()) {
                hql = "from Game";
                query = session.createQuery(hql, Game.class);
            } else {
                hql = "from Game g where g.firstPlayer.name = :playerName or g.secondPlayer.name = :playerName";
                query = session.createQuery(hql, Game.class);
                query.setParameter("playerName", playerName.trim());
            }

            query.setFirstResult(offset);
            query.setMaxResults(limit);

            List<Game> result = query.getResultList();
            if (!result.isEmpty()) {
                return result;
            }

            if (count() == 0) {
                return Collections.emptyList();
            } else {
                throw new NoSuchElementException();
            }
        }
    }

}

