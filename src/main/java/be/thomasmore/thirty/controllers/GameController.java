package be.thomasmore.thirty.controllers;

import be.thomasmore.thirty.model.*;
import be.thomasmore.thirty.repositories.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

//https://stackoverflow.com/questions/34896877/call-controller-method-from-jsp-button-in-spring-mvc
@Controller
public class GameController {
    @Autowired
    private ShipRepository shipRepository;
    private Board board;
    private ShipCreator shipCreator = new ShipCreator();
    private int imgSize = 20;
    private Point currentLocation = new Point(0, 0);

    @GetMapping(value="/tileClick")
    public void tileClick(@RequestParam("x") float rawX, @RequestParam("y") float rawY){
        int x = (int)rawX;
        int y = (int)rawY;
        System.out.print("clicked tile:" + x + "_" + y);
        currentLocation.setLocation(x, y);
    }

    @GetMapping(value="/handleCommand")
    public void handleCommand(@RequestParam("shipId") float shipId, @RequestParam("commandIndex") float commandIndex){
        int x = (int)shipId;
        int y = (int)commandIndex;
        System.out.print("handle command:" + x + "_" + y);
    }

    @GetMapping("/game")
    public String start(@RequestParam(required = false) Integer width,
                        @RequestParam(required = false) Integer height,
                        Model model) {
        if(width == null || height == null){
            model.addAttribute("started", false);
            return "game";
        }
        board = new Board(width, height);
        Iterable<ShipClass> shipClasses = shipRepository.findAll();
        ArrayList<Ship> ships = shipCreator.getShips(shipClasses, board);
        model.addAttribute(board);
        model.addAttribute("ships", ships);
        model.addAttribute("tileSize", imgSize);
        model.addAttribute("commands", Command.values());
        model.addAttribute("started", true);
        return "game";
    }


}