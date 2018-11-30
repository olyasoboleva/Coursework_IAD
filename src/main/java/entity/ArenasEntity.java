package entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "arenas", schema = "public", catalog = "postgres")
public class ArenasEntity {
    private int arenaid;
    private short arenaLength;
    private short arenaWidth;
    private LocationsEntity mainLocation;
    private GamesEntity gamesByArenaid;

    public ArenasEntity() {}

    public ArenasEntity(short arenaLength, short arenaWidth, LocationsEntity location) {
        this.arenaLength = arenaLength;
        this.arenaWidth = arenaWidth;
        this.mainLocation = location;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "arenaid")
    public int getArenaid() {
        return arenaid;
    }

    public void setArenaid(int arenaid) {
        this.arenaid = arenaid;
    }

    @Basic
    @Min(0)
    @NotNull
    @Column(name = "arena_length")
    public short getArenaLength() {
        return arenaLength;
    }

    public void setArenaLength(short arenaLength) {
        this.arenaLength = arenaLength;
    }

    @Basic
    @Min(0)
    @NotNull
    @Column(name = "arena_width")
    public short getArenaWidth() {
        return arenaWidth;
    }

    public void setArenaWidth(short arenaWidth) {
        this.arenaWidth = arenaWidth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArenasEntity that = (ArenasEntity) o;

        if (arenaid != that.arenaid) return false;
        if (arenaLength != that.arenaLength) return false;
        if (arenaWidth != that.arenaWidth) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = arenaid;
        result = 31 * result + (int) arenaLength;
        result = 31 * result + (int) arenaWidth;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "locationid", referencedColumnName = "locationid", nullable = false)
    public LocationsEntity getMainLocation() {
        return mainLocation;
    }

    public void setMainLocation(LocationsEntity mainLocation) {
        this.mainLocation = mainLocation;
    }

    @OneToOne(mappedBy = "arenasByArena")
    public GamesEntity getGamesByArenaid() {
        return gamesByArenaid;
    }

    public void setGamesByArenaid(GamesEntity gamesByArenaid) {
        this.gamesByArenaid = gamesByArenaid;
    }
}
