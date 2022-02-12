package a1.anicholas;

public class Munitions extends Item {
    private float damage;

    public Munitions() {
        super();
        this.damage = 0;
    }

    public Munitions(String name, double price, int qty, float damage) {
        super(name, price, qty);
        this.damage = damage;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    @Override
    public String toString() {
        return String.format("%s %7s %-20s |", super.toString(), "|", this.damage);
    }
}
