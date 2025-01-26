# Tennis scoreboard

A web application that implements a tennis match score board.

- [Rules of counting in a tennis match](https://best-tennis.ru/blog/schet-v-tennise/)
- [RoadMap by Sergey Zhukov: Tennis Scoreboard (4)](https://zhukovsd.github.io/java-backend-learning-course/projects/tennis-scoreboard/)

## Features

- **Frontend**: HTML/CSS, JavaScript
- **Backend**: HTTP - GET and POST requests. The project is written on java servlets.
- **Database**: Hibernate, H2 (in-memory SQL database)
- **Tests**: JUnit5

## Application map

|         Page          | METHOD | Link                                  | Note                          |
|:---------------------:|:------:|:--------------------------------------|-------------------------------|
|       Main page       | `GET`  | /                                     |                               |
|       New match       | `GET`  | /new-match                            |                               |
|        Players        | `GET`  | /players                              |                               |
|        Players        | `GET`  | /players?page=`int`                   | Pagination                    |
|        Players        | `GET`  | /players?filter_by_player_name=`name` | Find player by `name`         |
|    Player details     | `GET`  | /player?name=`name`                   |                               |
|    Finished match     | `GET`  | /matches                              |                               |
|    Finished match     | `GET`  | /matches?page=`int`                   | Pagination                    |
|    Finished match     | `GET`  | /matches?filter_by_player_name=`name` | Find match by `name` p1 or p2 |
|     Match details     | `GET`  | /match?uuid=`MatchUuid`               |                               |
| Last registered match | `GET`  | /local                                |                               |
|     Match control     | `POST` | /match-score?uuid=`MatchLocalUuid`    |                               |

# Pages info

## Main

Home page, which has buttons to navigate to the following pages: **new match**,
**players**, **finished match**, **last registered match**

![Image](https://github.com/user-attachments/assets/f84cf177-28d9-4a7f-995c-aa4e8a450028)

---

## New match

- Is a page with a form for registering a match. The page displays two fields for entering
  the names of the players and one button for registering the match, after clicking which the match will start.

![Image](https://github.com/user-attachments/assets/5d0a3537-1868-46a3-87d7-de941d6f49de)

---

## Match control

- Redirect to this page after successful registration. The match is managed on this page. A table with the values of
  sets, games, and player points is
  displayed. A "+1" button is provided in the player's row to increase the player's score.

![Image](https://github.com/user-attachments/assets/2f7d5be2-5fb2-4474-9b66-58cf0e4286aa)

- The demonstration of the "ADVANTAGE" game mode begins with an equal score of 40:40. With a score of 40:40, the next
  point scored gives an advantage, and with another win, this player wins. If the opponent wins the next point, the
  score will return at 40:40

![Image](https://github.com/user-attachments/assets/127b73fd-774f-48a5-9e38-0d869795281d)

- Demonstration of the "Tiebreak" mode. It is activated when the game value is 6:6. In this mode, the score is
  different: from 1 to 7. To win in this mode, you must reach 7 points first and have a two-point difference. If the
  score is greater than 7, the game continues until there is a two-point difference.

![Image](https://github.com/user-attachments/assets/a93bf5be-9d80-49ca-ba81-e81dacd3bcb1)

---

## Match details

- The page that displays the results of the completed match: the winner (in a green frame) and
  the loser, as well as game statistics: the number of sets of players.

![Image](https://github.com/user-attachments/assets/3cd467a2-4a4e-48a3-babe-c1ef1b365aa8)

- By clicking on the player, you can go to the "player details" page

![Image](https://github.com/user-attachments/assets/ff362e6c-ad2a-40c4-9c8d-d8eb7834914e)

---

## Players

- A page with all registered players. The page implements pagination and search for the player by name.

![Image](https://github.com/user-attachments/assets/28dbef71-4039-4020-aa70-c6c606f9c23a)

- By clicking on a player from the table, you can go to the "player details" page

---

## Player details

- A page with information about the player, it displays the statistics of the player: the
  number of matches played, defeats and wins.

![Image](https://github.com/user-attachments/assets/d35846df-657b-4d06-96fa-720c3e1c3f11)

---

## Finished match

- A page that displays a table with matches that have been completed. The page implements
  pagination and match search by the name of one of the players.

![Image](https://github.com/user-attachments/assets/1701ec90-4e6d-4d95-b22c-6dad1f9043de)

- By clicking on a match from the table, you can go to the "Match details" page.

---

- Demonstration of the match search by the name of one of the players. For example, we will find matches with Andy
  Murray.

![Image](https://github.com/user-attachments/assets/4b54e7d6-62f3-42c0-b459-89e63923659a)
![Image](https://github.com/user-attachments/assets/bf5c15cf-1b3a-445e-8808-5a248ef974c1)

---

## Last 7 registered match

- The page that displays the history of the last 7 registered matches. The items in the table are not clickable.

![Image](https://github.com/user-attachments/assets/1202c6a5-3598-4d63-8115-9f39d214ddad)

---

# Backend

## Entity

- `Match` - an entity that stores its id, uuid, match players, and winner.
- `Player` - an entity that stores its id, match statistics (wins, losses, matches played).
- `Scoreboard` - an entity that stores the sets of players in a match.

---

## Diagram Description

![Image](https://github.com/user-attachments/assets/9fd48da9-1cef-4a6d-9cce-28b383cb1dae)
This diagram represents the structure of the `MATCH`, `PLAYERS`, and `SCOREBOARD` entities and their relationships.

### Classes

#### **MATCH**

- **Attributes:**
    - `GAME_DATE: date` — The date of the match.
    - `FIRST_PLAYER: bigint` — The ID of the first player.
    - `SECOND_PLAYER: bigint` — The ID of the second player.
    - `WINNER: bigint` — The ID of the winner.
    - `UUID: uuid` — A unique identifier for the match.
    - `ID: integer` — The match ID.
- **Relationships:**
    - Linked to `PLAYERS` via `FIRST_PLAYER`, `SECOND_PLAYER`, and `WINNER`.

#### **PLAYERS**

- **Attributes:**
    - `LOSSES: integer` — The number of losses for the player.
    - `MATCHES: integer` — The number of matches played.
    - `WINS: integer` — The number of wins.
    - `NAME: character varying(255)` — The name of the player.
    - `ID: bigint` — The player ID.
- **Relationships:**
    - Linked to `MATCH` via `ID`.

#### **SCOREBOARD**

- **Attributes:**
    - `P1_SET: integer` — The number of sets won by the first player.
    - `P2_SET: integer` — The number of sets won by the second player.
    - `UUID: uuid` — A unique identifier for the scoreboard.
    - `ID: integer` — The scoreboard ID.
- **Relationships:**
    - No explicit relationships with other classes are shown in the diagram.

---

### Relationships

- `MATCH` is linked to `PLAYERS` through three attributes:
    - `FIRST_PLAYER:ID` — Relationship with the first player.
    - `SECOND_PLAYER:ID` — Relationship with the second player.
    - `WINNER:ID` — Relationship with the match winner.

---

## Servlet

|          Servlet          | METHOD | Note                                                                                                   |
|:-------------------------:|:------:|:-------------------------------------------------------------------------------------------------------|
|   `MatchFinishServlet`    | `POST` | Recording of the completed match in the database                                                       |
| `MatchLocalTableServlet`  | `GET`  | Displays the history of the started matches                                                            |
|  `MatchRegisterServlet`   | `GET`  | Redirect to the registration page                                                                      |
|  `MatchRegisterServlet`   | `POST` | Registration of matches in the local repository                                                        |
| `MatchScoreboardServlet`  | `GET`  | Displays completed matches from the Persistent repository (H2)                                         |
| `MatchUpdateScoreServlet` | `GET`  | Match management, the "Match control" page                                                             |
| `MatchUpdateScoreServlet` | `POST` | Updates the score board on the "Match control" page                                                    |
|    `PlayerInfoServlet`    | `GET`  | Displays a page with information about the user (number of wins, losses, and participation in matches) |
|   `PlayersTableServlet`   | `GET`  | Displays all registered players                                                                        |

---

## Repository

- Two repositories: `PersistentceRepository` and `LocalRepository`. Implement interface `Repository`.

```java
public interface Repository<T> {

    T getByKey(String key);

    List<T> getAll();

    void save(T object);

    void delete(T object);

    long count();

    long countWithName(String playerName);

    List<T> findAllWithPageAndName(String name, int offset, int pageSize);

}
```

---

### LocalRepository

- The `ConcurrentHashMap` serves as the repository's "storage device".
- The essence of a local repository is to store objects in the application's memory. It is used to store registered
  matches. Upon completion, the match is saved in a persistent repository and deleted in a local one.

```java
public abstract class LocalRepository<T, K> implements Repository<T> {

    protected final Map<K, T> repository;
    protected volatile List<T> cachedList;

    protected LocalRepository() {
        repository = new ConcurrentHashMap<>();
        cachedList = new ArrayList<>();
    }
```

- `cachedList` required for the history of registered matches.

---

### PersistenceRepository

- The Persistence repository is used to store data in the H2 database.

```java
public abstract class PersistenceRepository<T> implements Repository<T> {

    private final Class<T> entityType;

    protected PersistenceRepository(Class<T> entityType) {
        this.entityType = entityType;
    }
```

---

## Service

- `MainService` - An abstract class that implements the `Service` interface.
- `MatchService` , `PlayerService`, `ScoreboardService` - CRUD operations, pagination, and name search. Extends
  `MainService`.
- `MatchScoreService` - He is responsible for managing the game process in the match, including scoring, updating the
  score on the `Scoreboard` and ending the match when the winner is revealed.
- `ServiceFactory` - Singleton. A service factory for managing the creation and reuse of service instances to avoid
  redundant
  object creation. The class uses ConcurrentHashMap to store the created service instances. The key for each service is
  a string consisting of the name of the service class and the name of the repository class passed to the method. If the
  service for this key already exists, it is returned from the cache. If not, a new instance is created and stored in
  the cache.

---

### Service interface

```java
public interface Service<T> {

    T getByKey(String key);

    List<T> getAll();

    void save(T val);

    void delete(T val);

    long getMaxPageNum(String key);

    List<T> findAllWithPageAndName(String name, int page);

}
```

---

### MainService

```java
public abstract class MainService<T> implements Service<T> {

    protected final Repository<T> repository;
    private final int pageSize;

    public MainService(Repository<T> repository) {
        this.repository = repository;
        this.pageSize = AppConst.Constants.PAGE_SIZE;
    }
```

---

## The logic of the match score

- The code for calculating the match score is taken from [here](https://youtu.be/mI7SICN0ekc?si=4QbH53l562VP4o5F)

- `Score` - Abstract class. Provides basic functionality for account management in the game. It abstracts away the
  storage and updating
  of the score, leaving the implementation of the logic of winning a point and determining the state of the game at the
  discretion of the subclasses.
- `RegularGameScore`, `SetScore`, `TieBreakGameScore`, `MatchScore` - They implement the scoring logic and inherit the
  Score class

---

### `Score` abstract class

```java
public abstract class Score<T> {

    private final List<T> playerScores = new ArrayList<>();

    protected abstract T getZeroScore();

    public Score() {
        playerScores.add(getZeroScore());
        playerScores.add(getZeroScore());
    }

    public T getPlayerScore(int playerNumber) {
        return playerScores.get(playerNumber);
    }

    public T getOpponentPlayerScore(int playerNumber) {
        return playerScores.get(playerNumber == 0 ? 1 : 0);
    }

    public void setPlayerScores(int playerNumber, T playerScore) {
        playerScores.set(playerNumber, playerScore);
    }

    public void setOpponentPlayerScores(int playerNumber, T playerScore) {
        playerScores.set(playerNumber == 0 ? 1 : 0, playerScore);
    }

    public abstract State pointWon(int playerNumber);
}
```

---

### `RegularGameScore` class

- When creating a `RegularGameScore` object, the wallpaper score of the players is initialized with the value
  `RegularGamePlayerPoints.ZERO`.
- **Point processing Logic:**
    - The `pointWon(int playerNumber)` method determines how the score changes when a point is won:
        - If the player's current score is less than or equal to `THIRTY`, the score increases by one value (**for
          example**, `ZERO` → `FIFTEEN`, `FIFTEEN` → `THIRTY`).
        - If the player's current account is `FORTY`:
            - If the opponent has an `ADVANTAGE`, the opponent's score increases to `FORTY`.
            - If he is an opponent of even `FORTY`, the player gets an `ADVANTAGE`.
            - In the opposite case, the player wins the game.
        - If the player's current score is `ADVANTAGE`, he wins the game.
- **Return value:**
    - The method returns the game state:
        - `State.ONGOING` - the game continues.
        - `State.PLAYER_ONE_WON` or `State.PLAYER_TWO_WON` - the player has won the game.

```java

@Override
public State pointWon(int playerNumber) {
    RegularGamePlayerPoints playerScore = getPlayerScore(playerNumber);
    RegularGamePlayerPoints opponentScore = getOpponentPlayerScore(playerNumber);

    if (playerScore.ordinal() <= RegularGamePlayerPoints.THIRTY.ordinal()) {
        setPlayerScores(playerNumber, playerScore.next());
        return State.ONGOING;
    }

    if (playerScore == RegularGamePlayerPoints.FORTY) {
        if (opponentScore == RegularGamePlayerPoints.ADVANTAGE) {
            setOpponentPlayerScores(playerNumber, RegularGamePlayerPoints.FORTY);
        } else if (opponentScore == RegularGamePlayerPoints.FORTY) {
            setPlayerScores(playerNumber, RegularGamePlayerPoints.ADVANTAGE);
        } else {
            return playerNumber == 0 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON;
        }
        return State.ONGOING;
    }

    if (playerScore == RegularGamePlayerPoints.ADVANTAGE) {
        return playerNumber == 0 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON;
    }
    throw new IllegalStateException();
}
```

---

### `SetScore` class

- When creating a `SetScore` object, the score of both players is initialized to zero values.
- The `currentGame` is initialized as a `RegularGameScore`.
- **Processing of winning a point:**
    - The `pointWon(int playerNumber)` method calls the `pointWon` method for the current game (`currentGame`).
    - If the game is completed (the player has won the game), the `gameWon(playerNumber)` method is called.
- **Game win processing:**
    - The `gameWon(int playerNumber)` method increases the player's score in the set.
    - If the score becomes `6`-`6`, the current game switches to `TieBreakGameScore` (tie-break).
    - If a player wins a set (score >= `6` and difference >= `2`), the `State` of `PLAYER_ONE_WON` or `PLAYER_TWO_WON`
      is returned.
    - Otherwise, the current game is reset to the `RegularGameScore`.

```java

@Override
public State pointWon(int playerNumber) {
    State gameState = currentGame.pointWon(playerNumber);
    if (gameState == State.PLAYER_ONE_WON) {
        return gameWon(0);
    } else if (gameState == State.PLAYER_TWO_WON) {
        return gameWon(1);
    }
    return State.ONGOING;
}

private State gameWon(int playerNumber) {
    setPlayerScores(playerNumber, getPlayerScore(playerNumber) + 1);
    int opponentScore = getOpponentPlayerScore(playerNumber);

    if (getPlayerScore(playerNumber) == 6 && opponentScore == 6) {
        this.currentGame = new TieBreakGameScore();
        return State.ONGOING;
    }
    if (getPlayerScore(playerNumber) >= 6 && getPlayerScore(playerNumber) - opponentScore >= 2) {
        return playerNumber == 0 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON;
    }
    this.currentGame = new RegularGameScore();
    return State.ONGOING;
}
```

---

### `TieBreakGameScore` class

- When creating a `TieBreakGameScore` object, the score of both players is initialized with zero values.
- **Point processing logic:**
    - The `pointWon(int playerNumber)` method increases the player's score by `1`.
    - If the player's score is >= `7` and the difference with the opponent is >= `2`, the player wins the tie-break.
    - Otherwise, the game continues.

```java

@Override
public State pointWon(int playerNumber) {
    int playerScore = getPlayerScore(playerNumber);
    int opponentScore = getOpponentPlayerScore(playerNumber);

    setPlayerScores(playerNumber, playerScore + 1);
    if (getPlayerScore(playerNumber) >= 7 && getPlayerScore(playerNumber) - opponentScore >= 2) {
        return playerNumber == 0 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON;
    }
    return State.ONGOING;
}
```

---

### `MatchScore` class

- When creating a `MatchScore` object, the score of both players is initialized with zero values.
- The current set (`currentScore`) is initialized as a new `SetScore` object.
- **Processing of winning a point:**
    - The `pointWon(int playerNumber)` method calls the `pointWon` method for the `currentScore`.
    - If the set is completed (the player has won the set), the `matchWon(int playerNumber)` method is called.
- **Processing of winning a set:**
    - The `matchWon(int playerNumber)` method increases the player's score in the match.
    - The current set is reset to a new `SetScore` object.
    - If a player wins `3` sets, he wins the match.

```java

@Override
public State pointWon(int playerNumber) {
    State state = currentScore.pointWon(playerNumber);
    if (state == State.PLAYER_ONE_WON) {
        return matchWon(0);
    } else if (state == State.PLAYER_TWO_WON) {
        return matchWon(1);
    }
    return State.ONGOING;
}

private State matchWon(int playerNumber) {
    setPlayerScores(playerNumber, getPlayerScore(playerNumber) + 1);
    this.currentScore = new SetScore();
    if (getPlayerScore(playerNumber) == 3) {
        if (playerNumber == 0) {
            return State.PLAYER_ONE_WON;
        } else {
            return State.PLAYER_TWO_WON;
        }
    }
    return State.ONGOING;
}
```

---