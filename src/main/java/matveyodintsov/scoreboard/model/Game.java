package matveyodintsov.scoreboard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Game {
    Integer id;
    Player firstPlayer;
    Player secondPlayer;
    LocalDate gameDate;
}
