package ir.ac.kntu.Model;

public class Dragon extends HeroCard {

    public Dragon() {

        super(HeroCardName.DRAGON, HeroType.RANGE, 35,
                500, 2, DamageType.ENEMYHEROES, 350, 3);
    }
}
