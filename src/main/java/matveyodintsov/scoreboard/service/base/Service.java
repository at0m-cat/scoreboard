package matveyodintsov.scoreboard.service.base;

import java.util.List;

public interface Service<T> {

    T getByKey(String key);

    List<T> getAll();

    void save(T val);

    void delete(T val);

    long getMaxPageNum(String key);

    List<T> findAllWithPageAndName(String name, int page);

}
