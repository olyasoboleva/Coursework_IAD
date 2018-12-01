package entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "weapons", schema = "public", catalog = "postgres")
public class WeaponsEntity {
    private int weaponId;
    private String name;
    private String typeOfWeapon;
    private int damage;
    private int radiusOfAction;
    private String picturePath;

    private SkillsEntity skill;
    private Collection<TributesEntity> owners;

    public WeaponsEntity(){ }

    public WeaponsEntity(String name, String typeOfWeapon, int damage, int radiusOfAction, String picturePath) {
        this.name = name;
        this.typeOfWeapon = typeOfWeapon;
        this.damage = damage;
        this.radiusOfAction = radiusOfAction;
        this.picturePath = picturePath;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weaponId")
    public int getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(int weaponId) {
        this.weaponId = weaponId;
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
    @Column(name = "typeOfWeapon", length = 40)
    public String getTypeOfWeapon() {
        return typeOfWeapon;
    }

    public void setTypeOfWeapon(String typeOfWeapon) {
        this.typeOfWeapon = typeOfWeapon;
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
    @Column(name = "radiusOfAction")
    public int getRadiusOfAction() {
        return radiusOfAction;
    }

    public void setRadiusOfAction(int radiusOfAction) {
        this.radiusOfAction = radiusOfAction;
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
        return weaponId == that.weaponId &&
                damage == that.damage &&
                radiusOfAction == that.radiusOfAction &&
                Objects.equals(name, that.name) &&
                Objects.equals(typeOfWeapon, that.typeOfWeapon) &&
                Objects.equals(picturePath, that.picturePath) &&
                Objects.equals(skill, that.skill) &&
                Objects.equals(owners, that.owners);
    }

    @Override
    public int hashCode() {

        return Objects.hash(weaponId, name, typeOfWeapon, damage, radiusOfAction, picturePath, skill, owners);
    }

    @OneToOne(mappedBy = "weapon")
    public SkillsEntity getSkill() {
        return skill;
    }
    public void setSkill(SkillsEntity skill) {
        this.skill = skill;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "weaponsInGame",
            joinColumns = {@JoinColumn(name = "weaponId")},
            inverseJoinColumns = {@JoinColumn(name = "tributeId")}
    )
    public Collection<TributesEntity> getOwners() {
        return owners;
    }

    public void setOwners(Collection<TributesEntity> owners) {
        this.owners = owners;
    }


}
