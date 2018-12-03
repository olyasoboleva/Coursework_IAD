package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class WeaponsInGamePK implements Serializable {
    @Getter
    @Setter
    @Column(name = "tributeId")
    @Id
    private int tributeId;

    @Getter
    @Setter
    @Column(name = "weaponId")
    @Id
    private int weaponId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeaponsInGamePK that = (WeaponsInGamePK) o;

        if (tributeId != that.tributeId) return false;
        if (weaponId != that.weaponId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tributeId;
        result = 31 * result + weaponId;
        return result;
    }
}
