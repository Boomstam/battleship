package be.thomasmore.thirty.controllers;

import be.thomasmore.thirty.model.Artist;
import be.thomasmore.thirty.model.Venue;
import be.thomasmore.thirty.repositories.ArtistRepository;
import be.thomasmore.thirty.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    private VenueRepository venueRepository;
    @Autowired
    private ArtistRepository artistRepository;
    private final Venue[] venues = {
            new Venue("De Club", "http://declubinfo"),
            new Venue("De Loods", "http://deloodsinfo"),
            new Venue("Zapoi", "http://zapoiinfo"),
            new Venue("Nekkerhal", "http://nekkerhalinfo")
    };

    @GetMapping({"/", "home"})
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model){
        return "about";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @GetMapping("/venuelist")
    public String venueList(Model model) {
        Iterable<Venue> venues = venueRepository.findAll();
        model.addAttribute("venues", venues);
        return "venuelist";
    }

    @GetMapping("/artistlist")
    public String artistList(Model model) {
        Iterable<Artist> artists = artistRepository.findAll();
        model.addAttribute("artists", artists);
        return "artistlist";
    }

    @GetMapping({"/venuedetails", "/venuedetails/{idString}"})
    public String venueDetails(Model model, @PathVariable(required = false) String idString) {
        int id = -1;
        try {
            id = Integer.parseInt(idString);
        }
        catch (NumberFormatException e)
        {
        }
        Optional<Venue> venue = venueRepository.findById(id);
        if(venue.isPresent()){
            model.addAttribute("venue", venue.get());
        } else {
            model.addAttribute("venue", null);
        }
        int numVenues = (int)venueRepository.count();
        model.addAttribute("previd", (id>=1 && id <= numVenues) ? (id>1 ? id-1 : numVenues) : null);
        model.addAttribute("nextid", (id>=1 && id <= numVenues) ? (id<numVenues ? id+1 : 1) : null);
        return "venuedetails";
    }

    @GetMapping({"/artistdetails", "/artistdetails/{idString}"})
    public String artistDetails(Model model, @PathVariable(required = false) String idString) {
        int id = -1;
        try {
            id = Integer.parseInt(idString);
        }
        catch (NumberFormatException e)
        {
        }
        Optional<Artist> artist = artistRepository.findById(id);
        if(artist.isPresent()){
            model.addAttribute("artist", artist.get());
        } else {
            model.addAttribute("artist", null);
        }
        int numArtists = (int)artistRepository.count();
        model.addAttribute("previd", (id>=1 && id <= numArtists) ? (id>1 ? id-1 : numArtists) : null);
        model.addAttribute("nextid", (id>=1 && id <= numArtists) ? (id<numArtists ? id+1 : 1) : null);
        return "artistdetails";
    }
}