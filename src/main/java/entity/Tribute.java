package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Collection;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tribute",schema = "public", catalog = "postgres")
public class Tribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tributeId")
    private Integer tributeId;

    @Column(name = "status", length = 40)
    private String status;

    @Column(name = "causeOfDeath", length = 80)
    private String causeOfDeath;

    @Min(0)
    @Max(100)
    @Column(name = "health")
    private int health;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "presentsToTribute",
            joinColumns = {@JoinColumn(name = "tributeId")},
            inverseJoinColumns = {@JoinColumn(name = "productId")}
    )
    private Collection<Shop> productsOfTribute;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "gameId", referencedColumnName = "gameId", nullable = false)
    private Game game;

    @ManyToMany(mappedBy = "owners", fetch = FetchType.LAZY)
    private Collection<Weapon> weaponOfTribute;

    @ManyToMany(mappedBy = "recipients", fetch = FetchType.LAZY)
    private Collection<User> presentsSenders;

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
        return Objects.equals(tributeId, that.tributeId) &&
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

}
