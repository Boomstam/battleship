package be.thomasmore.thirty.model;

public enum WeaponType {
    Gun(1), Aircraft(1), Torpedo(2), Depth(1), Mine(2);

    private int damage;

    WeaponType(int damage) {
        this.damage = damage;
    }
}
