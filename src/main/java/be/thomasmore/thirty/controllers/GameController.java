package be.thomasmore.thirty.controllers;

import be.thomasmore.thirty.model.Board;
import be.thomasmore.thirty.model.Ship;
import be.thomasmore.thirty.repositories.ShipRepository;
import be.thomasmore.thirty.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;

//https://stackoverflow.com/questions/34896877/call-controller-method-from-jsp-button-in-spring-mvc
@Controller
public class GameController {
    @Autowired
    private ShipRepository shipRepository;
    private Board board;
    private int imgSize = 20;
    private boolean isFirstRun = true;

    private Point currentLocation = new Point(0, 0);

    @GetMapping("game")
    public String game(Model model) {
        onFirstRun();
        model.addAttribute("started", false);
        model.addAttribute(board);
        model.addAttribute("tileSize", imgSize);
        return "game";
    }

    private void onFirstRun(){
        if(isFirstRun){
            //ImageWriter.writeImage(imgSize, imgSize);
            board = new Board();

            isFirstRun = false;
        }
    }

    @GetMapping(value="/tileClick")
    public void tileClick(@RequestParam("x") float rawX, @RequestParam("y") float rawY){
        int x = (int)rawX;
        int y = (int)rawY;
        System.out.print("clicked tile:" + x + "_" + y);
        currentLocation.setLocation(x, y);
    }

    @GetMapping("game/start")
    public String start(Model model) {
        onFirstRun();
        Iterable<Ship> ships = shipRepository.findAll();
        for(Ship ship : ships){
            System.out.print(ship.toString());
        }
        model.addAttribute(board);
        model.addAttribute("tileSize", imgSize);
        model.addAttribute("started", true);
        return "game";
    }
}