package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "skills", schema = "public", catalog = "s243887")
public class SkillsEntity {
    private int skillid;
    private String name;
    private String description;
    private String typeofskill;
    private Integer weaponid;
    private Collection<DistrictsEntity> districtsBySkillid;
    private WeaponsEntity weaponsByWeaponid;
    private Collection<TrainingsEntity> trainingsBySkillid;
    private Collection<UserskillsEntity> userskillsBySkillid;

    @Id
    @Column(name = "skillid")
    public int getSkillid() {
        return skillid;
    }

    public void setSkillid(int skillid) {
        this.skillid = skillid;
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
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "typeofskill")
    public String getTypeofskill() {
        return typeofskill;
    }

    public void setTypeofskill(String typeofskill) {
        this.typeofskill = typeofskill;
    }

    @Basic
    @Column(name = "weaponid")
    public Integer getWeaponid() {
        return weaponid;
    }

    public void setWeaponid(Integer weaponid) {
        this.weaponid = weaponid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SkillsEntity that = (SkillsEntity) o;

        if (skillid != that.skillid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (typeofskill != null ? !typeofskill.equals(that.typeofskill) : that.typeofskill != null) return false;
        if (weaponid != null ? !weaponid.equals(that.weaponid) : that.weaponid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = skillid;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (typeofskill != null ? typeofskill.hashCode() : 0);
        result = 31 * result + (weaponid != null ? weaponid.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "skillsBySkillid")
    public Collection<DistrictsEntity> getDistrictsBySkillid() {
        return districtsBySkillid;
    }

    public void setDistrictsBySkillid(Collection<DistrictsEntity> districtsBySkillid) {
        this.districtsBySkillid = districtsBySkillid;
    }

    @OneToOne
    @JoinColumn(name = "weaponid", referencedColumnName = "weaponid")
    public WeaponsEntity getWeaponsByWeaponid() {
        return weaponsByWeaponid;
    }

    public void setWeaponsByWeaponid(WeaponsEntity weaponsByWeaponid) {
        this.weaponsByWeaponid = weaponsByWeaponid;
    }

    @OneToMany(mappedBy = "skillsBySkillid")
    public Collection<TrainingsEntity> getTrainingsBySkillid() {
        return trainingsBySkillid;
    }

    public void setTrainingsBySkillid(Collection<TrainingsEntity> trainingsBySkillid) {
        this.trainingsBySkillid = trainingsBySkillid;
    }

    @OneToMany(mappedBy = "skillsBySkillid")
    public Collection<UserskillsEntity> getUserskillsBySkillid() {
        return userskillsBySkillid;
    }

    public void setUserskillsBySkillid(Collection<UserskillsEntity> userskillsBySkillid) {
        this.userskillsBySkillid = userskillsBySkillid;
    }
}
