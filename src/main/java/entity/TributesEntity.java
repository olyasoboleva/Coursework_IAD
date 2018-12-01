package entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "tributes",schema = "public", catalog = "postgres")
public class TributesEntity {
    private int tributeId;
    private String status;
    private String causeOfDeath;
    private int health;
    private Collection<ShopEntity> productsOfTribute;
    private UsersEntity user;
    private GamesEntity game;
    private Collection<WeaponsEntity> weaponOfTribute;
    private Collection<UsersEntity> presentsSenders;

    public TributesEntity() { }

    public TributesEntity(UsersEntity user, GamesEntity game) {
        this.user = user;
        this.health = 100;
        this.status = "alive";
        this.game = game;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tributeId")
    public int getTributeId() {
        return tributeId;
    }

    public void setTributeId(int tributeId) {
        this.tributeId = tributeId;
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
    @Column(name = "causeOfDeath", length = 80)
    public String getCauseOfDeath() {
        return causeOfDeath;
    }

    public void setCauseOfDeath(String causeOfDeath) {
        this.causeOfDeath = causeOfDeath;
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
        return tributeId == that.tributeId &&
                health == that.health &&
                Objects.equals(status, that.status) &&
                Objects.equals(causeOfDeath, that.causeOfDeath) &&
                Objects.equals(productsOfTribute, that.productsOfTribute) &&
                Objects.equals(user, that.user) &&
                Objects.equals(game, that.game) &&
                Objects.equals(weaponOfTribute, that.weaponOfTribute);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tributeId, status, causeOfDeath, health, productsOfTribute, user, game, weaponOfTribute);
    }

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "gameId", referencedColumnName = "gameId", nullable = false)
    public GamesEntity getGame() {
        return game;
    }
    public void setGame(GamesEntity game) {
        this.game = game;
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
            name = "presentsToTributes",
            joinColumns = {@JoinColumn(name = "tributeId")},
            inverseJoinColumns = {@JoinColumn(name = "productId")}
    )
    public Collection<ShopEntity> getProductsOfTribute() {
        return productsOfTribute;
    }

    public void setProductsOfTribute(Collection<ShopEntity> productsOfTribute) {
        this.productsOfTribute = productsOfTribute;
    }

    @ManyToMany(mappedBy = "recipients", fetch = FetchType.LAZY)
    public Collection<UsersEntity> getPresentsSenders() {
        return presentsSenders;
    }

    public void setPresentsSenders(Collection<UsersEntity> presentsSenders) {
        this.presentsSenders = presentsSenders;
    }
}
