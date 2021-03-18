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
import java.util.*;

//https://stackoverflow.com/questions/34896877/call-controller-method-from-jsp-button-in-spring-mvc
@Controller
public class GameController {
    @Autowired
    private ShipRepository shipRepository;
    private Board board;
    private ShipCreator shipCreator = new ShipCreator();
    private CommandExecuter commandExecuter = new CommandExecuter();
    private int imgSize = 20;
    private Point currentLocation = new Point(0, 0);
    private HashMap<Integer, Command> commands = new HashMap<>();
    private ArrayList<Ship> ships;
    private int turn = 1;

    @GetMapping(value="/tileClick")
    public void tileClick(@RequestParam("x") float rawX, @RequestParam("y") float rawY){
        int x = (int)rawX;
        int y = (int)rawY;
        System.out.print("clicked tile:" + x + "_" + y);
        currentLocation.setLocation(x, y);
    }

    @GetMapping(value="/handleCommand")
    public void handleCommand(@RequestParam("shipId") float shipId, @RequestParam("commandIndex") float commandIndex){
        int id = (int)shipId;
        Command command = Command.values ()[(int)commandIndex];
        //System.out.print("handle command:" + idInt + "_" + commandIndexInt);
        commands.put(id, command);
    }

    @GetMapping("/game/{turn}")
    public String nextTurn(@PathVariable(required = false) String turn, Model model) {
        if(turn != null){
            try {
                this.turn = Integer.parseInt(turn);
            } catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
        commandExecuter.executeCommand(commands, board, ships);
        commands.clear();
        updateGameModel(model, board);
        return "game";
    }

    @GetMapping("/game")
    public String start(@RequestParam(required = false) Integer width,
                        @RequestParam(required = false) Integer height,
                        Model model) {
        commands = new HashMap<>();
        if(width == null || height == null){
            model.addAttribute("started", false);
            return "game";
        }
        board = new Board(width, height);
        Iterable<ShipClass> shipClasses = shipRepository.findAll();
        ships = shipCreator.getShips(shipClasses, board);
        updateGameModel(model, board);
        return "game";
    }

    private void updateGameModel(Model model, Board board){
        model.addAttribute(board);
        model.addAttribute("ships", ships);
        model.addAttribute("commands", Command.values());
        model.addAttribute("turn", turn);
        model.addAttribute("started", true);
        model.addAttribute("tileSize", imgSize);
    }
}