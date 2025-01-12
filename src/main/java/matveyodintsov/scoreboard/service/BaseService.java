package matveyodintsov.scoreboard.service;

import matveyodintsov.scoreboard.repository.Repository;

import java.util.List;

public abstract class BaseService<T> implements Service<T> {

    protected final Repository<T> repository;

    public BaseService(Repository<T> repository) {
        this.repository = repository;
    }

    @Override
    public T getByKey(String key) {
        return repository.getByKey(key);
    }

    @Override
    public List<T> getAll() {
        return repository.getAll();
    }

    @Override
    public void save(T val) {
        repository.save(val);
    }

    @Override
    public void delete(T val) {
        repository.delete(val);
    }
}
