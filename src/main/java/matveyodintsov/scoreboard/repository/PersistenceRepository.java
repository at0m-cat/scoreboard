package matveyodintsov.scoreboard.repository;

import matveyodintsov.scoreboard.util.AppConst;
import matveyodintsov.scoreboard.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public abstract class PersistenceRepository<T> implements Repository<T> {

    private final Class<T> entityType;

    protected PersistenceRepository(Class<T> entityType) {
        this.entityType = entityType;
    }

    @Override
    public abstract T getByKey(String key);

    @Override
    public void save(T object) {
        if (object == null) {
            throw new IllegalArgumentException(AppConst.Message.CANNOT_SAVE);
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(object);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(AppConst.Message.CANNOT_SAVE);
        }
    }

    @Override
    public void delete(T object) {
        if (object == null) {
            throw new IllegalArgumentException(AppConst.Message.FAILED_DELETE);
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(object);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(AppConst.Message.FAILED_DELETE);
        }
    }

    @Override
    public long count() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("select count(*) from " + entityType.getSimpleName(), Long.class);
            return query.uniqueResult();
        }
    }
}