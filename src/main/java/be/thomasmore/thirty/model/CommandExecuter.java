package be.thomasmore.thirty.model;

import java.awt.*;
import java.lang.annotation.Target;
import java.util.*;

public class CommandExecuter {
    public void executeCommands(Collection<Command> commands, Board board){
        ArrayList<Command> commandsList = new ArrayList<>(commands);
        commandsList.sort(Comparator.comparing(Command::getShipInitiative));
        for(Command command : commandsList){
            Ship ship = command.getShip();
            //System.out.println(command.toString() + ship.toString());
            handleCommand(command, ship, board);
        }
    }

    private void handleCommand(Command command, Ship ship, Board board){
        ship.setOnPatrol(false);
        switch (command.getCommandType()){
            case Patrol:
                patrol(ship);
                break;
            case Fire:
                fire(ship, command.getTarget(), board);
                break;
            case TurnLeft:
                break;
            case TurnRight:
                break;
            case HalfThrottle:
                move(ship, true, board);
                break;
            case FullThrottle:
                move(ship, false, board);
                break;
            default: System.out.println(command.getCommandType() + " not found");
        }
    }

    private void patrol(Ship ship){
        ship.setOnPatrol(true);
    }

    private void fire(Ship ship, Tile target, Board board){

    }

    private void turn(Ship ship, boolean left, Board board){

    }

    private void move(Ship ship, boolean halfThrottle, Board board){
        int speed = ship.getSpeed();
        if(halfThrottle){
            speed = speed / 2;
        }
        for(int i = 0; i < speed; i++){
            if(tryMoveForward(ship, board) == false){
                return;
            }
        }
    }

    private boolean tryMoveForward(Ship ship, Board board){
        int numSegments = ship.getShipSize();
        Point[] oldSegmentLocations = ship.getSegmentLocations();
        Point[] newSegmentLocations = new Point[numSegments];
        Point direction = ship.getDirectionType().getVector();
        for(int i = 0; i < numSegments; i++){
            Point newLocation = (Point)oldSegmentLocations[i].clone();
            newLocation.translate(direction.x, direction.y);
            newSegmentLocations[i] = newLocation;
        }
        try{
            board.place(ship, newSegmentLocations);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}