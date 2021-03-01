package be.thomasmore.thirty.helpers;

public class ArrayPersistance {
    private final static String separator = "-";

    public static int[] parseIntArray(String string){
        String[] splitString = string.split(separator);
        int[] parsedInts = new int[splitString.length];
        for(int i = 0; i < splitString.length; i++){
            String subString = splitString[i];
            try {
                int parsedNum = Integer.parseInt(subString);
                parsedInts[i] = parsedNum;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return parsedInts;
    }

    public static String formatIntArray(int[] array){
        String result = "";
        for(int i = 0; i < array.length; i++){
            result = result + array[i];
            if(i != array.length-1){
                result = result + separator;
            }
        }
        return result;
    }

    public static String[] parseStringArray(String string){
        return string.split(separator);
    }

    public static String formatStringArray(String[] array){
        String result = "";
        for(int i = 0; i < array.length; i++){
            result = result + array[i];
            if(i != array.length-1){
                result = result + separator;
            }
        }
        return result;
    }
}
