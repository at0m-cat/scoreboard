package matveyodintsov.scoreboard.repository;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Queue;

public class GameRepository {

    public List<Game> findAllGames() {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        hibernateSession.beginTransaction();
        Query<Game> query = hibernateSession.createQuery("from Game", Game.class);
        List<Game> games = query.getResultList();
        hibernateSession.getTransaction().commit();
        hibernateSession.close();
        return games;
    }

}
