package ir.ac.kntu.Model;

import java.io.Serializable;

public class TowerCard implements Serializable {
    private TowerCardConstants towerCardConstants;
    private int currentX;
    private int currentY;
    private double currentHP;

    public TowerCard(double damage, double HP, int mana, int range,DamageType damageType,TowerName towerName) {
        this.towerCardConstants = new TowerCardConstants(towerName,damage,HP,mana,range,damageType);
        currentHP=towerCardConstants.getHP();

    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    public void setCurrentHP(double currentHP) {
        this.currentHP = currentHP;
    }

    public double getCurrentHP() {
        return currentHP;

    }
    public TowerName getTowerName() {
        return towerCardConstants.getTowerName();
    }

    public double getDamage() {
        return towerCardConstants.getDamage();
    }

    public double getHP() {
        return towerCardConstants.getHP();
    }

    public int getMana() {
        return towerCardConstants.getMana();
    }

    public int getRange() {
        return towerCardConstants.getRange();
    }

    public DamageType getDamageType() {
        return towerCardConstants.getDamageType();
    }
}
