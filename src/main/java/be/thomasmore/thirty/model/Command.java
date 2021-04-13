package be.thomasmore.thirty.model;

public class Command {

    private Ship ship;
    private CommandType commandType;
    private Tile target;

    public Command(Ship ship, CommandType commandType) {
        this.ship = ship;
        this.commandType = commandType;
    }

    public Command(Ship ship, CommandType commandType, Tile target) {
        this.ship = ship;
        this.commandType = commandType;
        this.target = target;
    }

    /*public boolean hasTarget(){
        return target != null;
    }*/

    public CommandType getCommandType() {
        return commandType;
    }

    public Tile getTarget() {
        return target;
    }

    public Ship getShip() {
        return ship;
    }

    public int getShipInitiative(){
        return ship.getInitiative();
    }

    @Override
    public String toString() {
        return "Command{" +
                "commandType=" + commandType +
                ", target=" + target +
                '}';
    }
}