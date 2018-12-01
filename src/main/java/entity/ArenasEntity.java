package entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "arenas", schema = "public", catalog = "postgres")
public class ArenasEntity {
    private int arenaId;
    private int arenaLength;
    private int arenaWidth;
    private LocationsEntity mainLocation;
    private GamesEntity game;

    public ArenasEntity() {}

    public ArenasEntity(int arenaLength, int arenaWidth, LocationsEntity location) {
        this.arenaLength = arenaLength;
        this.arenaWidth = arenaWidth;
        this.mainLocation = location;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "arenaId")
    public int getArenaId() {
        return arenaId;
    }

    public void setArenaId(int arenaId) {
        this.arenaId = arenaId;
    }

    @Basic
    @Min(0)
    @NotNull
    @Column(name = "arena_length")
    public int getArenaLength() {
        return arenaLength;
    }

    public void setArenaLength(int arenaLength) {
        this.arenaLength = arenaLength;
    }

    @Basic
    @Min(0)
    @NotNull
    @Column(name = "arena_width")
    public int getArenaWidth() {
        return arenaWidth;
    }

    public void setArenaWidth(int arenaWidth) {
        this.arenaWidth = arenaWidth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArenasEntity that = (ArenasEntity) o;

        if (arenaId != that.arenaId) return false;
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

    @ManyToOne
    @JoinColumn(name = "locationId", referencedColumnName = "locationId", nullable = false)
    public LocationsEntity getMainLocation() {
        return mainLocation;
    }
    public void setMainLocation(LocationsEntity mainLocation) {
        this.mainLocation = mainLocation;
    }

    @OneToOne(mappedBy = "arena")
    public GamesEntity getGame() {
        return game;
    }
    public void setGame(GamesEntity game) {
        this.game = game;
    }
}
