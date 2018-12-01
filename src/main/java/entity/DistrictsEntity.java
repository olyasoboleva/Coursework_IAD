package entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name = "districts", schema = "public", catalog = "postgres")
public class DistrictsEntity {
    private int districtId;
    private String name;
    private String typeOfActivity;
    private SkillsEntity skill;
    private Collection<UsersEntity> users;

    public DistrictsEntity() { }

    public DistrictsEntity(String name, String typeOfActivity, SkillsEntity skill) {
        this.name = name;
        this.typeOfActivity = typeOfActivity;
        this.skill = skill;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "districtId")
    public int getDistrictId() {
        return districtId;
    }
    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    @Basic
    @Column(name = "name", length = 20, unique = true)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @NotNull
    @Column(name = "typeOfActivity", length = 40)
    public String getTypeOfActivity() {
        return typeOfActivity;
    }
    public void setTypeOfActivity(String typeOfActivity) {
        this.typeOfActivity = typeOfActivity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DistrictsEntity that = (DistrictsEntity) o;

        if (districtId != that.districtId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (typeOfActivity != null ? !typeOfActivity.equals(that.typeOfActivity) : that.typeOfActivity != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) districtId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (typeOfActivity != null ? typeOfActivity.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "skillId", referencedColumnName = "skillId")
    public SkillsEntity getSkill() {
        return skill;
    }
    public void setSkill(SkillsEntity skill) {
        this.skill = skill;
    }

    @OneToMany(mappedBy = "district", fetch = FetchType.LAZY)
    public Collection<UsersEntity> getUsers() {
        return users;
    }
    public void setUsers(Collection<UsersEntity> users) {
        this.users = users;
    }
}
