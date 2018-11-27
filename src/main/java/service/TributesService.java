package service;

import entity.ShopEntity;
import entity.TributesEntity;

import java.util.List;

public interface TributesService {
    TributesEntity createTribute(TributesEntity tribute);
    boolean deleteTribute(TributesEntity tribute);
    TributesEntity updateTribute(TributesEntity tribute);
}
