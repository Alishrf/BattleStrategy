package ir.ac.kntu.Model;

public class Knight extends HeroCard {

    public Knight() {

        super(HeroCardName.KNIGHT, HeroType.MELEE,30,
                600, 2, DamageType.ENEMYHEROES, 400, 1);
    }
}
