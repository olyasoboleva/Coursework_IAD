package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name = "districts", schema = "public", catalog = "postgres")
public class District {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "districtId")
    private int districtId;

    @Getter
    @Setter
    @Basic
    @Column(name = "name", length = 20, unique = true)
    private String name;

    @Getter
    @Setter
    @Basic
    @NotNull
    @Column(name = "typeOfActivity", length = 40)
    private String typeOfActivity;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "skillId", referencedColumnName = "skillId")
    private Skill skill;

    @Getter
    @Setter
    @OneToMany(mappedBy = "district", fetch = FetchType.LAZY)
    private Collection<User> users;

    public District() { }

    public District(String name, String typeOfActivity, Skill skill) {
        this.name = name;
        this.typeOfActivity = typeOfActivity;
        this.skill = skill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        District that = (District) o;

        if (districtId != that.districtId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (typeOfActivity != null ? !typeOfActivity.equals(that.typeOfActivity) : that.typeOfActivity != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) districtId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (typeOfActivity != null ? typeOfActivity.hashCode() : 0);
        return result;
    }

}
