package be.thomasmore.thirty.model;

public class Command {

    private CommandType commandType;
    private Tile target;

    public Command(CommandType commandType) {
        this.commandType = commandType;
    }

    public Command(CommandType commandType, Tile target) {
        this.commandType = commandType;
        this.target = target;
    }

    public boolean hasTarget(){
        return target != null;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public Tile getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return "Command{" +
                "commandType=" + commandType +
                ", target=" + target +
                '}';
    }
}