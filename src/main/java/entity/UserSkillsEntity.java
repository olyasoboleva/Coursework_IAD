package entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Objects;

@Entity
@Table(name = "userskills", schema = "public", catalog = "postgres")
@IdClass(UserSkillsEntityPK.class)
public class UserSkillsEntity {
    private int userId;
    private int skillId;
    private int levelOfSkill;
    private UsersEntity usersByUserid;
    private SkillsEntity skillsBySkillid;

    public UserSkillsEntity(){}

    public UserSkillsEntity(UsersEntity user, SkillsEntity skill, int levelOfSkill) {
        setUsersByUserid(user);
        setSkillsBySkillid(skill);
        this.levelOfSkill = levelOfSkill;
    }

    @Id
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "skillId")
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @Basic
    @Min(0)
    @Column(name = "levelOfSkill")
    public int getLevelOfSkill() {
        return levelOfSkill;
    }

    public void setLevelOfSkill(int levelOfSkill) {
        this.levelOfSkill = levelOfSkill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSkillsEntity that = (UserSkillsEntity) o;
        return userId == that.userId &&
                skillId == that.skillId &&
                levelOfSkill == that.levelOfSkill &&
                Objects.equals(usersByUserid, that.usersByUserid) &&
                Objects.equals(skillsBySkillid, that.skillsBySkillid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, skillId, levelOfSkill, usersByUserid, skillsBySkillid);
    }

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false, insertable = false, updatable = false)
    public UsersEntity getUsersByUserid() {
        return usersByUserid;
    }

    public void setUsersByUserid(UsersEntity usersByUserid) {
        this.usersByUserid = usersByUserid;
        this.userId = usersByUserid.getUserId();
    }

    @ManyToOne
    @JoinColumn(name = "skillId", referencedColumnName = "skillId", nullable = false, insertable = false, updatable = false)
    public SkillsEntity getSkillsBySkillid() {
        return skillsBySkillid;
    }

    public void setSkillsBySkillid(SkillsEntity skillsBySkillid) {
        this.skillsBySkillid = skillsBySkillid;
        this.skillId = skillsBySkillid.getSkillId();
    }
}
