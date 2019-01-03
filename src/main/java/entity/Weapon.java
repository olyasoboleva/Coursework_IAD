package entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "weapon", schema = "public", catalog = "postgres")
public class Weapon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weapon_id")
    private Integer weaponId;

    @Column(name = "name", length = 64, unique = true)
    private String name;

    @NotNull
    @Column(name = "type_of_weapon", length = 40)
    private String typeOfWeapon;

    @Min(0)
    @Max(100)
    @Column(name = "damage")
    private int damage;

    @Min(0)
    @Column(name = "radius_of_action")
    private int radiusOfAction;

    @Column(name = "picture")
    private byte[] picture;

    @OneToOne(mappedBy = "weapon")
    private Skill skill;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "weapons_in_game",
            joinColumns = {@JoinColumn(name = "weapon_id")},
            inverseJoinColumns = {@JoinColumn(name = "tribute_id")}
    )
    private Collection<Tribute> owners;


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
        return Objects.equals(weaponId, that.weaponId) &&
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
