package ir.ac.kntu.Model;

public class Shield extends HeroCard {

    public Shield() {

        super(HeroCardName.SHIELD, HeroType.MELEE,
                10, 1000, 1, DamageType.ENEMYHEROES, 150, 1);
    }
}
