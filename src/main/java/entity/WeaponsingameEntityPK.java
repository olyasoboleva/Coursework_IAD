package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class WeaponsingameEntityPK implements Serializable {
    private int tributeid;
    private int weaponid;

    @Column(name = "tributeid")
    @Id
    public int getTributeid() {
        return tributeid;
    }

    public void setTributeid(int tributeid) {
        this.tributeid = tributeid;
    }

    @Column(name = "weaponid")
    @Id
    public int getWeaponid() {
        return weaponid;
    }

    public void setWeaponid(int weaponid) {
        this.weaponid = weaponid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeaponsingameEntityPK that = (WeaponsingameEntityPK) o;

        if (tributeid != that.tributeid) return false;
        if (weaponid != that.weaponid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tributeid;
        result = 31 * result + weaponid;
        return result;
    }
}
