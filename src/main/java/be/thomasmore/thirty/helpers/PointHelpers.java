package be.thomasmore.thirty.helpers;

import be.thomasmore.thirty.model.Tile;

import java.awt.*;

public class PointHelpers {
    private final static String separator = "_";
    private final static String arraySeparator = "-";

    public static Point pointFromString(String pointString) throws Exception{
        String[] coors = pointString.split(separator);
        int x = Integer.parseInt(coors[0]);
        int y = Integer.parseInt(coors[1]);
        return new Point(x, y);
    }

    public static String pointStrFormat(Point point){
        return point.x + separator + point.y;
    }

    public static String pointArrayStrFormat(Point[] points){
        String str = "";
        for(int i = 0; i < points.length; i++){
            Point point = points[i];
            str = str + pointStrFormat(point);
            if(i != points.length - 1){
                str = str + arraySeparator;
            }
        }
        return str;
    }

    public static Point[] pointArrayFromString(String string) throws Exception{
        String[] pointStrings = string.split(arraySeparator);
        Point[] points = new Point[pointStrings.length];
        for(int i = 0; i < points.length; i++){
            Point point = pointFromString(pointStrings[i]);
            points[i] = point;
        }
        return points;
    }
}
