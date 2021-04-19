package be.thomasmore.thirty.helpers;

public class Chance {

    private double chance;

    public Chance(double chanceBetweenOAnd1){
        if(chanceBetweenOAnd1 < 0 || chanceBetweenOAnd1 > 1){
            System.out.println("Chance out of bounds");
            chance = -1;
        }
    }

    public boolean evaluate(){
        double randVal = Math.random();
        if(randVal > chance){
            return false;
        }
        return true;
    }
}
