package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class WeaponsInGameEntityPK implements Serializable {
    private int tributeId;
    private int weaponId;

    @Column(name = "tributeId")
    @Id
    public int getTributeId() {
        return tributeId;
    }

    public void setTributeId(int tributeId) {
        this.tributeId = tributeId;
    }

    @Column(name = "weaponId")
    @Id
    public int getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(int weaponId) {
        this.weaponId = weaponId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeaponsInGameEntityPK that = (WeaponsInGameEntityPK) o;

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
