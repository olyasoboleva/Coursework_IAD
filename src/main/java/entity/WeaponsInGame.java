package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "weapons_in_game")
public class WeaponsInGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weapon_in_game_id")
    private Integer weaponInGameId;

    @ManyToOne
    @JoinColumn(name = "tribute_id", referencedColumnName = "tribute_id")
    private Tribute tribute;

    @ManyToOne
    @JoinColumn(name = "weapon_id", referencedColumnName = "weapon_id", nullable = false)
    private Weapon weapon;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "game_id", nullable = false)
    private Game game;

    @Column(name = "location_x")
    int locationX;

    @Column(name = "location_y")
    int locationY;

    @Column(name = "active")
    boolean active;

    public WeaponsInGame(Tribute tribute, Weapon weapon) {
        setTribute(tribute);
        setWeapon(weapon);
        setGame(tribute.getGame());
        setLocationX(tribute.getLocationX());
        setLocationY(tribute.getLocationY());
        setActive(false);
    }


    public WeaponsInGame(Game game, Weapon weapon, int x, int y) {
        setWeapon(weapon);
        setGame(game);
        setLocationX(x);
        setLocationY(y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WeaponsInGame that = (WeaponsInGame) o;
        return Objects.equals(weaponInGameId, that.weaponInGameId) &&
                Objects.equals(tribute, that.tribute) &&
                Objects.equals(weapon, that.weapon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), weaponInGameId, tribute, weapon);
    }
}
