package matveyodintsov.scoreboard.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "first_player", nullable = false)
    private Player firstPlayer;

    @ManyToOne
    @JoinColumn(name = "second_player", nullable = false)
    private Player secondPlayer;

    @Column(name = "p1_score", nullable = false)
    private int firstPlayerScore;

    @Column(name = "p2_score", nullable = false)
    private int secondPlayerScore;

    @Column(name = "game_date", nullable = false)
    private LocalDate gameDate;

    public Game(Player firstPlayer, Player secondPlayer) {
        this.id = null;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.firstPlayerScore = 0;
        this.secondPlayerScore = 0;
        this.gameDate = LocalDate.now();
    }

}
