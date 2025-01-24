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

    @Column(name = "game_date", nullable = false)
    private LocalDate gameDate;

    @Transient
    private Scoreboard scoreboard = null;

    public Match(Player firstPlayer, Player secondPlayer) {
        this.id = null;
        this.uuid = UUID.randomUUID();
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.gameDate = LocalDate.now();
    }

}
