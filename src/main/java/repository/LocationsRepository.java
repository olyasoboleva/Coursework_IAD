package repository;

import entity.LocationsEntity;
import entity.ShopEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LocationsRepository  extends CrudRepository<LocationsEntity, Integer> {
    LocationsEntity findLocationsEntityByLocationid(int id);
    List<LocationsEntity> getLocationsEntitiesByProducts(ShopEntity product);
}
