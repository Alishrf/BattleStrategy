package ir.ac.kntu.Model;

import ir.ac.kntu.RandomHelper;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HandleSection {
    private Map map;

    public HandleSection(Map map) {
        this.map = map;

    }
    public void shooTeam1Tower(TowerCard towerCard){
        ArrayList<HeroCard> heroes = new ArrayList<HeroCard>();
        for(HeroCard h:map.getTeam2AliveHeroCards()){
            if(towerCard.getRange()>= map.getDistance(towerCard,h)){
                heroes.add(h);
            }
        }
        if (heroes.size()==0){
            return;
        }
        int rand = (int) RandomHelper.nextDouble() * heroes.size();
        heroes.get(rand).setCurrentHP(heroes.get(rand).getCurrentHP() - towerCard.getDamage());
        if (heroes.get(rand).getCurrentHP() < 0){
            heroes.get(rand).setCurrentHP(0);
        }
        System.out.println(towerCard.getTowerName() + "  " + " shoot " + heroes.get(rand));

    }
    public void shooTeam2Tower(TowerCard towerCard){
        ArrayList<HeroCard> heroes = new ArrayList<HeroCard>();
        for(HeroCard h:map.getTeam1AliveHeroCards()){
            if(towerCard.getRange()>= map.getDistance(towerCard,h)){
                heroes.add(h);
            }
        }
        if (heroes.size()==0){
            return;
        }
        int rand = (int) RandomHelper.nextDouble() * heroes.size();
        heroes.get(rand).setCurrentHP(heroes.get(rand).getCurrentHP() - towerCard.getDamage());
        if (heroes.get(rand).getCurrentHP() < 0){
            heroes.get(rand).setCurrentHP(0);
        }
        System.out.println(towerCard.getTowerName() + "  " + " shoot " + heroes.get(rand));

    }
    public void deleteDeadBodies(){
        for (int i = 0; i < map.getTeam1TowerCards().size(); i++) {
            if(map.getTeam1TowerCards().get(i).getCurrentHP()==0){
                map.getTeam1TowerCards().remove(i);
                i--;
            }
        }
        for (int i = 0; i < map.getTeam2TowerCards().size(); i++) {
            if(map.getTeam2TowerCards().get(i).getCurrentHP()==0){
                map.getTeam2TowerCards().remove(i);
                i--;
            }
        }
        for (int i = 0; i < map.getTeam1AliveHeroCards().size(); i++) {
            if(map.getTeam1AliveHeroCards().get(i).getCurrentHP()==0){
                map.getTeam1AliveHeroCards().remove(i);
                i--;
            }
        }
        for (int i = 0; i < map.getTeam2AliveHeroCards().size(); i++) {
            if(map.getTeam2AliveHeroCards().get(i).getCurrentHP()==0){
                map.getTeam2AliveHeroCards().remove(i);
                i--;
            }
        }
    }
    public void attackTeam1Hero(HeroCard heroCard){
        if(heroCard.getCurrentHP()==0){
            return;
        }
        ArrayList<HeroCard> heroes =new ArrayList<>();
        ArrayList<TowerCard> towers =new ArrayList<>();
        for(HeroCard h :map.getTeam2AliveHeroCards()){
            if(heroCard.getRange() >= map.getDistance(h,heroCard)){
                if(heroCard.getHeroType() == HeroType.RANGE ||
                        heroCard.getCurrentX()==h.getCurrentX() ||
                        heroCard.getCurrentY()==h.getCurrentY()){
                    heroes.add(h);
                }
            }
        }
        for(TowerCard t:map.getTeam2TowerCards()){
            if(heroCard.getRange() >= map.getDistance(t,heroCard)){
                if(heroCard.getHeroType() == HeroType.RANGE ||
                        heroCard.getCurrentX()==t.getCurrentX() ||
                        heroCard.getCurrentY()==t.getCurrentY()){
                    towers.add(t);
                }
            }
        }
        if((towers.size() + heroes.size()) ==0 ){
            return;
        }
        int rand =(int) RandomHelper.nextDouble() * (towers.size() + heroes.size());
        if(rand < heroes.size()){
            heroes.get(rand).setCurrentHP(heroes.get(rand).getCurrentHP()- heroCard.getDamage());
            if(heroes.get(rand).getCurrentHP() < 0 ){
                heroes.get(rand).setCurrentHP(0);
                System.out.println(heroCard.getHeroCardName() + " " + " Attacked " + heroes.get(rand));
            }
        }else {
            rand-=heroes.size();
            towers.get(rand).setCurrentHP(towers.get(rand).getCurrentHP()-heroCard.getDamage());
            if(towers.get(rand).getCurrentHP() < 0){
                towers.get(rand).setCurrentHP(0);
            }
            System.out.println(heroCard.getHeroCardName() + " " + " Attacked " + towers.get(rand));
        }

    }

    public void attackTeam2Hero(HeroCard heroCard){
        if(heroCard.getCurrentHP()==0){
            return;
        }
        ArrayList<HeroCard> heroes =new ArrayList<>();
        ArrayList<TowerCard> towers =new ArrayList<>();
        for(HeroCard h :map.getTeam1AliveHeroCards()){
            if(heroCard.getRange() >= map.getDistance(h,heroCard)){
                if(heroCard.getHeroType() == HeroType.RANGE ||
                        heroCard.getCurrentX()==h.getCurrentX() ||
                        heroCard.getCurrentY()==h.getCurrentY()){
                    heroes.add(h);
                }
            }
        }
        for(TowerCard t:map.getTeam1TowerCards()){
            if(heroCard.getRange() >= map.getDistance(t,heroCard)){
                if(heroCard.getHeroType() == HeroType.RANGE ||
                        heroCard.getCurrentX()==t.getCurrentX() ||
                        heroCard.getCurrentY()==t.getCurrentY()){
                    towers.add(t);
                }
            }
        }
        if((towers.size() + heroes.size()) ==0 ){
            return;
        }
        int rand =(int) RandomHelper.nextDouble() * (towers.size() + heroes.size());
        System.out.println("raaaaaaaaaaand =" + rand);
        if(rand < heroes.size()){
            heroes.get(rand).setCurrentHP(heroes.get(rand).getCurrentHP() - heroCard.getDamage());
            if(heroes.get(rand).getCurrentHP() < 0 ){
                heroes.get(rand).setCurrentHP(0);
                System.out.println(heroCard.getHeroCardName() + " " + " Attacked " + heroes.get(rand));
            }
        }else {
            rand-=heroes.size();
            towers.get(rand).setCurrentHP(towers.get(rand).getCurrentHP() - heroCard.getDamage());
            if(towers.get(rand).getCurrentHP() < 0){
                towers.get(rand).setCurrentHP(0);
            }
            System.out.println(heroCard.getHeroCardName() + " " + " Attacked " + towers.get(rand));
        }

    }

    public void firstAttackHeroes(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(HeroCard h:map.getTeam1AliveHeroCards()){
            if(map.getCell(h.getCurrentX(),h.getCurrentY()).isTeam1()){
                executorService.execute(() -> attackTeam1Hero(h));
                System.out.println(h.getHeroCardName() + "Hp" + h.getCurrentHP());
            }
        }
        for(HeroCard h:map.getTeam2AliveHeroCards()){
            if(map.getCell(h.getCurrentX(),h.getCurrentY()).isTeam2()){
                executorService.execute(() -> attackTeam2Hero(h));
                System.out.println(h.getHeroCardName() + "Hp" + h.getCurrentHP());
            }
        }
        executorService.shutdown();
    }
    public void secondAttackHeroes(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(HeroCard h:map.getTeam1AliveHeroCards()){
            if(map.getCell(h.getCurrentX(),h.getCurrentY()).isTeam2()){
                executorService.execute(() -> attackTeam1Hero(h));
            }
        }
        for(HeroCard h:map.getTeam2AliveHeroCards()){
            if(map.getCell(h.getCurrentX(),h.getCurrentY()).isTeam1()){
                executorService.execute(() -> attackTeam2Hero(h));
            }
        }
        executorService.shutdown();
    }
    public void firstMoveHeroes(){
        ExecutorService executorService=Executors.newCachedThreadPool();
        for (HeroCard h:map.getTeam1AliveHeroCards()){
            if(map.getCell(h.getCurrentX(),h.getCurrentY()).isTeam1()){
                executorService.execute(() -> moveTeam1Hero(h));
            }
        }
        for (HeroCard h:map.getTeam2AliveHeroCards()){
            if(map.getCell(h.getCurrentX(),h.getCurrentY()).isTeam2()){
                executorService.execute(() -> moveTeam2Hero(h));
            }
        }
        executorService.shutdown();
    }
    public void secondMoveHeroes(){
        ExecutorService executorService=Executors.newCachedThreadPool();
        for (HeroCard h:map.getTeam1AliveHeroCards()){
            if(map.getCell(h.getCurrentX(),h.getCurrentY()).isTeam2()){
                executorService.execute(() -> moveTeam1Hero(h));
            }
        }
        for (HeroCard h:map.getTeam2AliveHeroCards()){
            if(map.getCell(h.getCurrentX(),h.getCurrentY()).isTeam1()){
                executorService.execute(() -> moveTeam2Hero(h));
            }
        }
        executorService.shutdown();
    }
    public void moveTeam1Hero(HeroCard heroCard){
       Cell nextCell=map.getCell(heroCard.getCurrentX(),heroCard.getCurrentY());
       boolean sign ;
        for (int i = 0; i < heroCard.getSpeed(); i++) {
            sign = false;
            Cell varNextCell = map.nextTeam1RouteCell(nextCell,heroCard);
            if(varNextCell.isRespawnZone()){
                nextCell = varNextCell;
                break;
            }
            for(HeroCard h:map.getTeam2AliveHeroCards()){
                if(h.getCurrentX() == varNextCell.getX() &&
                h.getCurrentY() == varNextCell.getY()){
                    sign= true;
                    break;
                }
            }
            if(sign){
                break;
            }
            nextCell = varNextCell;
        }
        heroCard.setCurrentX(nextCell.getX());
        heroCard.setCurrentY(nextCell.getY());
        System.out.println(heroCard.getHeroCardName() +
                "  To " +nextCell.getY() + " " +nextCell.getY());

    }
    public void moveTeam2Hero(HeroCard heroCard){
        Cell nextCell=map.getCell(heroCard.getCurrentX(),heroCard.getCurrentY());
        boolean sign ;
        for (int i = 0; i < heroCard.getSpeed(); i++) {
            sign = false;
            Cell varNextCell = map.nextTeam2RouteCell(nextCell,heroCard);
            if(varNextCell.isRespawnZone()){
                nextCell = varNextCell;
                break;
            }
            for(HeroCard h:map.getTeam1AliveHeroCards()){
                if(h.getCurrentX() == varNextCell.getX() &&
                        h.getCurrentY() == varNextCell.getY()){
                    sign= true;
                    break;
                }
            }
            if(sign){
                break;
            }
            nextCell = varNextCell;
        }
        heroCard.setCurrentX(nextCell.getX());
        heroCard.setCurrentY(nextCell.getY());
        System.out.println(heroCard.getHeroCardName() +
                " Moved  To " +nextCell.getY() + " " +nextCell.getY());

    }



}
