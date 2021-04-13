package be.thomasmore.thirty.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandExecuter {
    public void executeCommand(HashMap<Integer, CommandType> commands, Board board, ArrayList<Ship> ships){

        for(Map.Entry<Integer, CommandType> entry : commands.entrySet()){
            Integer id = entry.getKey();
            CommandType commandType = entry.getValue();
            Ship ship = ships.get(id);
            System.out.println(commandType.toString() + ship.toString());
        }
    }
}