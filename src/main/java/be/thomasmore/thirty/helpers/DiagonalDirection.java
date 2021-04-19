package be.thomasmore.thirty.helpers;

import java.awt.*;

public enum DiagonalDirection {

    UpLeft(-1, 1),  UpRight(1, 1), DownRight(1, -1), DownLeft(-1, -1);

    private Point vector;

    DiagonalDirection(int x, int y) {
        this.vector = new Point(x, y);
    }

    public Point getVector() {
        return vector;
    }

    private static int numDirs(){
        int numDirs = Direction.values().length;
        return numDirs;
    }

    private static DiagonalDirection getDirection(int index){
        DiagonalDirection dirVal = DiagonalDirection.values()[index];
        return dirVal;
    }

    private static Point getVector(int index){
        DiagonalDirection dirVal = getDirection(index);
        Point vect = dirVal.getVector();
        return vect;
    }

    public static Point[] getDiagonalNeighbors(Point point){
        Point[] neighbors = new Point[numDirs()];
        for(int dirIndex = 0; dirIndex < numDirs(); dirIndex++){
            DiagonalDirection dir = getDirection(dirIndex);
            Point neighbor = getNeighbor(point, dir);
            neighbors[dirIndex] = neighbor;
        }
        return neighbors;
    }

    public static Point getNeighbor(Point point, DiagonalDirection direction){
        int dirIndex = direction.ordinal();
        Point vect = getVector(dirIndex);
        Point neighbor = new Point(point.x + vect.x, point.y + vect.y);
        return neighbor;
    }
}
