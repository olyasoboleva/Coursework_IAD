package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "skill")
@ToString(of = {"skillId", "name", "description", "typeOfSkill"})
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

}
