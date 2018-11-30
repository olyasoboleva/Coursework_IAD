package entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Table(name = "tributes",schema = "public", catalog = "postgres")
public class TributesEntity {
    private int tributeid;
    private String status;
    private String causeofdeath;
    private int health;
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
    public int getTributeid() {
        return tributeid;
    }

    public void setTributeid(int tributeid) {
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
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TributesEntity that = (TributesEntity) o;
        return tributeid == that.tributeid &&
                health == that.health &&
                Objects.equals(status, that.status) &&
                Objects.equals(causeofdeath, that.causeofdeath) &&
                Objects.equals(productsOfTribute, that.productsOfTribute) &&
                Objects.equals(usersByUserid, that.usersByUserid) &&
                Objects.equals(gamesByGameid, that.gamesByGameid) &&
                Objects.equals(weaponOfTribute, that.weaponOfTribute);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tributeid, status, causeofdeath, health, productsOfTribute, usersByUserid, gamesByGameid, weaponOfTribute);
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
