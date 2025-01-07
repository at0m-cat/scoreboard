package matveyodintsov.scoreboard.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "game_date", nullable = false)
    private LocalDate gameDate;
}
