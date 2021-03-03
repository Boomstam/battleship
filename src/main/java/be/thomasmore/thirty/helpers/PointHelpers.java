package be.thomasmore.thirty.helpers;

import be.thomasmore.thirty.model.Tile;

import java.awt.*;

public class PointHelpers {
    private final static String separator = "_";

    public static Tile fromString(String tileString){
        String[] coors = tileString.split(separator);
        try {
            int x = Integer.parseInt(coors[0]);
            int y = Integer.parseInt(coors[1]);
            return new Tile(x, y);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String pointStrFormat(Point point){
        return point.x + separator + point.y;
    }
}
