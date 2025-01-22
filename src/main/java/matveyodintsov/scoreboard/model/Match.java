package matveyodintsov.scoreboard.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "uuid", nullable = false)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "first_player", nullable = false)
    private Player firstPlayer;

    @ManyToOne
    @JoinColumn(name = "second_player", nullable = false)
    private Player secondPlayer;

    @ManyToOne
    @JoinColumn(name = "winner")
    private Player winner;

    @Column(name = "sets_first_player", nullable = false)
    private Integer setsFirstPlayer;

    @Column(name = "sets_second_player", nullable = false)
    private Integer setsSecondPlayer = 0;

    @Column(name = "games_first_player", nullable = false)
    private Integer gamesFirstPlayer = 0;

    @Column(name = "games_second_player", nullable = false)
    private Integer gamesSecondPlayer = 0;

    @Column(name = "score_first_player", nullable = false)
    private Integer scoreFirstPlayer;

    @Column(name = "score_second_player", nullable = false)
    private Integer scoreSecondPlayer;

    @Column(name = "game_date", nullable = false)
    private LocalDate gameDate;

    public Match(Player firstPlayer, Player secondPlayer) {
        this.id = null;
        this.uuid = UUID.randomUUID();
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.setsFirstPlayer = 0;
        this.setsSecondPlayer = 0;
        this.gamesFirstPlayer =0;
        this.gamesSecondPlayer = 0;
        this.scoreFirstPlayer = 0;
        this.scoreSecondPlayer = 0;
        this.gameDate = LocalDate.now();
    }

}
