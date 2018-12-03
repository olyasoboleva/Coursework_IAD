package service;

import entity.Location;
import entity.Shop;

import java.util.List;

public interface LocationService {

    /**
     * find location by id
     * @param locationId id
     * @return location
     */
    Location findLocationById(int locationId);

    /**
     * find all locations where product can be applied
     * @param product product
     * @return list of locations
     */
    List<Location> getLocationsByProduct(Shop product);

    /**
     * It creates new Location
     * @param location location
     * @return new location
     */
    Location createLocation(Location location);

    /**
     * It updates the location
     * @param location location
     * @return location if it was updated correctly
     */
    Location updateLocation(Location location);

    /**
     * It deletes location
     * @param location location
     * @return true if location was successfully deleted
     */
    boolean deleteLocation(Location location);
}
