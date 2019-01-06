package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "district", schema = "public", catalog = "postgres")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "district_id")
    private Integer districtId;

    @Column(name = "name", length = 20, unique = true)
    private String name;

    @NotNull
    @Column(name = "type_of_activity", length = 40)
    private String typeOfActivity;

    @OneToOne
    @JoinColumn(name = "skill_id", referencedColumnName = "skill_id")
    private Skill skill;

    @JsonIgnore
    @OneToMany(mappedBy = "district", fetch = FetchType.LAZY)
    private Collection<User> users;


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

        if (!(Objects.equals(districtId,that.districtId))) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (typeOfActivity != null ? !typeOfActivity.equals(that.typeOfActivity) : that.typeOfActivity != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = districtId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (typeOfActivity != null ? typeOfActivity.hashCode() : 0);
        return result;
    }

}
