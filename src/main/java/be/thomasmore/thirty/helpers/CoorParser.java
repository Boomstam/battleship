package be.thomasmore.thirty.helpers;

import java.awt.*;

public class CoorParser {
    public static Point parse(String locationString) {
        String[] coors = locationString.split("_");
        int x = 0, y = 0;
        try {
            x = Integer.parseInt(coors[0]);
            y = Integer.parseInt(coors[1]);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new Point(x, y);
    }
}