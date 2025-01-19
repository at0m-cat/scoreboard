package matveyodintsov.scoreboard.service;

import matveyodintsov.scoreboard.repository.Repository;
import matveyodintsov.scoreboard.util.AppConst;

import java.util.List;

public abstract class BaseService<T> implements Service<T> {

    protected final Repository<T> repository;
    private final int pageSize;

    public BaseService(Repository<T> repository) {
        this.repository = repository;
        this.pageSize = AppConst.Constants.PAGE_SIZE;
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
    public long getMaxPageNum(String playerName) {
        long totalItems = repository.countWithName(playerName);
        if (totalItems == 0) {
            return 0;
        }
        return (totalItems + pageSize - 1) / pageSize;
    }

    @Override
    public List<T> findAllWithPageAndName(String name, int page) {
        int offset = (page - 1) * pageSize;
        return repository.findAllWithPageAndName(name, offset, pageSize);
    }
}
