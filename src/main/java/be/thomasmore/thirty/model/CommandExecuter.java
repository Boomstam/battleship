package be.thomasmore.thirty.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandExecuter {
    public void executeCommand(HashMap<Integer, Command> commands, Board board, ArrayList<Ship> ships){

        for(Map.Entry<Integer, Command> entry : commands.entrySet()){
            Integer id = entry.getKey();
            Command command = entry.getValue();
            Ship ship = ships.get(id);
            System.out.println(command.toString() + ship.toString());
        }
    }
}