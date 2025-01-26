package matveyodintsov.scoreboard.repository.player;

import jakarta.persistence.EntityNotFoundException;
import matveyodintsov.scoreboard.model.Player;
import matveyodintsov.scoreboard.repository.PersistenceRepository;
import matveyodintsov.scoreboard.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.*;

public class PlayerPersistenceRepository extends PersistenceRepository<Player> {

    public PlayerPersistenceRepository() {
        super(Player.class);
    }

    //TODO throw new exception else NULL getByKey

    @Override
    public Player getByKey(String playerName) throws EntityNotFoundException {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        Query<Player> query = hibernateSession.createQuery("FROM Player WHERE name = :name", Player.class);
        query.setParameter("name", playerName);
        Optional<Player> player = Optional.ofNullable(query.uniqueResult());
        hibernateSession.close();
        return player.orElseThrow(() -> new EntityNotFoundException("Player with name " + playerName + " not found"));
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

//                TODO count() already exist!

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
                hql = "from Player p order by p.id";
                query = session.createQuery(hql, Player.class);
            } else {
                hql = "from Player p where p.name LIKE :name order by p.id";
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
