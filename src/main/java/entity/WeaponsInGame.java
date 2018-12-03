package entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "weaponsInGame", schema = "public", catalog = "postgres")
public class WeaponsInGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weaponInGameId")
    private Integer weaponInGameId;

    @ManyToOne
    @JoinColumn(name = "tributeId", referencedColumnName = "tributeId", nullable = false)
    private Tribute tribute;

    @ManyToOne
    @JoinColumn(name = "weaponId", referencedColumnName = "weaponId", nullable = false)
    private Weapon weapon;

    public WeaponsInGame(Tribute tribute, Weapon weapon) {
        setTribute(tribute);
        setWeapon(weapon);
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
