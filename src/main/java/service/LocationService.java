package service;

import entity.LocationsEntity;
import entity.ShopEntity;

import java.util.List;

public interface LocationService {

    /**
     * find location by id
     * @param locationId id
     * @return location
     */
    LocationsEntity findLocationById(int locationId);

    /**
     * find all locations where product can be applied
     * @param product product
     * @return list of locations
     */
    List<LocationsEntity> getLocationsByProduct(ShopEntity product);

    /**
     * It creates new Location
     * @param location location
     * @return new location
     */
    LocationsEntity createLocation(LocationsEntity location);

    /**
     * It updates the location
     * @param location location
     * @return location if it was updated correctly
     */
    LocationsEntity updateLocation(LocationsEntity location);

    /**
     * It deletes location
     * @param location location
     * @return true if location was successfully deleted
     */
    boolean deleteLocation(LocationsEntity location);
}
