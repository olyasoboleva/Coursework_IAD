package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class UserskillsEntityPK implements Serializable {
    private int userid;
    private int skillid;

    @Column(name = "userid")
    @Id
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Column(name = "skillid")
    @Id
    public int getSkillid() {
        return skillid;
    }

    public void setSkillid(int skillid) {
        this.skillid = skillid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserskillsEntityPK that = (UserskillsEntityPK) o;

        if (userid != that.userid) return false;
        if (skillid != that.skillid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userid;
        result = 31 * result + skillid;
        return result;
    }
}
