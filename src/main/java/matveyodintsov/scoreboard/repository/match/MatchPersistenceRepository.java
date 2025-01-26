package matveyodintsov.scoreboard.repository.match;

import jakarta.persistence.EntityNotFoundException;
import matveyodintsov.scoreboard.model.Match;
import matveyodintsov.scoreboard.repository.PersistenceRepository;
import matveyodintsov.scoreboard.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.*;

public class MatchPersistenceRepository extends PersistenceRepository<Match> {

    public MatchPersistenceRepository() {
        super(Match.class);
    }

    //TODO throw new exception else NULL getByKey

    @Override
    public Match getByKey(String uuid) throws EntityNotFoundException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Match> query = session.createQuery("from Match where uuid = :uuid");
        query.setParameter("uuid", UUID.fromString(uuid));
        Optional<Match> game = Optional.ofNullable(query.uniqueResult());
        session.close();
        return game.orElseThrow(() -> new EntityNotFoundException("Could not find match with uuid " + uuid));
    }

    @Override
    public List<Match> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Match> query = session.createQuery("from Match");
        List<Match> matches = query.list();
        session.close();
        if (matches.isEmpty()) {
            return Collections.emptyList();
        } else {
            return matches;
        }
    }

    @Override
    public long countWithName(String playerName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql;
            Query<Long> query;

            if (playerName == null || playerName.trim().isEmpty()) {

//                 TODO count() already exist!

                hql = "select count(*) from Match";
                query = session.createQuery(hql, Long.class);
            } else {
                hql = "select count(*) from Match g where g.firstPlayer.name = :playerName or g.secondPlayer.name = :playerName";
                query = session.createQuery(hql, Long.class);
                query.setParameter("playerName", playerName.trim());
            }

            return query.uniqueResult();
        }
    }

    @Override
    public List<Match> findAllWithPageAndName(String playerName, int offset, int limit) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            if (count() == 0) {
                return Collections.emptyList();
            }

            String hql;
            Query<Match> query;

            if (playerName == null || playerName.trim().isEmpty()) {
                hql = "from Match m order by m.id desc";
                query = session.createQuery(hql, Match.class);
            } else {
                hql = "from Match m where m.firstPlayer.name = :playerName or m.secondPlayer.name = :playerName order by m.id";
                query = session.createQuery(hql, Match.class);
                query.setParameter("playerName", playerName.trim());
            }

            query.setFirstResult(offset);
            query.setMaxResults(limit);

            List<Match> result = query.getResultList();
            if (!result.isEmpty()) {
                return result;
            } else {
                throw new NoSuchElementException();
            }
        }
    }

}

