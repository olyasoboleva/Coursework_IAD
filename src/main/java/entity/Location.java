package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "locations", schema = "public", catalog = "postgres")
public class Location {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "locationId")
    private int locationId;

    @Getter
    @Setter
    @Basic
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @Basic
    @Column(name = "picture")
    private byte[] picture;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "locations", fetch = FetchType.LAZY)
    private Collection<Shop> products;

    @Getter
    @Setter
    @OneToMany(mappedBy = "mainLocation", fetch = FetchType.LAZY)
    private Collection<Arena> arenas;

    @Getter
    @Setter
    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    private Collection<Hook> hooks;

    public Location(){ }

    public Location(String name, byte[] picture){
        this.name = name;
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location that = (Location) o;
        return locationId == that.locationId &&
                Objects.equals(name, that.name) &&
                Objects.equals(picture, that.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, name, picture);
    }

}
