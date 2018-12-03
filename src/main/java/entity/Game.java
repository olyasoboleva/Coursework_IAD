package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "games", schema = "public", catalog = "postgres")
public class Game {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gameId")
    private int gameId;

    @Getter
    @Setter
    @Basic
    @NotNull
    @Column(name = "typeOfGame")
    private boolean typeOfGame;

    @Getter
    @Setter
    @Basic
    @Min(0)
    @Column(name = "numberOfTributes")
    private int numberOfTributes;

    @Getter
    @Setter
    @Basic
    @NotNull
    @Column(name = "startDate")
    private Date startDate;

    @Getter
    @Setter
    @Basic
    @Column(name = "duration", nullable = false)
    private int duration;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "steward", referencedColumnName = "userId", insertable = false, updatable = false)
    private User steward;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "arena", referencedColumnName = "arenaId")
    private Arena arena;

    @Getter
    @Setter
    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
    private Collection<Tribute> tributes;

    public Game() { }

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
        return gameId == that.gameId &&
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
