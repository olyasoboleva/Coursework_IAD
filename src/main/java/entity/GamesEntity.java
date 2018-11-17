package entity;

import javax.persistence.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "games", schema = "public", catalog = "postgres")
public class GamesEntity {
    private int gameid;
    private boolean typeofgame;
    private Integer steward;
    private Integer arena;
    private Short numberoftributes;
    private Date startdate;
    private int duration;
    private UsersEntity usersBySteward;
    private ArenasEntity arenasByArena;
    private Collection<TributesEntity> tributesByGameid;

    public GamesEntity() {
        tributesByGameid = new HashSet<TributesEntity>();
    }

    public GamesEntity(boolean typeofgame, Integer steward, Integer arena, Short numberoftributes, Date startdate) {
        this.typeofgame = typeofgame;
        this.steward = steward;
        this.arena = arena;
        this.numberoftributes = numberoftributes;
        this.startdate = startdate;
        tributesByGameid = new HashSet<TributesEntity>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gameid")
    public int getGameid() {
        return gameid;
    }

    public void setGameid(int gameid) {
        this.gameid = gameid;
    }

    @Basic
    @NotNull
    @Column(name = "typeofgame")
    public boolean isTypeofgame() {
        return typeofgame;
    }

    public void setTypeofgame(boolean typeofgame) {
        this.typeofgame = typeofgame;
    }

    @Basic
    @Column(name = "steward")
    public Integer getSteward() {
        return steward;
    }

    public void setSteward(Integer steward) {
        this.steward = steward;
    }

    @Basic
    @Column(name = "arena")
    public Integer getArena() {
        return arena;
    }

    public void setArena(Integer arena) {
        this.arena = arena;
    }

    @Basic
    @Min(0)
    @Column(name = "numberoftributes")
    public Short getNumberoftributes() {
        return numberoftributes;
    }

    public void setNumberoftributes(Short numberoftributes) {
        this.numberoftributes = numberoftributes;
    }

    @Basic
    @NotNull
    @Column(name = "startdate")
    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    @Basic
    //TODO: not null
    @Column(name = "duration")
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

        if (gameid != that.gameid) return false;
        if (typeofgame != that.typeofgame) return false;
        if (duration != that.duration) return false;
        if (steward != null ? !steward.equals(that.steward) : that.steward != null) return false;
        if (arena != null ? !arena.equals(that.arena) : that.arena != null) return false;
        if (numberoftributes != null ? !numberoftributes.equals(that.numberoftributes) : that.numberoftributes != null)
            return false;
        if (startdate != null ? !startdate.equals(that.startdate) : that.startdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = gameid;
        result = 31 * result + (typeofgame ? 1 : 0);
        result = 31 * result + (steward != null ? steward.hashCode() : 0);
        result = 31 * result + (arena != null ? arena.hashCode() : 0);
        result = 31 * result + (numberoftributes != null ? numberoftributes.hashCode() : 0);
        result = 31 * result + (startdate != null ? startdate.hashCode() : 0);
        result = 31 * result + duration;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "steward", referencedColumnName = "userid", insertable = false, updatable = false)
    public UsersEntity getUsersBySteward() {
        return usersBySteward;
    }

    public void setUsersBySteward(UsersEntity usersBySteward) {
        this.usersBySteward = usersBySteward;
    }

    @ManyToOne
    @JoinColumn(name = "arena", referencedColumnName = "arenaid", insertable = false, updatable = false)
    public ArenasEntity getArenasByArena() {
        return arenasByArena;
    }

    public void setArenasByArena(ArenasEntity arenasByArena) {
        this.arenasByArena = arenasByArena;
    }

    @OneToMany(mappedBy = "gamesByGameid")
    public Collection<TributesEntity> getTributesByGameid() {
        return tributesByGameid;
    }

    public void setTributesByGameid(Collection<TributesEntity> tributesByGameid) {
        this.tributesByGameid = tributesByGameid;
    }
}
