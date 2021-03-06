package be.thomasmore.thirty.controllers;

import be.thomasmore.thirty.model.*;
import be.thomasmore.thirty.repositories.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
    private HashMap<Integer, Command> commands = new HashMap<>();
    private ArrayList<Ship> ships;
    private int turn = 1;
    private boolean gameStarted = false;

    @GetMapping(value="/handleCommand")
    public void handleCommand(@RequestParam("shipId") float shipId, @RequestParam("commandIndex") float commandIndex,
                              @RequestParam(value ="targetID", required = false) String targetID){
        int id = (int)shipId;
        Ship ship = null;
        try{
            ship = getById(id);
        } catch (Exception e){
            e.printStackTrace();
        }
        CommandType commandType = CommandType.values ()[(int)commandIndex];
        System.out.print("handle command:" + id + "_" + commandIndex);
        Command command = null;
        if(targetID == null){
            System.out.print("null");
            command = new Command(ship, commandType);
        } else {
            System.out.print(targetID);
            try {
                if(commandType != CommandType.Fire){
                    throw new Exception("There was a target specified for a non-fire command_" +
                            targetID + "_" + commandType);
                }
                String[] coors = targetID.split("_");
                int x = Integer.parseInt(coors[0]);
                int y = Integer.parseInt(coors[1]);
                Tile tile = board.getTileAt(x, y);
                command = new Command(ship, commandType, tile);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        commands.put(id, command);
    }

    @GetMapping("/game")
    public String nextTurn(@RequestParam(required = false) Integer width,
                           @RequestParam(required = false) Integer height,
                           Model model) {
        if((width != null)){
            startGame(width, height, model);
        } else {
            if(gameStarted == false){
                model.addAttribute("started", false);
            } else {
                turn = turn + 1;
                addDefaultCommands();
                commandExecuter.executeCommands(commands.values(), board);
                commands.clear();
                updateGameModel(model, board);
            }
        }
        return "game";
    }

    private void startGame(Integer width, Integer height, Model model) {
        commands = new HashMap<>();
        Iterable<ShipClass> shipClasses = shipRepository.findAll();
        board = new Board(width, height);
        ships = shipCreator.getShips(shipClasses, board);
        Ship[] shipsArray = ships.toArray(new Ship[ships.size()]);
        board.setShips(shipsArray);
        updateGameModel(model, board);
        gameStarted = true;
    }

    private void updateGameModel(Model model, Board board){
        model.addAttribute(board);
        model.addAttribute("ships", ships);
        model.addAttribute("commands", CommandType.values());
        model.addAttribute("turn", turn);
        model.addAttribute("started", true);
        model.addAttribute("tileSize", imgSize);
    }

    private Ship getById(int id) throws Exception {
        for(Ship ship : ships){
            if(ship.getId() == id){
                return ship;
            }
        }
        throw new Exception("Ship not found_" + id);
    }

    private void addDefaultCommands(){
        for(Ship ship : ships){
            int id = ship.getId();
            if(commands.containsKey(id) == false){
                commands.put(id, new Command(ship, CommandType.Patrol));
            }
        }
    }
}