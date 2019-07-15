package ir.ac.kntu.Model;

public class HeroCardConstants {
    private HeroCardName heroCardName;
    private HeroType heroType;
    private double mana;
    private double HP;
    private double speed;
    private DamageType damageType;
    private double damage;
    private int range;

    public HeroCardConstants(HeroCardName heroCardName, HeroType heroType,
                             double mana, double HP, double speed,
                             DamageType damageType, double damage,int range) {


        this.heroCardName = heroCardName;
        this.heroType = heroType;
        this.mana = mana;
        this.HP = HP;
        this.speed = speed;
        this.damageType = damageType;
        this.damage = damage;
        this.range = range;
    }


    public HeroCardName getHeroCardName() {
        return heroCardName;
    }

    public HeroType getHeroType() {
        return heroType;
    }

    public double getMana() {
        return mana;
    }

    public double getHP() {
        return HP;
    }

    public double getSpeed() {
        return speed;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public double getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }
}
