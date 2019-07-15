package ir.ac.kntu.Model;

import java.io.Serializable;

public class HeroCard implements Serializable {
    private HeroCardConstants heroCardConstants;
    private double currentHP;
    private int currentX;
    private int currentY;
    private int previousX;
    private int previousY;

    public HeroCardConstants getHeroCardConstants() {
        return heroCardConstants;
    }

    public void setPreviousX(int previousX) {
        this.previousX = previousX;
    }

    public void setPreviousY(int previousY) {
        this.previousY = previousY;
    }

    public int getPreviousX() {
        return previousX;
    }

    public int getPreviousY() {
        return previousY;
    }

    public HeroCard(HeroCardConstants heroCardConstants,
                    double currentHP, int currentX, int currentY) {
        this.heroCardConstants = heroCardConstants;
        this.currentHP = currentHP;
        this.currentX = currentX;
        this.currentY = currentY;
    }

    public HeroCard(HeroCardName heroCardName, HeroType heroType,
                    double mana, double HP, double speed,
                    DamageType damageType, double damage,int range) {
        this.heroCardConstants = new HeroCardConstants(heroCardName,heroType,mana,
                HP,speed,damageType,damage,range);
        currentHP=heroCardConstants.getHP();


    }

    public double getCurrentHP() {
        return currentHP;
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public void setCurrentHP(double currentHP) {
        this.currentHP = currentHP;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    public HeroCardName getHeroCardName() {
        return heroCardConstants.getHeroCardName();
    }

    public HeroType getHeroType() {
        return heroCardConstants.getHeroType();
    }

    public double getMana() {
        return heroCardConstants.getMana();
    }

    public double getHP() {
        return heroCardConstants.getHP();
    }

    public double getSpeed() {
        return heroCardConstants.getSpeed();
    }

    public DamageType getDamageType() {
        return heroCardConstants.getDamageType();
    }

    public double getDamage() {
        return heroCardConstants.getDamage();
    }

    public int getRange() {
        return heroCardConstants.getRange();
    }
}
