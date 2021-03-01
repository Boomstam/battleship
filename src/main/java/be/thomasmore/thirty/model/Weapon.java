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
}