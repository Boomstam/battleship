package be.thomasmore.thirty.model;

import java.awt.*;
import java.util.Objects;

public class HashedPoint {
    private int x;
    private int y;

    public HashedPoint(int x, int y){
        this.x = x;
        this.y = y;
    }

    public HashedPoint(Point point){
        x = point.x;
        y = point.y;
    }

    public Point asPoint(){
        return new Point(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashedPoint hashedPoint = (HashedPoint) o;
        return x == hashedPoint.x &&
                y == hashedPoint.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "HashedPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}