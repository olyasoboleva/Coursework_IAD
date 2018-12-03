package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "hooks", schema = "public", catalog = "postgres")
public class Hook {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hookId")
    private int hookId;

    @Getter
    @Setter
    @Basic
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @Basic
    @Column(name = "damage")
    private int damage;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "locationId", referencedColumnName = "locationId", nullable = false)
    private Location location;

    public Hook(){ }

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

        if (hookId != that.hookId) return false;
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
