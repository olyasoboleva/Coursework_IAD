package entity;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "weapons", schema = "public", catalog = "postgres")
public class WeaponsEntity {
    private int weaponid;
    private String name;
    private String typeofweapon;
    private Short damage;
    private Short radiusofaction;
    private String picturePath;

    private SkillsEntity skillByWeaponid;
    private Collection<WeaponsingameEntity> weaponsingamesByWeaponid;

    public WeaponsEntity(){
        weaponsingamesByWeaponid  = new HashSet<WeaponsingameEntity>();
    }

    public WeaponsEntity(String name, String typeofweapon, Short damage, Short radiusofaction, String picturePath) {
        this.name = name;
        this.typeofweapon = typeofweapon;
        this.damage = damage;
        this.radiusofaction = radiusofaction;
        this.weaponsingamesByWeaponid  =new HashSet<WeaponsingameEntity>();
        this.picturePath = picturePath;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "weaponid")
    public int getWeaponid() {
        return weaponid;
    }

    public void setWeaponid(int weaponid) {
        this.weaponid = weaponid;
    }

    @Basic
    @Column(name = "name", length = 64, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @NotNull
    @Column(name = "typeofweapon", length = 40)
    public String getTypeofweapon() {
        return typeofweapon;
    }

    public void setTypeofweapon(String typeofweapon) {
        this.typeofweapon = typeofweapon;
    }

    @Basic
    @Min(0)
    @Max(100)
    @Column(name = "damage")
    public Short getDamage() {
        return damage;
    }

    public void setDamage(Short damage) {
        this.damage = damage;
    }

    @Basic
    @Min(0)
    @Column(name = "radiusofaction")
    public Short getRadiusofaction() {
        return radiusofaction;
    }

    public void setRadiusofaction(Short radiusofaction) {
        this.radiusofaction = radiusofaction;
    }

    @Basic
    @Column(name = "picturePath", nullable = false)
    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeaponsEntity that = (WeaponsEntity) o;

        if (weaponid != that.weaponid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (typeofweapon != null ? !typeofweapon.equals(that.typeofweapon) : that.typeofweapon != null) return false;
        if (damage != null ? !damage.equals(that.damage) : that.damage != null) return false;
        if (radiusofaction != null ? !radiusofaction.equals(that.radiusofaction) : that.radiusofaction != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = weaponid;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (typeofweapon != null ? typeofweapon.hashCode() : 0);
        result = 31 * result + (damage != null ? damage.hashCode() : 0);
        result = 31 * result + (radiusofaction != null ? radiusofaction.hashCode() : 0);
        return result;
    }

    @OneToOne(mappedBy = "weaponsByWeaponid")
    public SkillsEntity getSkillByWeaponid() {
        return skillByWeaponid;
    }

    public void setSkillByWeaponid(SkillsEntity skillByWeaponid) {
        this.skillByWeaponid = skillByWeaponid;
    }

    @OneToMany(mappedBy = "weaponsByWeaponid")
    public Collection<WeaponsingameEntity> getWeaponsingamesByWeaponid() {
        return weaponsingamesByWeaponid;
    }

    public void setWeaponsingamesByWeaponid(Collection<WeaponsingameEntity> weaponsingamesByWeaponid) {
        this.weaponsingamesByWeaponid = weaponsingamesByWeaponid;
    }
}
