package entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "districts", schema = "public", catalog = "postgres")
public class DistrictsEntity {
    private int districtid;
    private String name;
    private String typeofactivity;
    private SkillsEntity skillsBySkillid;
    private Collection<UsersEntity> usersByDistrictid;

    public DistrictsEntity() { }

    public DistrictsEntity(String name, String typeofactivity, SkillsEntity skill) {
        this.name = name;
        this.typeofactivity = typeofactivity;
        this.skillsBySkillid = skill;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "districtid")
    public int getDistrictid() {
        return districtid;
    }

    public void setDistrictid(int districtid) {
        this.districtid = districtid;
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
    @Column(name = "typeofactivity", length = 40)
    public String getTypeofactivity() {
        return typeofactivity;
    }

    public void setTypeofactivity(String typeofactivity) {
        this.typeofactivity = typeofactivity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DistrictsEntity that = (DistrictsEntity) o;

        if (districtid != that.districtid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (typeofactivity != null ? !typeofactivity.equals(that.typeofactivity) : that.typeofactivity != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) districtid;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (typeofactivity != null ? typeofactivity.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "skillid", referencedColumnName = "skillid")
    @JsonBackReference(value="skillsDistricts")
    public SkillsEntity getSkillsBySkillid() {
        return skillsBySkillid;
    }

    public void setSkillsBySkillid(SkillsEntity skillsBySkillid) {
        this.skillsBySkillid = skillsBySkillid;
    }

    @OneToMany(mappedBy = "districtsByDistrict")
    public Collection<UsersEntity> getUsersByDistrictid() {
        return usersByDistrictid;
    }

    public void setUsersByDistrictid(Collection<UsersEntity> usersByDistrictid) {
        this.usersByDistrictid = usersByDistrictid;
    }
}
