/*package be.thomasmore.thirty.model;

public enum ShipType {
    Minesweeper, Frigate, Submarine, Destroyer, Cruiser, Carrier;

    public static ShipType fromString(String str) throws Exception {
        for(ShipType type : ShipType.values()){
            if(str.equals(type.toString())){
                return type;
            }
        }
        throw new Exception("Ship type not found for " + str);
    }
}

    public ShipType shipTypeEnum() {
        try{
             ShipType type = ShipType.fromString(shipType);
             return type;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

*/