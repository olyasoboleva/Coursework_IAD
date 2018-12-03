package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class UserSkillPK implements Serializable {
    @Getter
    @Setter
    @Column(name = "userId")
    @Id
    private int userId;

    @Getter
    @Setter
    @Column(name = "skillId")
    @Id
    private int skillId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSkillPK that = (UserSkillPK) o;

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
