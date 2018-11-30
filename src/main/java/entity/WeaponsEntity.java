package entity;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Table(name = "weapons", schema = "public", catalog = "postgres")
public class WeaponsEntity {
    private int weaponid;
    private String name;
    private String typeofweapon;
    private int damage;
    private int radiusofaction;
    private String picturePath;

    private SkillsEntity skillByWeaponid;
    private Collection<TributesEntity> owners;

    public WeaponsEntity(){ }

    public WeaponsEntity(String name, String typeofweapon, int damage, int radiusofaction, String picturePath) {
        this.name = name;
        this.typeofweapon = typeofweapon;
        this.damage = damage;
        this.radiusofaction = radiusofaction;
        this.picturePath = picturePath;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Basic
    @Min(0)
    @Column(name = "radiusofaction")
    public int getRadiusofaction() {
        return radiusofaction;
    }

    public void setRadiusofaction(int radiusofaction) {
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
        return weaponid == that.weaponid &&
                damage == that.damage &&
                radiusofaction == that.radiusofaction &&
                Objects.equals(name, that.name) &&
                Objects.equals(typeofweapon, that.typeofweapon) &&
                Objects.equals(picturePath, that.picturePath) &&
                Objects.equals(skillByWeaponid, that.skillByWeaponid) &&
                Objects.equals(owners, that.owners);
    }

    @Override
    public int hashCode() {

        return Objects.hash(weaponid, name, typeofweapon, damage, radiusofaction, picturePath, skillByWeaponid, owners);
    }

    @OneToOne(mappedBy = "weaponsByWeaponid")
    public SkillsEntity getSkillByWeaponid() {
        return skillByWeaponid;
    }

    public void setSkillByWeaponid(SkillsEntity skillByWeaponid) {
        this.skillByWeaponid = skillByWeaponid;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "weaponsingame",
            joinColumns = {@JoinColumn(name = "weaponid")},
            inverseJoinColumns = {@JoinColumn(name = "tributeid")}
    )
    public Collection<TributesEntity> getOwners() {
        return owners;
    }

    public void setOwners(Collection<TributesEntity> owners) {
        this.owners = owners;
    }
}
