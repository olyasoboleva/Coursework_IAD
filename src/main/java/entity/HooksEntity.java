package entity;

import javax.persistence.*;

@Entity
@Table(name = "hooks", schema = "public", catalog = "postgres")
public class HooksEntity {
    private int hookId;
    private String name;
    private int damage;
    private LocationsEntity location;

    public HooksEntity(){ }

    public HooksEntity(String name, int damage, LocationsEntity location){
        this.name = name;
        this.damage = damage;
        this.location = location;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hookId")
    public int getHookId() {
        return hookId;
    }

    public void setHookId(int hookId) {
        this.hookId = hookId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "damage")
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HooksEntity that = (HooksEntity) o;

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

    @ManyToOne
    @JoinColumn(name = "locationId", referencedColumnName = "locationId", nullable = false)
    public LocationsEntity getLocation() {
        return location;
    }

    public void setLocation(LocationsEntity location) {
        this.location = location;
    }
}
