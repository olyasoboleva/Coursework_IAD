package entity;

import javax.persistence.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "games", schema = "public", catalog = "postgres")
public class GamesEntity {
    private int gameId;
    private boolean typeOfGame;
    private int numberOfTributes;
    private Date startDate;
    private int duration;
    private UsersEntity steward;
    private ArenasEntity arena;
    private Collection<TributesEntity> tributes;

    public GamesEntity() { }

    public GamesEntity(boolean typeOfGame, UsersEntity steward, ArenasEntity arena, int numberOfTributes, Date startDate) {
        this.typeOfGame = typeOfGame;
        this.numberOfTributes = numberOfTributes;
        this.startDate = startDate;
        this.steward = steward;
        this.arena = arena;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gameId")
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    @Basic
    @NotNull
    @Column(name = "typeOfGame")
    public boolean isTypeOfGame() {
        return typeOfGame;
    }

    public void setTypeOfGame(boolean typeOfGame) {
        this.typeOfGame = typeOfGame;
    }

    @Basic
    @Min(0)
    @Column(name = "numberOfTributes")
    public int getNumberOfTributes() {
        return numberOfTributes;
    }

    public void setNumberOfTributes(int numberOfTributes) {
        this.numberOfTributes = numberOfTributes;
    }

    @Basic
    @NotNull
    @Column(name = "startDate")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "duration", nullable = false)
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GamesEntity that = (GamesEntity) o;
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

    @ManyToOne
    @JoinColumn(name = "steward", referencedColumnName = "userId", insertable = false, updatable = false)
    public UsersEntity getSteward() {
        return steward;
    }
    public void setSteward(UsersEntity steward) {
        this.steward = steward;
    }


    @OneToOne
    @JoinColumn(name = "arena", referencedColumnName = "arenaId")
    public ArenasEntity getArena() {
        return arena;
    }
    public void setArena(ArenasEntity arena) {
        this.arena = arena;
    }

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
    public Collection<TributesEntity> getTributes() {
        return tributes;
    }
    public void setTributes(Collection<TributesEntity> tributes) {
        this.tributes = tributes;
    }
}
