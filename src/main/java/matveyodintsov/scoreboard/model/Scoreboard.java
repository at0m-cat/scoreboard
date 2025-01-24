package matveyodintsov.scoreboard.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import matveyodintsov.scoreboard.calculating.RegularGamePlayerPoints;
import matveyodintsov.scoreboard.calculating.score.MatchScore;
import matveyodintsov.scoreboard.calculating.score.RegularGameScore;
import matveyodintsov.scoreboard.calculating.score.SetScore;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "scoreboard")
public class Scoreboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "uuid", nullable = false)
    private UUID uuid;

    @Column(name = "p1_game")
    private Integer firstPlayerGameScore = 0;

    @Column(name = "p2_game")
    private Integer secondPlayerGameScore = 0;

    @Column(name = "p1_set")
    private Integer firstPlayerSetScore = 0;

    @Column(name = "p2_set")
    private Integer secondPlayerSetScore = 0;

    @Transient
    private String firstPlayerScore = RegularGamePlayerPoints.ZERO.name();

    @Transient
    private String secondPlayerScore = RegularGamePlayerPoints.ZERO.name();

    @Transient
    private MatchScore matchScore = new MatchScore();

    @Transient
    private SetScore setScore = new SetScore();

    @Transient
    private RegularGameScore regularGameScore = new RegularGameScore();

    public Scoreboard(UUID uuid) {
        this.uuid = uuid;
    }
}
