package matveyodintsov.scoreboard.repository;

import java.util.List;

public interface Repository<T> {

    public T getByKey(String key);

    public List<T> getAll();

    public void save(T object);

    public void delete(T object);

}
