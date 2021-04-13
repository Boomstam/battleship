package be.thomasmore.thirty.model;

public enum Player {
    PLAYER_1(true, 0, 0.4f), PLAYER_2(false, 0.6f, 1);

    Player(boolean isHuman, float minMapLimit, float maxMapLimit) {
        this.isHuman = isHuman;
        this.minMapLimit = minMapLimit;
        this.maxMapLimit = maxMapLimit;
    }

    public boolean isHuman() {
        return isHuman;
    }

    public float getMinMapLimit() {
        return minMapLimit;
    }

    public float getMaxMapLimit() {
        return maxMapLimit;
    }

    private boolean isHuman;
    private float minMapLimit;
    private float maxMapLimit;
}