package matveyodintsov.scoreboard.repository;

import matveyodintsov.scoreboard.util.AppConst;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public abstract class LocalRepository<T, K> implements Repository<T> {

    protected final Map<K, T> repository;
    protected volatile List<T> cachedList;

    protected LocalRepository() {
        repository = new ConcurrentHashMap<>();
        cachedList = new ArrayList<>();
    }

    @Override
    public T getByKey(String uuid) {
        return repository.get(UUID.fromString(uuid));
    }

    @Override
    public List<T> getAll() {
        return cachedList;
    }

    @Override
    public long count() {
        return repository.size();
    }

    @Override
    public abstract void save(T object);

    @Override
    public abstract void delete(T object);


}
