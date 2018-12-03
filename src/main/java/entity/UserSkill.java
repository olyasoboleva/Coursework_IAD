package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Objects;

@Entity
@Table(name = "userskills", schema = "public", catalog = "postgres")
//@IdClass(UserSkillPK.class)
public class UserSkill {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userSkillId")
    private int userSkillId;

    @Getter
    @Setter
    @Basic
    @Min(0)
    @Column(name = "levelOfSkill")
    private int levelOfSkill;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false, insertable = false, updatable = false)
    private User user;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "skillId", referencedColumnName = "skillId", nullable = false, insertable = false, updatable = false)
    private Skill skill;

    public UserSkill(){}

    public UserSkill(User user, Skill skill, int levelOfSkill) {
        setUser(user);
        setSkill(skill);
        this.levelOfSkill = levelOfSkill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSkill that = (UserSkill) o;
        return userSkillId == that.userSkillId &&
                levelOfSkill == that.levelOfSkill &&
                Objects.equals(user, that.user) &&
                Objects.equals(skill, that.skill);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userSkillId, levelOfSkill, user, skill);
    }
}
