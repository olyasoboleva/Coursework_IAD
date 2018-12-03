package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name = "skills", schema = "public", catalog = "postgres")
public class Skill {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skillId")
    private int skillId;

    @Getter
    @Setter
    @Basic
    @NotNull
    @Column(name = "name", length = 32)
    private String name;

    @Getter
    @Setter
    @Basic
    @Column(name = "description")
    private String description;

    @Getter
    @Setter
    @Basic
    @NotNull
    @Column(name = "typeOfSkill", length = 42)
    private String typeOfSkill;

    @Getter
    @Setter
    @OneToOne(mappedBy = "skill")
    private District district;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "weaponId", referencedColumnName = "weaponId")
    private Weapon weapon;

    @Getter
    @Setter
    @OneToMany(mappedBy = "skill", fetch = FetchType.LAZY)
    private Collection<Training> trainings;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "skills", fetch = FetchType.LAZY)
    private Collection<User> users;

    public Skill() { }

    public Skill(String name, String description, String typeOfSkill, Weapon weapon) {
        this.name = name;
        this.description = description;
        this.typeOfSkill = typeOfSkill;
        this.weapon = weapon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Skill that = (Skill) o;

        if (skillId != that.skillId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (typeOfSkill != null ? !typeOfSkill.equals(that.typeOfSkill) : that.typeOfSkill != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = skillId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (typeOfSkill != null ? typeOfSkill.hashCode() : 0);
        return result;
    }


    /*@ManyToMany(mappedBy = "skills", fetch = FetchType.LAZY)
    public Collection<User> getUsers() {
        return users;
    }
    public void setUsers(Collection<User> users) {
        this.users = users;
    }*/
}
