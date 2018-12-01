package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class UserSkillsEntityPK implements Serializable {
    private int userId;
    private int skillId;

    @Column(name = "userId")
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "skillId")
    @Id
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSkillsEntityPK that = (UserSkillsEntityPK) o;

        if (userId != that.userId) return false;
        if (skillId != that.skillId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + skillId;
        return result;
    }
}
