package weatherservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CacheManager cacheManager;

    @Cacheable("locations")
    public Location read(Long id){
        return locationRepository.getOne(id);
    }

    @Cacheable("locations")
    public List<Location> readLocations(){
        return locationRepository.findAll();
    }

    public Location saveLocation (Location location){
        return locationRepository.save(location);
    }

    public void tyhjaaValimuisti(){
        cacheManager.getCache("locations").clear();
    }

    public LocationRepository getLocationRepository() {
        return locationRepository;
    }
}
