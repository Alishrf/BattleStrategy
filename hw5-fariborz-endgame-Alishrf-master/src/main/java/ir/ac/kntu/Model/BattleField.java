package ir.ac.kntu.Model;


import ir.ac.kntu.Controller.GameController;
import ir.ac.kntu.RandomHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BattleField {
    private double maxMana;
    private String player1Name;
    private String player2Name;
    private double player1Mana;
    private double player2Mana;
    private double player1Health;
    private double player2Health;
    private double addingManaPlayer1;
    private double addingManaPlayer2;
    private Map map;

    private HandleSection handleSection;

    public BattleField(Map map) {
        this.player1Name = "Fariborz";
        this.player2Name = "Farinaz";
        this.player1Mana = 120;
        this.player2Mana = 120;
        this.player1Health = 3;
        this.player2Health = 3;
        this.addingManaPlayer1 = 2;
        this.addingManaPlayer2 = 2;
        this.maxMana=120;
        this.map = map;
        handleSection=new HandleSection(map);
    }

    public void setPlayer1Mana(double player1Mana) {
        this.player1Mana = player1Mana;
    }

    public void setPlayer2Mana(double player2Mana) {
        this.player2Mana = player2Mana;
    }


    public static boolean start=false;

    public boolean isStart() {
        return start;
    }

    public static void setStart(boolean start) {
        BattleField.start = start;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }


    public void reducePlayer1Health(double player1Health) {
        this.player1Health = player1Health - 1;
    }

    public void reducePlayer2Health(double player2Health) {
        this.player2Health = player2Health -1;
    }

    public void addMana() {
        player1Mana = (player1Mana +addingManaPlayer2 > maxMana) ? maxMana:player1Mana +addingManaPlayer1;
        player2Mana =(player2Mana +addingManaPlayer2 > maxMana) ? maxMana:player2Mana +addingManaPlayer2;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public double getPlayer1Mana() {
        return player1Mana;
    }

    public double getPlayer2Mana() {
        return player2Mana;
    }

    public double getPlayer1Health() {
        return player1Health;
    }

    public double getPlayer2Health() {
        return player2Health;
    }

    public double getAddingManaPlayer1() {
        return addingManaPlayer1;
    }
    public double getAddingManaPlayer2() {
        return addingManaPlayer2;
    }

    public Map getMap() {
        return map;
    }
    public boolean addTeam1Tower(TowerName towerName, Cell cell){
        TowerCard towerCard = getTowerByName(towerName);
        if(player1Mana >= towerCard.getMana()
                && cell.isTowerZone()
                && map.getTeam1TowerCards().size() < 3){

            player1Mana -= towerCard.getMana();
            towerCard.setCurrentX(cell.getX());
            towerCard.setCurrentY(cell.getY());
            map.getTeam1TowerCards().add(towerCard);
            System.out.println(towerCard + " Team1 created");
            return true;
        }else {
            return false;
        }
    }
    public boolean addTeam1hero(HeroCardName heroCardName , Cell cell){
        HeroCard heroCard = getHeroByName(heroCardName);
        if(player1Mana >= heroCard.getMana()){
            player1Mana -=heroCard.getMana();
            heroCard.setCurrentX(cell.getX());
            heroCard.setCurrentY(cell.getY());
            map.getTeam1AliveHeroCards().add(heroCard);
            return true;
        }else {
            return false;
        }
    }
    
    public boolean addTeam2hero(HeroCardName heroCardName , Cell cell){
        HeroCard heroCard = getHeroByName(heroCardName);
        if(player2Mana >= heroCard.getMana()){
            player2Mana -=heroCard.getMana();
            heroCard.setCurrentX(cell.getX());
            heroCard.setCurrentY(cell.getY());
            map.getTeam2AliveHeroCards().add(heroCard);
            return true;
        }else {
            return false;
        }
    }

    public boolean addTeam2Tower(TowerName towerName, Cell cell){
        TowerCard towerCard = getTowerByName(towerName);
        if(player2Mana >= towerCard.getMana()
                && cell.isTowerZone()
                && map.getTeam2TowerCards().size() < 3){

            player2Mana -=towerCard.getMana();
            towerCard.setCurrentX(cell.getX());
            towerCard.setCurrentY(cell.getY());
            map.getTeam2TowerCards().add(towerCard);
            System.out.println(towerCard + " Team2 created");
            return true;
        }else {
            return false;
        }
    }
    public void shootTowers(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(TowerCard t:map.getTeam1TowerCards()){
            executorService.execute(() -> handleSection.shooTeam1Tower(t));
        }
        for(TowerCard t:map.getTeam2TowerCards()){
            executorService.execute(() -> handleSection.shooTeam2Tower(t));
        }
        executorService.shutdown();
    }
    public void deleteDeadBodys(){
        handleSection.deleteDeadBodies();
    }
    public void AttackHeroes(){
        handleSection.firstAttackHeroes();
        handleSection.secondAttackHeroes();
    }
    public void moveHeroes(){
        handleSection.firstMoveHeroes();
        handleSection.secondMoveHeroes();
    }
    public boolean checkEndGame(){
        for(HeroCard h:map.getTeam1AliveHeroCards()){
            if(map.getCell(h.getCurrentX(),h.getCurrentY()).isRespawnZone() &&
                    h.getCurrentX()<10){
                h.setCurrentHP(0);
                player2Health--;
                if(player2Health==0){
                    return false;
                }
            }
        }
        for(HeroCard h:map.getTeam2AliveHeroCards()){
            if(map.getCell(h.getCurrentX(),h.getCurrentY()).isRespawnZone() &&
                    h.getCurrentX()>10){
                h.setCurrentHP(0);
                player1Health--;
                if(player1Health==0){
                    return false;
                }
            }
        }
        return true;
    }
    public void addTeam1Camp(Cell cell){
        Camp camp=new Camp(GameController.getBattleField().getMap().getPlayer1HeroCardNames(),
                GameController.getBattleField().getMap().getPlayer2HeroCardNames());
        map.getTeam1Camp().add(camp);
        addingManaPlayer1/=4;
    }
    public void addTeam2Camp(Cell cell){
        Camp camp=new Camp(GameController.getBattleField().getMap().getPlayer1HeroCardNames(),
                GameController.getBattleField().getMap().getPlayer2HeroCardNames());
        map.getTeam2Camp().add(camp);
        addingManaPlayer2/=4;
    }
    public void getHeroCamp1(){
        int rand= (int)(RandomHelper.nextDouble()*3);
        Cell cell;
        if(rand==0){
            cell=map.getCell(19,5);
        }else if(rand==1){
            cell=map.getCell(19,9);
        }else {
            cell=map.getCell(19,13);
        }
        for(Camp c:map.getTeam1Camp()){
            c.addNewHerocardForTeam1(cell);
        }

    }
    public void getHeroCamp2(){
        int rand= (int)(RandomHelper.nextDouble()*3);
        Cell cell;
        if(rand==0){
            cell=map.getCell(0,5);
        }else if(rand==1){
            cell=map.getCell(0,9);
        }else {
            cell=map.getCell(0,13);
        }
        for(Camp c:map.getTeam2Camp()){
            c.addNewHerocardForTeam2(cell);
        }

    }

    public TowerCard getTowerByName(TowerName towerName){
        switch (towerName){
            case BLACK:
                BlackTower blackTower = new BlackTower();
                return blackTower;
            case ELECTRIC:
                ElectricTower electricTower=new ElectricTower();
                return electricTower;
            default:
                HospitalTower hospitalTower = new HospitalTower();
                return hospitalTower;
        }

    }
    public HeroCard getHeroByName(HeroCardName heroCardName){
        switch (heroCardName){
            case ARCHER:
                Archer archer=new Archer();
                return archer;
            case SWORDSMAN:
                Swordsman swordsman=new Swordsman();
                return swordsman;
            case SHIELD:
                Shield shield=new Shield();
                return shield;
            case KNIGHT:
                Knight knight=new Knight();
                return knight;
            case HEALER:
                Healer healer=new Healer();
                return healer;
            case GOBLIN:
                Goblin goblin=new Goblin();
                return goblin;
            default:
                Dragon dragon=new Dragon();
                return dragon;

        }

    }
}
