package matveyodintsov.scoreboard.service;

import matveyodintsov.scoreboard.repository.Repository;

public class SingletonServiceFactory<T, R extends Repository<T>, S extends BaseService<T>> {

    private static volatile SingletonServiceFactory<?, ?, ?> instance;
    private final S service;

    private SingletonServiceFactory(S service) {
        this.service = service;
    }

    public static <T, R extends Repository<T>, S extends BaseService<T>> SingletonServiceFactory<T, R, S> getInstance(S service) {
        if (instance == null) {
            synchronized (SingletonServiceFactory.class) {
                if (instance == null) {
                    instance = new SingletonServiceFactory<>(service);
                }
            }
        }
        return (SingletonServiceFactory<T, R, S>) instance;
    }

    public S getService() {
        return service;
    }
}
