package ir.ac.kntu.Model;

public class TowerCardConstants {
    private TowerName towerName;
    private double damage;
    private double HP;
    private int mana;
    private int range;
    private DamageType damageType;


    public TowerCardConstants(TowerName towerName, double damage, double HP,
                              int mana, int range, DamageType damageType) {
        this.towerName = towerName;
        this.damage = damage;
        this.HP = HP;
        this.mana = mana;
        this.range = range;
        this.damageType = damageType;
    }

    public TowerName getTowerName() {
        return towerName;
    }

    public double getDamage() {
        return damage;
    }

    public double getHP() {
        return HP;
    }

    public int getMana() {
        return mana;
    }

    public int getRange() {
        return range;
    }

    public DamageType getDamageType() {
        return damageType;
    }
}
