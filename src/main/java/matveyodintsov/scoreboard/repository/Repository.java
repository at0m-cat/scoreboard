package matveyodintsov.scoreboard.repository;

import java.util.List;

public interface Repository<T> {

    T getByKey(String key);

    List<T> getAll();

    void save(T object);

    void delete(T object);

    long count();

    List<T> findAllWithPage(int offset, int pageSize);

}
