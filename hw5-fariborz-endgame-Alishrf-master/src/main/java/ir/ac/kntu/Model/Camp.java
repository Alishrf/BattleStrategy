package ir.ac.kntu.Model;

import ir.ac.kntu.Controller.GameController;

import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class Camp implements Serializable {
    private ArrayList<HeroCardName> team1HeroCards;
    private ArrayList<HeroCardName> team2HeroCards;

    public Camp(ArrayList<HeroCardName> team1HeroCards,
                ArrayList<HeroCardName> team2HeroCards) {
        this.team1HeroCards = team1HeroCards;
        this.team2HeroCards = team2HeroCards;
    }


    public void addNewHerocardForTeam1(Cell cell){
        getNewHeroCard(team2HeroCards,cell,true);

    }
    public void addNewHerocardForTeam2(Cell cell){
        getNewHeroCard(team1HeroCards,cell,false);

    }

    private void getNewHeroCard(ArrayList<HeroCardName> team1HeroCards,Cell cell,boolean isTeam1) {
        Random random = new Random(LocalTime.now().getNano());
        int heroIndex = (int) (random.nextDouble() * team1HeroCards.size());
        if(isTeam1) {
            GameController.getBattleField().addTeam1hero(team1HeroCards.get(heroIndex), cell);
            double mana = GameController.getBattleField().getPlayer1Mana() +
                    GameController.getBattleField().getHeroByName(team1HeroCards.get(heroIndex)).getMana();
            GameController.getBattleField().setPlayer1Mana(mana);
        }else {
            double mana = GameController.getBattleField().getPlayer2Mana() +
                    GameController.getBattleField().getHeroByName(team1HeroCards.get(heroIndex)).getMana();
            GameController.getBattleField().setPlayer2Mana(mana);
            GameController.getBattleField().addTeam2hero(team1HeroCards.get(heroIndex), cell);
        }

    }
}
