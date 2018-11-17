package repository;

import entity.ArenasEntity;
import org.springframework.data.repository.CrudRepository;

public interface ArenasRepository extends CrudRepository<ArenasEntity, Integer> {
    ArenasEntity findArenasEntityByArenaWidthAndArenaLength(short width, short h);
}
