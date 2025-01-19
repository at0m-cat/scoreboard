package matveyodintsov.scoreboard.repository;

import java.util.List;

public interface Repository<T> {

    T getByKey(String key);

    List<T> getAll();

    void save(T object);

    void delete(T object);

    long count();

    long countWithName(String playerName);

    List<T> findAllWithPageAndName(String name, int offset, int pageSize);

}
