package matveyodintsov.scoreboard.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "wins", nullable = false)
    private int totalWins;

    @Column(name = "losses", nullable = false)
    private int totalLosses;

    @Column(name = "matches", nullable = false)
    private int totalMatches;

    public Player(String name) {
        this.id = null;
        this.name = name;
        this.totalWins = 0;
        this.totalLosses = 0;
        this.totalMatches = 0;
    }
}
