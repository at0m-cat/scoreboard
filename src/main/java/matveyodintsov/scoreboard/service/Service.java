package matveyodintsov.scoreboard.service;

import java.util.List;

public interface Service<T> {

    public T getByKey(String key);

    public List<T> getAll();

    public void save(T val);

}
