package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "weaponsInGame", schema = "public", catalog = "postgres")
//@IdClass(WeaponsInGamePK.class)
public class WeaponsInGame {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weaponInGameId")
    private int weaponInGameId;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "tributeId", referencedColumnName = "tributeId", nullable = false, updatable = false, insertable = false)
    private Tribute tribute;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "weaponId", referencedColumnName = "weaponId", nullable = false, updatable = false, insertable = false)
    private Weapon weapon;

    public WeaponsInGame(Tribute tribute, Weapon weapon) {
        setTribute(tribute);
        setWeapon(weapon);
    }

    public WeaponsInGame() {}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeaponsInGame that = (WeaponsInGame) o;

        if (weaponInGameId != that.weaponInGameId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = weaponInGameId;
        return result;
    }
}
