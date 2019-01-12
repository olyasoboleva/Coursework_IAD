package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Integer locationId;

    @Column(name = "name")
    private String name;

    @Column(name = "picture")
    private byte[] picture;

    @JsonIgnore
    @ManyToMany(mappedBy = "locations", fetch = FetchType.LAZY)
    private Collection<Shop> products;

    @JsonIgnore
    @OneToMany(mappedBy = "mainLocation", fetch = FetchType.LAZY)
    private Collection<Arena> arenas;

    @JsonIgnore
    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    private Collection<Hook> hooks;

    @JsonIgnore
    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    private Collection<Map> gameMap;

    public Location(String name, byte[] picture){
        this.name = name;
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location that = (Location) o;
        return Objects.equals(locationId,that.locationId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(picture, that.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, name, picture);
    }

}
