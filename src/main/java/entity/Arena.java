package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "arena", schema = "public", catalog = "postgres")
public class Arena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "arena_id")
    private Integer arenaId;

    @Min(0)
    @NotNull
    @Column(name = "arena_length")
    private int arenaLength;

    @Min(0)
    @NotNull
    @Column(name = "arena_width")
    private int arenaWidth;

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "location_id", nullable = false)
    private Location mainLocation;

    @JsonIgnore
    @OneToOne(mappedBy = "arena")
    private Game game;

    public Arena(int arenaLength, int arenaWidth, Location location) {
        this.arenaLength = arenaLength;
        this.arenaWidth = arenaWidth;
        this.mainLocation = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Arena that = (Arena) o;

        if (!Objects.equals(arenaId, that.arenaId)) return false;
        if (arenaLength != that.arenaLength) return false;
        if (arenaWidth != that.arenaWidth) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = arenaId;
        result = 31 * result +  arenaLength;
        result = 31 * result + arenaWidth;
        return result;
    }

}
