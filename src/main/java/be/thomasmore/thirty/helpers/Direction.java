package be.thomasmore.thirty.helpers;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

public enum Direction {

    Up(0, 1),  Right(1, 0), Down(0, -1), Left(-1, 0);

    private Point vector;

    Direction(int x, int y) {
        this.vector = new Point(x, y);
    }

    public Point getVector() {
        return vector;
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

    public static Point[] getNeighborsIncludingDiagonal(Point point){
        Point[] neighbors = getNeighbors(point);
        Point[] diagonalNeighbors = DiagonalDirection.getDiagonalNeighbors(point);
        ArrayList<Point> all = new ArrayList<>();
        all.addAll(Arrays.asList(neighbors));
        all.addAll(Arrays.asList(diagonalNeighbors));
        return all.toArray(new Point[0]);
    }

    public static Point getNeighbor(Point point, Direction direction){
        int dirIndex = direction.ordinal();
        Point vect = getVector(dirIndex);
        Point neighbor = new Point(point.x + vect.x, point.y + vect.y);
        return neighbor;
    }

    public static Point randomDirection(){
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
        System.out.println("can't find direction_" + from + "_" + to);
        return -1;
    }

    public static boolean isHorizontal(Direction direction){
        return direction == Direction.Left || direction == Direction.Right;
    }

    public static Direction opposite(Direction direction){
        switch (direction){
            case Left:
                return Direction.Right;
            case Right:
                return Direction.Left;
            case Down:
                return Direction.Up;
            case Up:
                return Direction.Down;
        }
        System.out.println("Opposite not found_" + direction);
        return null;
    }

    public static Point[] perpendicularDirections(Direction direction){
        if(isHorizontal(direction)){
            return new Point[]{ Direction.Up.getVector(), Direction.Down.getVector() };
        }
        return new Point[]{ Direction.Left.getVector(), Direction.Right.getVector() };
    }

    public static Direction nextClockwise(Direction direction){
        int dirIndex = (direction.ordinal() + 1) % numDirs();
        return getDirection(dirIndex);
    }

    public static Direction nextCounterClockwise(Direction direction){
        int dirIndex = (Math.abs(direction.ordinal() - 1)) % numDirs();
        return getDirection(dirIndex);
    }
}