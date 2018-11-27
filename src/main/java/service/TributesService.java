package service;

import entity.TributesEntity;

public interface TributesService {
    TributesEntity createTribute(TributesEntity tribute);
    boolean deleteTribute(TributesEntity tribute);
    TributesEntity updateTribute(TributesEntity tribute);
}
