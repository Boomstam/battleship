package be.thomasmore.thirty.model;

import java.awt.*;
import java.util.ArrayList;

public class ShipCreator {

    public ArrayList<Ship> getShips(Iterable<ShipClass> shipClasses, Board board) {
        ArrayList<Ship> ships = new ArrayList<>();
        for(int playerIndex = 0; playerIndex < Player.values().length; playerIndex++) {
            Player player = Player.values()[playerIndex];
            for (ShipClass shipClass : shipClasses) {
                for (int i = 0; i < shipClass.getAmount(); i++) {
                    Ship ship = new Ship(shipClass, i, player);
                    ships.add(ship);
                    try {
                        placeShip(ship, board);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return ships;
    }

    private void placeShip(Ship ship, Board board) throws Exception {
        Player player = ship.getPlayer();
        Point[] segmentLocations = board.nextFreeShipSpace(
                ship.getShipSize(), player.getMinMapLimit(), player.getMaxMapLimit());
        board.place(ship, segmentLocations);
    }
}