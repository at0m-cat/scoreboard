package matveyodintsov.scoreboard.service;

import matveyodintsov.scoreboard.repository.Repository;
import matveyodintsov.scoreboard.util.StringContainer;

import java.util.List;

public abstract class BaseService<T> implements Service<T> {

    protected final Repository<T> repository;
    private final int PAGE_SIZE;

    public BaseService(Repository<T> repository) {
        this.repository = repository;
        this.PAGE_SIZE = StringContainer.Constants.PAGE_SIZE;
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

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public List<T> getPage(int page) {
        int offset = (page - 1) * PAGE_SIZE;
        return repository.findAllWithPage(offset, PAGE_SIZE);
    }
}
