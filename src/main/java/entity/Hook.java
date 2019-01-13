package entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "hook")
public class Hook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hook_id")
    private Integer hookId;

    @Column(name = "name")
    private String name;

    @Column(name = "damage")
    private int damage;

    @Column(name = "radius")
    private int radius;

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "location_id", nullable = false)
    private Location location;

    public Hook(String name, int damage, Location location){
        this.name = name;
        this.damage = damage;
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hook that = (Hook) o;

        if (!Objects.equals(hookId, that.hookId)) return false;
        if (damage != that.damage) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hookId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + damage;
        return result;
    }
}
