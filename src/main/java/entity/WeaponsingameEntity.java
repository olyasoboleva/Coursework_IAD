package entity;

import javax.persistence.*;

@Entity
@Table(name = "weaponsingame", schema = "public", catalog = "s243887")
@IdClass(WeaponsingameEntityPK.class)
public class WeaponsingameEntity {
    private int tributeid;
    private int weaponid;
    private TributesEntity tributesByTributeid;
    private WeaponsEntity weaponsByWeaponid;

    public WeaponsingameEntity(int tributeid, int weaponid) {
        this.tributeid = tributeid;
        this.weaponid = weaponid;
    }

    public WeaponsingameEntity() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tributeid")
    public int getTributeid() {
        return tributeid;
    }

    public void setTributeid(int tributeid) {
        this.tributeid = tributeid;
    }

    @Id
    @Column(name = "weaponid")
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

        WeaponsingameEntity that = (WeaponsingameEntity) o;

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

    @ManyToOne
    @JoinColumn(name = "tributeid", referencedColumnName = "tributeid", nullable = false)
    public TributesEntity getTributesByTributeid() {
        return tributesByTributeid;
    }

    public void setTributesByTributeid(TributesEntity tributesByTributeid) {
        this.tributesByTributeid = tributesByTributeid;
    }

    @ManyToOne
    @JoinColumn(name = "weaponid", referencedColumnName = "weaponid", nullable = false)
    public WeaponsEntity getWeaponsByWeaponid() {
        return weaponsByWeaponid;
    }

    public void setWeaponsByWeaponid(WeaponsEntity weaponsByWeaponid) {
        this.weaponsByWeaponid = weaponsByWeaponid;
    }
}
