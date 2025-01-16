package matveyodintsov.scoreboard.service;

import java.util.List;

public interface Service<T> {

    T getByKey(String key);

    List<T> getAll();

    void save(T val);

    void delete(T val);

    long getMaxPageNum();

    List<T> findAllWithPage(int page);

}
