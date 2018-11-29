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
    private Short numberoftributes;
    private Date startdate;
    private int duration;
    private UsersEntity usersBySteward;
    private ArenasEntity arenasByArena;
    private Collection<TributesEntity> tributesByGameid;

    public GamesEntity() { }

    public GamesEntity(boolean typeofgame, UsersEntity steward, ArenasEntity arena, Short numberoftributes, Date startdate) {
        this.typeofgame = typeofgame;
        this.numberoftributes = numberoftributes;
        this.startdate = startdate;
        this.usersBySteward = steward;
        this.arenasByArena = arena;
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

        if (gameid != that.gameid) return false;
        if (typeofgame != that.typeofgame) return false;
        if (duration != that.duration) return false;
        if (numberoftributes != null ? !numberoftributes.equals(that.numberoftributes) : that.numberoftributes != null)
            return false;
        if (startdate != null ? !startdate.equals(that.startdate) : that.startdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = gameid;
        result = 31 * result + (typeofgame ? 1 : 0);
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


    @OneToOne
    @JoinColumn(name = "arena", referencedColumnName = "arenaid")

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
