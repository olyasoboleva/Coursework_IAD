package service;

import entity.ArenasEntity;

public interface ArenasService {
    ArenasEntity createArena(ArenasEntity arena);
    boolean deleteArena(ArenasEntity arena);
    ArenasEntity updateArena(ArenasEntity arena);
}
