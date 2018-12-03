package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "tributes",schema = "public", catalog = "postgres")
public class Tribute {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tributeId")
    private int tributeId;

    @Getter
    @Setter

    @Basic
    @Column(name = "status", length = 40)
    private String status;

    @Getter
    @Setter
    @Basic
    @Column(name = "causeOfDeath", length = 80)
    private String causeOfDeath;

    @Getter
    @Setter
    @Basic
    @Min(0)
    @Max(100)
    @Column(name = "health")
    private int health;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "presentsToTributes",
            joinColumns = {@JoinColumn(name = "tributeId")},
            inverseJoinColumns = {@JoinColumn(name = "productId")}
    )
    private Collection<Shop> productsOfTribute;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    private User user;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "gameId", referencedColumnName = "gameId", nullable = false)
    private Game game;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "owners", fetch = FetchType.LAZY)
    private Collection<Weapon> weaponOfTribute;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "recipients", fetch = FetchType.LAZY)
    private Collection<User> presentsSenders;

    public Tribute() { }

    public Tribute(User user, Game game) {
        this.user = user;
        this.health = 100;
        this.status = "alive";
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tribute that = (Tribute) o;
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


    /*@ManyToMany(mappedBy = "owners", fetch = FetchType.LAZY)
    public Collection<Weapon> getWeaponOfTribute() {
        return weaponOfTribute;
    }

    public void setWeaponOfTribute(Collection<Weapon> weaponOfTribute) {
        this.weaponOfTribute = weaponOfTribute;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "presentsToTributes",
            joinColumns = {@JoinColumn(name = "tributeId")},
            inverseJoinColumns = {@JoinColumn(name = "productId")}
    )
    public Collection<Shop> getProductsOfTribute() {
        return productsOfTribute;
    }
    public void setProductsOfTribute(Collection<Shop> productsOfTribute) {
        this.productsOfTribute = productsOfTribute;
    }

    @ManyToMany(mappedBy = "recipients", fetch = FetchType.LAZY)
    public Collection<User> getPresentsSenders() {
        return presentsSenders;
    }
    public void setPresentsSenders(Collection<User> presentsSenders) {
        this.presentsSenders = presentsSenders;
    }*/
}
