package repository;

import entity.ArenasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArenasRepository extends JpaRepository<ArenasEntity, Integer> {
    ArenasEntity findArenasEntityByArenaWidthAndArenaLength(short width, short h);
}
