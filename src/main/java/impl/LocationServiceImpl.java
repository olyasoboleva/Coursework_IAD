package impl;

import entity.Location;
import entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.LocationRepository;
import service.LocationService;

import java.util.List;

@Service("locationService")
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Location findLocationById(int locationId) {
        return locationRepository.findLocationByLocationId(locationId);
    }

    @Override
    public Location findLocationByName(String name){
        return locationRepository.findLocationByName(name);
    }

    @Override
    public List<Location> getLocationsByProduct(Shop product) {
        return locationRepository.getLocationsByProducts(product);
    }

    @Transactional
    @Override
    public Location createLocation(Location location){
        locationRepository.save(location);
        return location;
    }

    @Transactional
    @Override
    public Location updateLocation(Location location) {
        return locationRepository.save(location);

    }

    @Transactional
    @Override
    public boolean deleteLocation(Location location) {
        locationRepository.delete(location);
        return true;
    }
}
