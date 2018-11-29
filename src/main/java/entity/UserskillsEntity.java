package entity;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "userskills", schema = "public", catalog = "postgres")
@IdClass(UserskillsEntityPK.class)
public class UserskillsEntity {
    private int userid;
    private int skillid;
    private Short levelofskill;
    private UsersEntity usersByUserid;
    private SkillsEntity skillsBySkillid;

    public UserskillsEntity(){}

    public UserskillsEntity(UsersEntity user, SkillsEntity skill, Short levelofskill) {
        setUsersByUserid(user);
        setSkillsBySkillid(skill);
        this.levelofskill = levelofskill;
    }

    @Id
    @Column(name = "userid")
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Id
    @Column(name = "skillid")
    public int getSkillid() {
        return skillid;
    }

    public void setSkillid(int skillid) {
        this.skillid = skillid;
    }

    @Basic
    @Min(0)
    @Column(name = "levelofskill")
    public Short getLevelofskill() {
        return levelofskill;
    }

    public void setLevelofskill(Short levelofskill) {
        this.levelofskill = levelofskill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserskillsEntity that = (UserskillsEntity) o;

        if (userid != that.userid) return false;
        if (skillid != that.skillid) return false;
        if (levelofskill != null ? !levelofskill.equals(that.levelofskill) : that.levelofskill != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userid;
        result = 31 * result + skillid;
        result = 31 * result + (levelofskill != null ? levelofskill.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false, insertable = false, updatable = false)
    public UsersEntity getUsersByUserid() {
        return usersByUserid;
    }

    public void setUsersByUserid(UsersEntity usersByUserid) {
        this.usersByUserid = usersByUserid;
        this.userid = usersByUserid.getUserid();
    }

    @ManyToOne
    @JoinColumn(name = "skillid", referencedColumnName = "skillid", nullable = false, insertable = false, updatable = false)
    public SkillsEntity getSkillsBySkillid() {
        return skillsBySkillid;
    }

    public void setSkillsBySkillid(SkillsEntity skillsBySkillid) {
        this.skillsBySkillid = skillsBySkillid;
        this.skillid = skillsBySkillid.getSkillid();
    }
}
