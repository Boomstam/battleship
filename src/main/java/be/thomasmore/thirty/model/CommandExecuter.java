package be.thomasmore.thirty.model;

import be.thomasmore.thirty.helpers.Chance;
import be.thomasmore.thirty.helpers.Direction;

import java.awt.*;
import java.util.*;

public class CommandExecuter {

    //create log


    private static final float submarineDodgeChance = 0.4f;

    public void executeCommands(Collection<Command> commands, Board board){
        ArrayList<Command> commandsList = new ArrayList<>(commands);
        commandsList.sort(Comparator.comparing(Command::getShipInitiative));
        for(Command command : commandsList){
            Ship ship = command.getShip();
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
                turn(ship, true, board);
                break;
            case TurnRight:
                turn(ship, false, board);
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
        WeaponType weapon = ship.getWeaponType();
        if(weapon == WeaponType.Mine){
            layMine(target);
            return;
        }
        int damage = weapon.getDamage();
        if(weapon == WeaponType.Depth){
            Point[] neighbors = Direction.getNeighborsIncludingDiagonal(target.getLocation());
            for(Point point : neighbors){
                try {
                    if(board.hasShipAt(point)){
                        Ship targetShip = board.getShipAt(point);
                        /*if(targetShip. true){

                        }*/
                    }
                } catch (Exception e){
                }
            }
        }
        if(weapon == WeaponType.Gun) {
            if(target.hasSegment()){
                Segment segment = target.getSegment();
                Ship targetShip = segment.getShip();
                if(targetShip.isSubmarine()){
                    Chance chance = new Chance(submarineDodgeChance);
                    if(chance.evaluate()){
                        if(segment.applyDamage(damage)){
                            handleShipDestruction(targetShip);
                        }
                    }
                } else {
                    if(segment.applyDamage(damage)){
                        handleShipDestruction(targetShip);
                    }
                }
            }
        }
    }

    private void layMine(Tile target){
        target.setHasMine(true);
    }

    private void turn(Ship ship, boolean left, Board board){
        Direction direction = ship.getDirectionType();
        Direction nextDirection;
        if(left){
            nextDirection = Direction.nextClockwise(direction);
        } else {
            nextDirection = Direction.nextCounterClockwise(direction);
        }
        tryTurn(ship, nextDirection, board);
    }

    private void tryTurn(Ship ship, Direction direction, Board board){
        int numSegments = ship.getShipSize();
        Point[] oldSegmentLocations = ship.getSegmentLocations();
        Point startSegmentLocation = oldSegmentLocations[0];
        Point[] newSegmentLocations = new Point[numSegments];
        newSegmentLocations[0] = startSegmentLocation;
        Point directionVector = direction.getVector();
        for(int i = 1; i < numSegments; i++){
            int prevIndex = i - 1;
            Point newLocation = (Point)newSegmentLocations[prevIndex].clone();
            newLocation.translate(directionVector.x, directionVector.y);
            newSegmentLocations[i] = newLocation;
        }
        try{
            board.place(ship, newSegmentLocations);
        } catch (Exception e){
        }
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

    private void handleShipDestruction(Ship ship){

    }
}