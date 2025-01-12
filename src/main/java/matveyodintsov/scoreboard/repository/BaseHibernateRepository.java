package matveyodintsov.scoreboard.repository;

import matveyodintsov.scoreboard.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public abstract class BaseHibernateRepository<T> implements Repository<T> {

    private final Class<T> entityType;

    protected BaseHibernateRepository(Class<T> entityType) {
        this.entityType = entityType;
    }

    @Override
    public abstract T getByKey(String key);

    @Override
    public List<T> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<T> query = session.createQuery("from " + entityType.getSimpleName(), entityType);
            return query.getResultList();
        }
    }

    @Override
    public void save(T object) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(object);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(T object) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(object);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}