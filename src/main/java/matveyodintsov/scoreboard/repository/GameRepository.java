package matveyodintsov.scoreboard.repository;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class GameRepository {

    public Game findGameByUuid(String uuid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query <Game> query = session.createQuery("from Game where uuid = :uuid");
        query.setParameter("uuid", uuid);
        Optional<Game> game = Optional.ofNullable(query.uniqueResult());
        session.close();
        return game.orElse(null);
    }

    public List<Game> findAllGames() {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        hibernateSession.beginTransaction();
        Query<Game> query = hibernateSession.createQuery("from Game", Game.class);
        List<Game> games = query.getResultList();
        hibernateSession.getTransaction().commit();
        hibernateSession.close();
        return games;
    }

    public void save(Game game) {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        hibernateSession.beginTransaction();
        try {
            hibernateSession.save(game);
            hibernateSession.getTransaction().commit();
        } catch (Exception e) {
            hibernateSession.getTransaction().rollback();
        } finally {
            hibernateSession.close();
        }
    }
}

