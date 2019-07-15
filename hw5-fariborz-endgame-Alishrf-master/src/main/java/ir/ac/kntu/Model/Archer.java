package ir.ac.kntu.Model;

public class Archer extends HeroCard {

    public Archer() {

        super(HeroCardName.ARCHER, HeroType.RANGE, 15,
                300, 1, DamageType.ENEMYHEROES, 200, 2);
    }
}
