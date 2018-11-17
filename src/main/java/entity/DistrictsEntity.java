package entity;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "districts", schema = "public", catalog = "postgres")
public class DistrictsEntity {
    private short districtid;
    private String name;
    private String typeofactivity;
    private Integer skillid;
    private SkillsEntity skillsBySkillid;
    private Collection<UsersEntity> usersByDistrictid;

    public DistrictsEntity() {
        usersByDistrictid = new HashSet<UsersEntity>();
    }

    public DistrictsEntity(String name, String typeofactivity, Integer skillid) {
        this.name = name;
        this.typeofactivity = typeofactivity;
        this.skillid = skillid;
        usersByDistrictid = new HashSet<UsersEntity>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "districtid")
    public short getDistrictid() {
        return districtid;
    }

    public void setDistrictid(short districtid) {
        this.districtid = districtid;
    }

    @Basic
    @UniqueElements
    @Column(name = "name", length = 20)
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

    @Basic
    @Column(name = "skillid")
    public Integer getSkillid() {
        return skillid;
    }

    public void setSkillid(Integer skillid) {
        this.skillid = skillid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DistrictsEntity that = (DistrictsEntity) o;

        if (districtid != that.districtid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (typeofactivity != null ? !typeofactivity.equals(that.typeofactivity) : that.typeofactivity != null)
            return false;
        if (skillid != null ? !skillid.equals(that.skillid) : that.skillid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) districtid;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (typeofactivity != null ? typeofactivity.hashCode() : 0);
        result = 31 * result + (skillid != null ? skillid.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "skillid", referencedColumnName = "skillid", insertable = false, updatable = false)
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
