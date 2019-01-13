package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "tribute")
public class Tribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tribute_id")
    private Integer tributeId;

    @Column(name = "status", length = 40)
    private String status;

    @Column(name = "cause_of_death", length = 80)
    private String causeOfDeath;

    @Column(name = "health")
    private int health = 100;
    @Column(name = "hunger")
    private int hunger =100;
    @Column(name = "thirst")
    private int thirst = 100;
    @Column(name = "location_x")
    private int locationX;
    @Column(name = "location_y")
    private int locationY;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "presents_to_tribute",
            joinColumns = {@JoinColumn(name = "tribute_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private Collection<Shop> productsOfTribute;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "game_id", nullable = false)
    private Game game;

    @ManyToMany(mappedBy = "owners", fetch = FetchType.LAZY)
    private Collection<Weapon> weaponOfTribute;

    @JsonIgnore
    @ManyToMany(mappedBy = "recipients", fetch = FetchType.LAZY)
    private Collection<User> presentsSenders;

    public Tribute(User user, Game game) {
        this.user = user;
        this.status = "alive";
        this.game = game;
        this.locationX = (int)(Math.random()*game.getArena().getArenaLength());
        this.locationY = (int)(Math.random()*game.getArena().getArenaWidth());
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
