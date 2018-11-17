package entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "tributes", schema = "public", catalog = "postgres")
public class TributesEntity {
    private long tributeid;
    private Integer userid;
    private int gameid;
    private String status;
    private String causeofdeath;
    private Integer weaponid;
    private Short health;
    private Collection<PresentstotributesEntity> presentstotributesByTributeid;
    private UsersEntity usersByUserid;
    private GamesEntity gamesByGameid;
    private WeaponsEntity weaponsByWeaponid;
    private Collection<WeaponsingameEntity> weaponsingamesByTributeid;

    public TributesEntity() {
        presentstotributesByTributeid = new HashSet<PresentstotributesEntity>();
        weaponsingamesByTributeid = new HashSet<WeaponsingameEntity>();
    }

    public TributesEntity(Integer userid, int gameid) {
        this.userid = userid;
        this.gameid = gameid;
        this.health = 100;
        this.status = "alive";
    }

    //TODO: CONSTRAINT user_on_game UNIQUE(gameID, userID)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tributeid")
    public long getTributeid() {
        return tributeid;
    }

    public void setTributeid(long tributeid) {
        this.tributeid = tributeid;
    }

    @Basic
    @Column(name = "userid")
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Basic
    @NotNull
    @Column(name = "gameid")
    public int getGameid() {
        return gameid;
    }

    public void setGameid(int gameid) {
        this.gameid = gameid;
    }

    @Basic
    @Column(name = "status", length = 40)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "causeofdeath", length = 80)
    public String getCauseofdeath() {
        return causeofdeath;
    }

    public void setCauseofdeath(String causeofdeath) {
        this.causeofdeath = causeofdeath;
    }

    @Basic
    @Column(name = "weaponid")
    public Integer getWeaponid() {
        return weaponid;
    }

    public void setWeaponid(Integer weaponid) {
        this.weaponid = weaponid;
    }

    @Basic
    @Min(0)
    //TODO: этого нет в бд
    @Max(100)
    @Column(name = "health")
    public Short getHealth() {
        return health;
    }

    public void setHealth(Short health) {
        this.health = health;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TributesEntity that = (TributesEntity) o;

        if (tributeid != that.tributeid) return false;
        if (gameid != that.gameid) return false;
        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (causeofdeath != null ? !causeofdeath.equals(that.causeofdeath) : that.causeofdeath != null) return false;
        if (weaponid != null ? !weaponid.equals(that.weaponid) : that.weaponid != null) return false;
        if (health != null ? !health.equals(that.health) : that.health != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (tributeid ^ (tributeid >>> 32));
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        result = 31 * result + gameid;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (causeofdeath != null ? causeofdeath.hashCode() : 0);
        result = 31 * result + (weaponid != null ? weaponid.hashCode() : 0);
        result = 31 * result + (health != null ? health.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "tributesByTributeid")
    public Collection<PresentstotributesEntity> getPresentstotributesByTributeid() {
        return presentstotributesByTributeid;
    }

    public void setPresentstotributesByTributeid(Collection<PresentstotributesEntity> presentstotributesByTributeid) {
        this.presentstotributesByTributeid = presentstotributesByTributeid;
    }

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    public UsersEntity getUsersByUserid() {
        return usersByUserid;
    }

    public void setUsersByUserid(UsersEntity usersByUserid) {
        this.usersByUserid = usersByUserid;
    }

    @ManyToOne
    @JoinColumn(name = "gameid", referencedColumnName = "gameid", nullable = false)
    public GamesEntity getGamesByGameid() {
        return gamesByGameid;
    }

    public void setGamesByGameid(GamesEntity gamesByGameid) {
        this.gamesByGameid = gamesByGameid;
    }

    @ManyToOne
    @JoinColumn(name = "weaponid", referencedColumnName = "weaponid")
    public WeaponsEntity getWeaponsByWeaponid() {
        return weaponsByWeaponid;
    }

    public void setWeaponsByWeaponid(WeaponsEntity weaponsByWeaponid) {
        this.weaponsByWeaponid = weaponsByWeaponid;
    }

    @OneToMany(mappedBy = "tributesByTributeid")
    public Collection<WeaponsingameEntity> getWeaponsingamesByTributeid() {
        return weaponsingamesByTributeid;
    }

    public void setWeaponsingamesByTributeid(Collection<WeaponsingameEntity> weaponsingamesByTributeid) {
        this.weaponsingamesByTributeid = weaponsingamesByTributeid;
    }
}
