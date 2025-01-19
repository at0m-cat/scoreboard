package matveyodintsov.scoreboard.repository;

import matveyodintsov.scoreboard.model.Player;
import matveyodintsov.scoreboard.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.*;

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

    @Override
    public List<Player> getAll() {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        Query<Player> query = hibernateSession.createQuery("FROM Player", Player.class);
        List<Player> players = query.getResultList();
        hibernateSession.close();
        if (players.isEmpty()) {
            return Collections.emptyList();
        } else {
            return players;
        }
    }

    @Override
    public long countWithName(String playerName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql;
            Query<Long> query;

            if (playerName == null || playerName.trim().isEmpty()) {
                hql = "select count(*) from Player ";
                query = session.createQuery(hql, Long.class);
            } else {
                hql = "select count(*) from Player where name LIKE :name";
                query = session.createQuery(hql, Long.class);
                query.setParameter("name", playerName.trim());
            }

            return query.uniqueResult();
        }
    }

    @Override
    public List<Player> findAllWithPageAndName(String playerName, int offset, int limit) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            if (count() == 0) {
                return Collections.emptyList();
            }

            String hql;
            Query<Player> query;

            if (playerName == null || playerName.trim().isEmpty()) {
                hql = "from Player";
                query = session.createQuery(hql, Player.class);
            } else {
                hql = "from Player where name LIKE :name";
                query = session.createQuery(hql, Player.class);
                query.setParameter("name", playerName.trim());
            }

            query.setFirstResult(offset);
            query.setMaxResults(limit);

            List<Player> result = query.getResultList();
            if (!result.isEmpty()) {
                return result;
            } else {
                throw new NoSuchElementException();
            }
        }
    }
}
