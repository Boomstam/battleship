package be.thomasmore.thirty.model;

import java.awt.*;

public class Action {

    private Ship ship;
    private Point[] moveGoal;
    private Point target;
    private boolean isMove;
    private boolean isFire;
    private boolean isPatrol;

    public Action(Ship ship, Point[] moveGoal, CommandType commandType) {
        this(ship, moveGoal, null, commandType);
    }

    public Action(Ship ship, Point target) {
        this(ship, null, target, CommandType.Fire);
    }

    public Action(Ship ship, Point[] moveGoal, Point target, CommandType commandType) {
        if(commandType == CommandType.Fire){
            isFire = true;
        } else if(commandType == CommandType.Patrol){
            isPatrol = true;
        } else {
            isMove = true;
        }
        this.ship = ship;
        this.moveGoal = moveGoal;
        this.target = target;
    }

    public Ship getShip() {
        return ship;
    }

    public Point[] getMoveGoal() {
        return moveGoal;
    }

    public Point getTarget() {
        return target;
    }

    public boolean isMove() {
        return isMove;
    }

    public boolean isFire() {
        return isFire;
    }

    public boolean isPatrol() {
        return isPatrol;
    }
}