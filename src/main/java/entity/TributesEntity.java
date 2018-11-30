package entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "tributes",schema = "public", catalog = "postgres")
public class TributesEntity {
    private long tributeid;
    private String status;
    private String causeofdeath;
    private Short health;
    private Collection<ShopEntity> productsOfTribute;
    private UsersEntity usersByUserid;
    private GamesEntity gamesByGameid;
    private Collection<WeaponsEntity> weaponOfTribute;

    public TributesEntity() { }

    public TributesEntity(UsersEntity user, GamesEntity game) {
        this.usersByUserid = user;
        this.health = 100;
        this.status = "alive";
        this.gamesByGameid = game;
    }

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
    @Min(0)
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
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (causeofdeath != null ? !causeofdeath.equals(that.causeofdeath) : that.causeofdeath != null) return false;
        if (health != null ? !health.equals(that.health) : that.health != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (tributeid ^ (tributeid >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (causeofdeath != null ? causeofdeath.hashCode() : 0);
        result = 31 * result + (health != null ? health.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false)
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

    @ManyToMany(mappedBy = "owners", fetch = FetchType.LAZY)
    public Collection<WeaponsEntity> getWeaponOfTribute() {
        return weaponOfTribute;
    }

    public void setWeaponOfTribute(Collection<WeaponsEntity> weaponOfTribute) {
        this.weaponOfTribute = weaponOfTribute;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "presentstotributes",
            joinColumns = {@JoinColumn(name = "tributeid")},
            inverseJoinColumns = {@JoinColumn(name = "productid")}
    )
    public Collection<ShopEntity> getProductsOfTribute() {
        return productsOfTribute;
    }

    public void setProductsOfTribute(Collection<ShopEntity> productsOfTribute) {
        this.productsOfTribute = productsOfTribute;
    }
}
