package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "skill", schema = "public", catalog = "postgres")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private Integer skillId;

    @NotNull
    @Column(name = "name", length = 32)
    private String name;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "type_of_skill", length = 42)
    private String typeOfSkill;

    @JsonIgnore
    @OneToOne(mappedBy = "skill")
    private District district;

    @OneToOne
    @JoinColumn(name = "weapon_id", referencedColumnName = "weapon_id")
    private Weapon weapon;

    @JsonIgnore
    @OneToMany(mappedBy = "skill", fetch = FetchType.LAZY)
    private Collection<Training> trainings;

    @JsonIgnore
    @ManyToMany(mappedBy = "skills", fetch = FetchType.LAZY)
    private Collection<User> users;

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

        if (!Objects.equals(skillId, that.skillId)) return false;
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

}
