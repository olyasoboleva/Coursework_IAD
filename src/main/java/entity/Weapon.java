package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "weapons", schema = "public", catalog = "postgres")
public class Weapon {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weaponId")
    private int weaponId;

    @Getter
    @Setter
    @Basic
    @Column(name = "name", length = 64, unique = true)
    private String name;

    @Getter
    @Setter
    @Basic
    @NotNull
    @Column(name = "typeOfWeapon", length = 40)
    private String typeOfWeapon;

    @Getter
    @Setter
    @Basic
    @Min(0)
    @Max(100)
    @Column(name = "damage")
    private int damage;

    @Getter
    @Setter
    @Basic
    @Min(0)
    @Column(name = "radiusOfAction")
    private int radiusOfAction;

    @Getter
    @Setter
    @Basic
    @Column(name = "picture")
    private byte[] picture;

    @Getter
    @Setter
    @OneToOne(mappedBy = "weapon")
    private Skill skill;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "weaponsInGame",
            joinColumns = {@JoinColumn(name = "weaponId")},
            inverseJoinColumns = {@JoinColumn(name = "tributeId")}
    )
    private Collection<Tribute> owners;

    public Weapon(){ }

    public Weapon(String name, String typeOfWeapon, int damage, int radiusOfAction, byte[] picture) {
        this.name = name;
        this.typeOfWeapon = typeOfWeapon;
        this.damage = damage;
        this.radiusOfAction = radiusOfAction;
        this.picture = picture;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weapon that = (Weapon) o;
        return weaponId == that.weaponId &&
                damage == that.damage &&
                radiusOfAction == that.radiusOfAction &&
                Objects.equals(name, that.name) &&
                Objects.equals(typeOfWeapon, that.typeOfWeapon) &&
                Objects.equals(skill, that.skill) &&
                Objects.equals(owners, that.owners);
    }

    @Override
    public int hashCode() {

        return Objects.hash(weaponId, name, typeOfWeapon, damage, radiusOfAction, picture, skill, owners);
    }


}
