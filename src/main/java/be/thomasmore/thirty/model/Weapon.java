package be.thomasmore.thirty.model;

public class Weapon {
    private WeaponType type;
    private int range;
    private int ammo;

    public Weapon(WeaponType type, int range, int ammo) {
        this.type = type;
        this.range = range;
        this.ammo = ammo;
    }

    public Weapon(int type, int range, int ammo) {
        this.type = WeaponType.values()[type];;
        this.range = range;
        this.ammo = ammo;
    }

    public WeaponType getType() {
        return type;
    }

    public void setType(WeaponType type) {
        this.type = type;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "type=" + type +
                ", range=" + range +
                ", ammo=" + ammo +
                '}';
    }
}