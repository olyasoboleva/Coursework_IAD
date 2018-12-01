package repository;

import entity.LocationsEntity;
import entity.ShopEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LocationsRepository  extends CrudRepository<LocationsEntity, Integer> {
    /**
     * find location by id
     * @param id id
     * @return location
     */
    LocationsEntity findLocationsEntityByLocationId(int id);

    /**
     * find all locations, where product can be applied
     * @param product product
     * @return list of locations
     */
    List<LocationsEntity> getLocationsEntitiesByProducts(ShopEntity product);
}
