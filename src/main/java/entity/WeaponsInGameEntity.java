package entity;

import javax.persistence.*;

@Entity
@Table(name = "weaponsInGame", schema = "public", catalog = "postgres")
@IdClass(WeaponsInGameEntityPK.class)
public class WeaponsInGameEntity {
    private int tributeId;
    private int weaponId;
    private TributesEntity tribute;
    private WeaponsEntity weapon;

    public WeaponsInGameEntity(TributesEntity tribute, WeaponsEntity weapon) {
        setTribute(tribute);
        setWeapon(weapon);
    }

    public WeaponsInGameEntity() {}

    @Id
    @Column(name = "tributeId")
    public int getTributeId() {
        return tributeId;
    }

    public void setTributeId(int tributeId) {
        this.tributeId = tributeId;
    }

    @Id
    @Column(name = "weaponId")
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

        WeaponsInGameEntity that = (WeaponsInGameEntity) o;

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

    @ManyToOne
    @JoinColumn(name = "tributeId", referencedColumnName = "tributeId", nullable = false, updatable = false, insertable = false)
    public TributesEntity getTribute() {
        return tribute;
    }

    public void setTribute(TributesEntity tributesByTributeid) {
        this.tribute = tributesByTributeid;
        tributeId = (int)tributesByTributeid.getTributeId();

    }

    @ManyToOne
    @JoinColumn(name = "weaponId", referencedColumnName = "weaponId", nullable = false, updatable = false, insertable = false)
    public WeaponsEntity getWeapon() {
        return weapon;
    }

    public void setWeapon(WeaponsEntity weaponsByWeaponid) {
        this.weapon = weaponsByWeaponid;
        weaponId = weaponsByWeaponid.getWeaponId();
    }
}
