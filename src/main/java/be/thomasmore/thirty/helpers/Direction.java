package be.thomasmore.thirty.helpers;

import java.awt.*;
import java.util.Random;

public enum Direction {

    Left, Right, Down, Up;

    private Point direction;

    public final static Point[] directions = {
            new Point(-1, 0),
            new Point(1, 0),
            new Point(0, -1),
            new Point(0, 1),
    };

    public static Point[] getNeighbors(Point point){
        Point[] neighbors = new Point[directions.length];
        for(int dirIndex = 0; dirIndex < directions.length; dirIndex++){
            Point dir = directions[dirIndex];
            Point neighbor = new Point(point.x + dir.x, point.y + dir.y);
            neighbors[dirIndex] = neighbor;
        }
        return neighbors;
    }

    public static Point getNeighbor(Point point, Direction direction){
        int dirIndex = direction.ordinal();
        Point dir = directions[dirIndex];
        Point neighbor = new Point(point.x + dir.x, point.y + dir.y);
        return neighbor;
    }

    public static Point nextDirection(){
        Random rand = new Random();
        int nextIndex = rand.nextInt(Direction.directions.length);
        Point direction = Direction.directions[nextIndex];
        return direction;
    }

    public static int getDirectionTo(Point from, Point to){
        Point difference = new Point(to.x - from.x, to.y - from.y);
        for(int i = 0; i < Direction.directions.length; i++){
            if(Direction.directions[i].equals(difference)){
                return i;
            }
        }
        return -1;
    }

    public static boolean isHorizontal(Direction direction){
        return direction == Direction.Left || direction == Direction.Right;
    }

    public static Point[] perpendicularDirections(Direction direction){
        if(isHorizontal(direction)){
            return new Point[]{ directions[2], directions[3] };
        }
        return new Point[]{ directions[0], directions[1] };
    }
}