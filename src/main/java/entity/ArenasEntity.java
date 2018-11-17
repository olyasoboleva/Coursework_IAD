package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "arenas", schema = "public", catalog = "s243887")
public class ArenasEntity {
    private int arenaid;
    private short arenaLength;
    private short arenaWidth;
    private String typeoflocation;
    private Collection<GamesEntity> gamesByArenaid;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "arenaid")
    public int getArenaid() {
        return arenaid;
    }

    public void setArenaid(int arenaid) {
        this.arenaid = arenaid;
    }

    @Basic
    @Column(name = "arena_length")
    public short getArenaLength() {
        return arenaLength;
    }

    public void setArenaLength(short arenaLength) {
        this.arenaLength = arenaLength;
    }

    @Basic
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

    @Basic
    @Column(name = "typeoflocation")
    public String getTypeoflocation() {
        return typeoflocation;
    }

    public void setTypeoflocation(String typeoflocation) {
        this.typeoflocation = typeoflocation;
    }

    @OneToMany(mappedBy = "arenasByArena")
    public Collection<GamesEntity> getGamesByArenaid() {
        return gamesByArenaid;
    }

    public void setGamesByArenaid(Collection<GamesEntity> gamesByArenaid) {
        this.gamesByArenaid = gamesByArenaid;
    }
}
