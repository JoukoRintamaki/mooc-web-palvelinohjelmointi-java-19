package weatherservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LocationController {

    //@Autowired
    //private LocationRepository locationRepository;

    @Autowired
    private LocationService locationService;

    @GetMapping("/locations")
    public String list(Model model) {
        //model.addAttribute("locations", locationRepository.findAll());
        model.addAttribute("locations", locationService.readLocations());
        return "locations";
    }

    @GetMapping("/locations/{id}")
    public String view(Model model, @PathVariable Long id) {
        //model.addAttribute("location", locationRepository.getOne(id));
        model.addAttribute("location", locationService.read(id));
        return "location";
    }

    @PostMapping("/locations")
    public String add(@ModelAttribute Location location) {
        //locationRepository.save(location);
        locationService.saveLocation(location);
        locationService.tyhjaaValimuisti();
        return "redirect:/locations";
    }

    @GetMapping("/flushcaches")
    public String tyhjaaCache(){
        locationService.tyhjaaValimuisti();
        return "locations";
    }
}
