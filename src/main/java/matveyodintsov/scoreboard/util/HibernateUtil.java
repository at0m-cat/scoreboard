package matveyodintsov.scoreboard.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static volatile SessionFactory sessionFactory;

    private HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            synchronized (HibernateUtil.class) {
                if (sessionFactory == null) {
                    try {
                        sessionFactory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .buildSessionFactory();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            synchronized (HibernateUtil.class) {
                if (sessionFactory != null) {
                    sessionFactory.close();
                    sessionFactory = null;
                }
            }
        }
    }
}