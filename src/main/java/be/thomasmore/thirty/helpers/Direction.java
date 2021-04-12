package be.thomasmore.thirty.helpers;

import java.awt.*;
import java.util.Random;

public enum Direction {

    Left(-1, 0), Right(1, 0), Down(0, -1), Up(0, 1);

    private Point vector;

    Direction(int x, int y) {
        this.vector = new Point(x, y);
    }

    private static int numDirs(){
        int numDirs = Direction.values().length;
        return numDirs;
    }

    private static Direction getDirection(int index){
        Direction dirVal = Direction.values()[index];
        return dirVal;
    }

    private static Point getVector(int index){
        Direction dirVal = getDirection(index);
        Point vect = dirVal.getVector();
        return vect;
    }

    public static Point[] getNeighbors(Point point){
        Point[] neighbors = new Point[numDirs()];
        for(int dirIndex = 0; dirIndex < numDirs(); dirIndex++){
            Direction dir = getDirection(dirIndex);
            Point neighbor = getNeighbor(point, dir);
            neighbors[dirIndex] = neighbor;
        }
        return neighbors;
    }

    public static Point getNeighbor(Point point, Direction direction){
        int dirIndex = direction.ordinal();
        Point vect = getVector(dirIndex);
        Point neighbor = new Point(point.x + vect.x, point.y + vect.y);
        return neighbor;
    }

    public static Point nextDirection(){
        Random rand = new Random();
        int nextIndex = rand.nextInt(numDirs());
        Point direction = getVector(nextIndex);
        return direction;
    }

    public static int getDirectionTo(Point from, Point to){
        Point difference = new Point(to.x - from.x, to.y - from.y);
        for(int dirIndex = 0; dirIndex < numDirs(); dirIndex++){
            Point vect = getVector(dirIndex);
            if(vect.equals(difference)){
                return dirIndex;
            }
        }
        return -1;
    }

    public static boolean isHorizontal(Direction direction){
        return direction == Direction.Left || direction == Direction.Right;
    }

    public static Point[] perpendicularDirections(Direction direction){
        if(isHorizontal(direction)){
            return new Point[]{ getVector(2), getVector(3) };
        }
        return new Point[]{ getVector(0), getVector(1) };
    }

    public Point getVector() {
        return vector;
    }
}