package be.thomasmore.thirty.model;

import java.awt.*;
import java.util.ArrayList;

public class ShipCreator {

    public ArrayList<Ship> getShips(Iterable<ShipClass> shipClasses, Board board) {
        ArrayList<Ship> ships = new ArrayList<>();
        for(ShipClass shipClass : shipClasses){
            for(int i = 0; i < shipClass.getAmount(); i++){
                Ship ship = new Ship(shipClass, i);
                ships.add(ship);
                try {
                    placeShip(ship, board);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return ships;
    }

    private void placeShip(Ship ship, Board board) throws Exception {
        Point[] segmentLocations = board.nextFreeShipSpace(ship.getShipSize());
        board.place(ship, segmentLocations);
    }
}