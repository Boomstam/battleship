package be.thomasmore.thirty.controllers;

import be.thomasmore.thirty.model.ShipClass;
import be.thomasmore.thirty.repositories.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class ShipopediaController {
    @Autowired
    private ShipRepository shipRepository;

    @GetMapping("/shipopedia")
    public String shipopedia(@RequestParam(required = false) Integer type,
                             @RequestParam(required = false) Integer size,
                             Model model) {
        Iterable<ShipClass> ships = shipRepository.findAll();
        Collection shipColl = (Collection)ships;
        model.addAttribute("numShips", shipColl.size());
        model.addAttribute("ships", ships);
        model.addAttribute("allShips", ships);
        model.addAttribute("filter", false);
        return "shipopedia";
    }

    @GetMapping({"/shipopedia/filter", "shipopedia/filter/{keyword}"})
    public String shipopediaKeyword(Model model,
                                    @RequestParam(required = false) String keyword,
                                    @RequestParam(required = false) String shipTypeSelect,
                                    @RequestParam(required = false) String sortDirection,
                                    @RequestParam(required = false) String propertySelect) {
        Iterable<ShipClass> allShips = shipRepository.findAll();
        model.addAttribute("allShips", allShips);
        Collection shipColl = (Collection)allShips;
        if(shipTypeSelect != null && shipTypeSelect.equals("All") == false){
            keyword = shipTypeSelect;
        }
        if(keyword != null){
            Iterable<ShipClass> ships = shipRepository.findByKeyword(keyword);
            shipColl = (Collection)ships;
        }
        /*List shipList = new ArrayList(shipColl);
        if (propertySelect != null) {
            switch (propertySelect){
                case "Initiative":
                    shipList.sort(Comparator.comparing(ShipClass::getInitiative));
                case "Amount":
                    shipList.sort(Comparator.comparing(ShipClass::getAmount));
            }
        }
        model.addAttribute("shipProperties", ShipClass.sortableProperties);*/
        model.addAttribute("numShips", shipColl.size());
        model.addAttribute("keyword", keyword);
        model.addAttribute("ships", shipColl);
        model.addAttribute("filter", true);
        return "shipopedia";
    }

    @GetMapping({"/shipDetails", "/shipDetails/{idString}"})
    public String shipDetails(Model model, @PathVariable(required = false) String idString) {
        int id = -1;
        try {
            id = Integer.parseInt(idString);
        }
        catch (NumberFormatException e)
        {
        }
        Optional<ShipClass> ship = shipRepository.findById(id);
        if(ship.isPresent()){
            model.addAttribute("ship", ship.get());
        } else {
            model.addAttribute("ship", null);
        }
        int numShips = (int)shipRepository.count();
        model.addAttribute("previd", (id>=1 && id <= numShips) ? (id>1 ? id-1 : numShips) : null);
        model.addAttribute("nextid", (id>=1 && id <= numShips) ? (id<numShips ? id+1 : 1) : null);
        return "shipDetails";
    }
}
