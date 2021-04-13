package be.thomasmore.thirty.model;

import java.awt.*;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandExecuter {
    public void executeCommands(HashMap<Integer, Command> commands, Board board, ArrayList<Ship> ships){
        for(Map.Entry<Integer, Command> entry : commands.entrySet()){
            Integer id = entry.getKey();
            Command command = entry.getValue();
            Ship ship = ships.get(id);
            //System.out.println(command.toString() + ship.toString());
            handleCommand(command, ship, board);
        }
    }

    private void handleCommand(Command command, Ship ship, Board board){
        ship.setOnPatrol(false);
        switch (command.getCommandType()){
            case Patrol:
                break;
            case Fire:
                break;
            case TurnLeft:
                break;
            case TurnRight:
                break;
            case HalfThrottle:
                break;
            case FullThrottle:
                break;
            default: System.out.println(command.getCommandType() + " not found");
        }
    }

    private void patrol(Ship ship){
        ship.setOnPatrol(true);
    }

    private void fire(Ship ship, Point target){

    }

    private void turn(Ship ship, boolean left){

    }

    private void move(Ship ship, boolean halfThrottle){

    }
}