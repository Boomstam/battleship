package be.thomasmore.thirty.helpers;

import java.awt.*;
import java.util.Random;

public enum Direction {

    left, right, down, up;

    private Point direction;

    public final static Point[] directions = {
            new Point(-1, 0),
            new Point(1, 0),
            new Point(0, -1),
            new Point(0, 1),
    };

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
        return direction == Direction.left || direction == Direction.right;
    }

    public static Point[] perpendicularDirections(Direction direction){
        if(isHorizontal(direction)){
            return new Point[]{ directions[2], directions[3] };
        }
        return new Point[]{ directions[0], directions[1] };
    }
}