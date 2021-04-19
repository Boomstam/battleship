package be.thomasmore.thirty.model;

public enum WeaponType {
    Gun(1), Aircraft(1), Torpedo(2), Depth(1), Mine(2);

    public int getDamage() {
        return damage;
    }

    private int damage;

    WeaponType(int damage) {
        this.damage = damage;
    }
}
