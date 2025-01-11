package matveyodintsov.scoreboard.repository;

import matveyodintsov.scoreboard.model.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameLocalRepository implements Repository<Game> {

    private Map<Long, Game> games = new HashMap<>();

    @Override
    public Game getByKey(String uuid) {
        return games.get(uuid);
    }

    @Override
    public List<Game> getAll() {
        return new ArrayList<>(games.values());
    }

    @Override
    public void save(Game object) {
        games.put(object.getUuid(), object);
    }

    @Override
    public void delete(Game object) {
        games.remove(object.getUuid());
    }
}
