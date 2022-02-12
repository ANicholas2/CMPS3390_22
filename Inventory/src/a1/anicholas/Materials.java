package a1.anicholas;

public class Materials extends Item {
    private float strength;

    public Materials() {
        super();
        this.strength = 0;
    }

    public Materials(String name, double price, int qty, float strength) {
        super(name, price, qty);
        this.strength = strength;
    }

    public float getStrength() {
        return strength;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }

    @Override
    public String toString() {
        return String.format("%s %7s %-20s |", super.toString(), "|", this.strength);
    }
}
