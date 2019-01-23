package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@Table(name = "map")
public class Map {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cell_id")
    private int cellId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "arena_id", referencedColumnName = "arena_id", nullable = false)
    private Arena arena;

    @Min(0)
    @Column(name = "x_coordinate")
    private Integer xCoordinate;

    @Min(0)
    @Column(name = "y_coordinate")
    private Integer yCoordinate;

    @Column(name = "location_id", insertable = false, updatable = false)
    private Integer locationId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "location_id", nullable = false)
    private Location location;

    public Map(Arena arena, Location location, int x, int y){
        this.arena = arena;
        this.location = location;
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Map map = (Map) o;
        return cellId == map.cellId &&
                Objects.equals(arena, map.arena) &&
                Objects.equals(xCoordinate, map.xCoordinate) &&
                Objects.equals(yCoordinate, map.yCoordinate) &&
                Objects.equals(location, map.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cellId, arena, xCoordinate, yCoordinate, location);
    }
}
