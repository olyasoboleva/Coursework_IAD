package entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name = "skills", schema = "public", catalog = "postgres")
public class SkillsEntity {
    private int skillId;
    private String name;
    private String description;
    private String typeOfSkill;
    private DistrictsEntity district;
    private WeaponsEntity weapon;
    private Collection<TrainingsEntity> trainings;
    private Collection<UsersEntity> users;

    public SkillsEntity() { }

    public SkillsEntity(String name, String description, String typeOfSkill, WeaponsEntity weapon) {
        this.name = name;
        this.description = description;
        this.typeOfSkill = typeOfSkill;
        this.weapon = weapon;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skillId")
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @Basic
    @NotNull
    @Column(name = "name", length = 32)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @NotNull
    @Column(name = "typeOfSkill", length = 42)
    public String getTypeOfSkill() {
        return typeOfSkill;
    }

    public void setTypeOfSkill(String typeOfSkill) {
        this.typeOfSkill = typeOfSkill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SkillsEntity that = (SkillsEntity) o;

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

    @OneToOne(mappedBy = "skill")
    public DistrictsEntity getDistrict() {
        return district;
    }
    public void setDistrict(DistrictsEntity district) {
        this.district = district;
    }

    @OneToOne
    @JoinColumn(name = "weaponId", referencedColumnName = "weaponId")
    public WeaponsEntity getWeapon() {
        return weapon;
    }
    public void setWeapon(WeaponsEntity weapon) {
        this.weapon = weapon;
    }

    @OneToMany(mappedBy = "skill", fetch = FetchType.LAZY)
    public Collection<TrainingsEntity> getTrainings() {
        return trainings;
    }
    public void setTrainings(Collection<TrainingsEntity> trainings) {
        this.trainings = trainings;
    }

    @ManyToMany(mappedBy = "skills", fetch = FetchType.LAZY)
    public Collection<UsersEntity> getUsers() {
        return users;
    }
    public void setUsers(Collection<UsersEntity> users) {
        this.users = users;
    }
}
