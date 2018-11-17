package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "districts", schema = "public", catalog = "s243887")
public class DistrictsEntity {
    private short districtid;
    private String name;
    private String typeofactivity;
    private Integer skillid;
    private SkillsEntity skillsBySkillid;
    private Collection<UsersEntity> usersByDistrictid;

    @Id
    @Column(name = "districtid")
    public short getDistrictid() {
        return districtid;
    }

    public void setDistrictid(short districtid) {
        this.districtid = districtid;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "typeofactivity")
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

    @ManyToOne
    @JoinColumn(name = "skillid", referencedColumnName = "skillid")
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
