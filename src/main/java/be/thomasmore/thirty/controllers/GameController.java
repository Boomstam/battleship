package be.thomasmore.thirty.controllers;

import be.thomasmore.thirty.model.*;
import be.thomasmore.thirty.repositories.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;
import java.util.ArrayList;

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
        Iterable<ShipClass> shipClasses = shipRepository.findAll();
        ArrayList<Ship> ships = getShips(shipClasses);
        model.addAttribute(board);
        model.addAttribute("ships", ships);
        model.addAttribute("tileSize", imgSize);
        model.addAttribute("started", true);
        return "game";
    }

    private ArrayList<Ship> getShips(Iterable<ShipClass> shipClasses) {
        ArrayList<Ship> ships = new ArrayList<>();
        for(ShipClass shipClass : shipClasses){
            //System.out.print(shipClass.toString());
            for(int i = 0; i < shipClass.getAmount(); i++){
                Ship ship = new Ship(shipClass, i);
                ships.add(ship);
                try {
                    Point[] segmentLocations = board.nextFreeShipSpace(ship.getShipSize());
                    Segment[] segments = getSegmentsAt(segmentLocations, shipClass.getStartHealth());
                    ship.setSegments(segments);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return ships;
    }

    private Segment[] getSegmentsAt(Point[] points, int startHealth){
        Segment[] segments = new Segment[points.length];
        for(int i = 0; i < points.length; i++){
            Point point = points[i];
            try {
                Tile tile = board.getTileAt(point);
                segments[i] = new Segment(tile, startHealth);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return segments;
    }
}