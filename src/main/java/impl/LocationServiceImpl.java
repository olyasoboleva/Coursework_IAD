package impl;

import entity.LocationsEntity;
import entity.ShopEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.LocationsRepository;
import service.LocationService;

import java.util.List;

@Service("locationService")
public class LocationServiceImpl implements LocationService {

    private final LocationsRepository locationsRepository;

    @Autowired
    public LocationServiceImpl(LocationsRepository locationsRepository) {
        this.locationsRepository = locationsRepository;
    }

    @Override
    public LocationsEntity findLocationById(int locationId) {
        return null;
    }

    @Override
    public List<LocationsEntity> getLocationsByProduct(ShopEntity product) {
        return null;
    }

    @Transactional
    @Override
    public LocationsEntity createLocation(LocationsEntity location){
        locationsRepository.save(location);
        return location;
    }

    @Transactional
    @Override
    public LocationsEntity updateLocation(LocationsEntity location) {
        locationsRepository.save(location);
        return location;
    }

    @Transactional
    @Override
    public boolean deleteLocation(LocationsEntity location) {
        locationsRepository.delete(location);
        return true;
    }
}
