package matveyodintsov.scoreboard.repository.scoreboard;

import matveyodintsov.scoreboard.model.Scoreboard;
import matveyodintsov.scoreboard.repository.PersistenceRepository;
import matveyodintsov.scoreboard.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ScoreboardPersistenceRepository extends PersistenceRepository<Scoreboard> {

    public ScoreboardPersistenceRepository() {
        super(Scoreboard.class);
    }

    //TODO throw new exception else NULL getByKey

    @Override
    public Scoreboard getByKey(String key) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Scoreboard> query = session.createQuery("from Scoreboard where uuid = :uuid");
        query.setParameter("uuid", UUID.fromString(key));
        Optional<Scoreboard> scoreboard = Optional.ofNullable(query.uniqueResult());
        session.close();
        return scoreboard.orElse(null);
    }

    @Override
    public List<Scoreboard> getAll() {
        return Collections.emptyList();
    }

    @Override
    public long countWithName(String playerName) {
        return 0;
    }

    @Override
    public List<Scoreboard> findAllWithPageAndName(String name, int offset, int pageSize) {
        return Collections.emptyList();
    }
}
