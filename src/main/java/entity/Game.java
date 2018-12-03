package entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "game", schema = "public", catalog = "postgres")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gameId")
    private Integer gameId;

    @NotNull
    @Column(name = "typeOfGame")
    private boolean typeOfGame;

    @Min(0)
    @Column(name = "numberOfTributes")
    private int numberOfTributes;

    @NotNull
    @Column(name = "startDate")
    private Date startDate;

    @Column(name = "duration", nullable = false)
    private int duration;

    @ManyToOne
    @JoinColumn(name = "steward", referencedColumnName = "userId", insertable = false, updatable = false)
    private User steward;

    @OneToOne
    @JoinColumn(name = "arena", referencedColumnName = "arenaId")
    private Arena arena;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
    private Collection<Tribute> tributes;

    public Game(boolean typeOfGame, User steward, Arena arena, int numberOfTributes, Date startDate) {
        this.typeOfGame = typeOfGame;
        this.numberOfTributes = numberOfTributes;
        this.startDate = startDate;
        this.steward = steward;
        this.arena = arena;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game that = (Game) o;
        return Objects.equals(gameId,that.gameId) &&
                typeOfGame == that.typeOfGame &&
                numberOfTributes == that.numberOfTributes &&
                duration == that.duration &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(steward, that.steward) &&
                Objects.equals(arena, that.arena) &&
                Objects.equals(tributes, that.tributes);
    }

    @Override
    public int hashCode() {

        return Objects.hash(gameId, typeOfGame, numberOfTributes, startDate, duration, steward, arena, tributes);
    }

}
