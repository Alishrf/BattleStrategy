package ir.ac.kntu.Model;

import ir.ac.kntu.RandomHelper;

import java.io.Serializable;
import java.util.ArrayList;

public class Map implements Serializable {
    private Cell[][] cells;
    private ArrayList<HeroCard> team1AliveHeroCards;
    private ArrayList<HeroCard> team2AliveHeroCards;
    private ArrayList<TowerCard> team1TowerCards;
    private ArrayList<TowerCard> team2TowerCards;
    private ArrayList<Camp> team1Camp;
    private ArrayList<Camp> team2Camp;
    private ArrayList<HeroCardName> player1HeroCardNames;
    private ArrayList<HeroCardName> player2HeroCardNames;


    public Map(Cell[][] cells) {
        this.cells = cells;
        team1AliveHeroCards =new ArrayList<>();
        team2AliveHeroCards =new ArrayList<>();
        team1TowerCards =new ArrayList<>();
        team2TowerCards=new ArrayList<>();
        team1Camp=new ArrayList<>();
        team2Camp=new ArrayList<>();
        player1HeroCardNames=new ArrayList<>();
        player2HeroCardNames=new ArrayList<>();
    }

    public Cell[][] getCells() {
        return cells;
    }

    public ArrayList<HeroCard> getTeam1AliveHeroCards() {
        return team1AliveHeroCards;
    }

    public ArrayList<HeroCard> getTeam2AliveHeroCards() {
        return team2AliveHeroCards;
    }

    public ArrayList<TowerCard> getTeam1TowerCards() {
        return team1TowerCards;
    }

    public ArrayList<TowerCard> getTeam2TowerCards() {
        return team2TowerCards;
    }

    public ArrayList<Camp> getTeam1Camp() {
        return team1Camp;
    }

    public ArrayList<Camp> getTeam2Camp() {
        return team2Camp;
    }
    public Cell getCell(int x,int y){
        Cell cell=new Cell(30,30,CellType.NONE,false,false);
        try {
            cell= cells[x][y];
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
        return cell;
    }
    public Cell nextTeam1RouteCell(Cell cell,HeroCard heroCard){
        if(getCell(cell.getX()-1,cell.getY()).isRouteCell()){
            heroCard.setPreviousX(cell.getX());
            heroCard.setPreviousY(cell.getY());
            return getCell(cell.getX()-1,cell.getY());
        }
        if(cell.getX()==heroCard.getPreviousX() &&
                cell.getY()-1==heroCard.getPreviousY() &&
                getCell(cell.getX(),cell.getY()+1).isRouteCell()){
            heroCard.setPreviousX(cell.getX());
            heroCard.setPreviousY(cell.getY());
            return getCell(cell.getX(),cell.getY()+1);

        }
        if(cell.getX()==heroCard.getPreviousX() &&
                cell.getY()+1==heroCard.getPreviousY() &&
                getCell(cell.getX(),cell.getY()-1).isRouteCell()){
            heroCard.setPreviousX(cell.getX());
            heroCard.setPreviousY(cell.getY());
            return getCell(cell.getX(),cell.getY()-1);
        }
        if(((cell.getX() + 1) == heroCard.getPreviousX()) &&
                (cell.getY() == heroCard.getPreviousY()) &&
                getCell(cell.getX(), cell.getY() + 1).isRouteCell()){
            return nextHelp(cell, heroCard);
        }
        if(cell.getX()+1==heroCard.getPreviousX() &&
                cell.getY()==heroCard.getPreviousY() &&
                getCell(cell.getX(),cell.getY()-1).isRouteCell()){
            heroCard.setPreviousX(cell.getX());
            heroCard.setPreviousY(cell.getY());
            boolean rand = RandomHelper.nextBoolean();
            if(rand && getCell(cell.getX(),cell.getY()+1).isRouteCell()){
                return getCell(cell.getX(),cell.getY()+1);
            }
            return getCell(cell.getX(),cell.getY()-1);
        }
        heroCard.setPreviousX(cell.getX());
        heroCard.setPreviousY(cell.getY());
        return getCell(cell.getX()+1,cell.getY());

    }

    private Cell nextHelp(Cell cell, HeroCard heroCard) {
        heroCard.setPreviousX(cell.getX());
        heroCard.setPreviousY(cell.getY());
        boolean rand = RandomHelper.nextBoolean();
        if(rand && getCell(cell.getX(),cell.getY()-1).isRouteCell()){
            return getCell(cell.getX(),cell.getY()-1);
        }
        return getCell(cell.getX(),cell.getY()+1);
    }


    public Cell nextTeam2RouteCell(Cell cell,HeroCard heroCard){
        if(getCell(cell.getX()+1,cell.getY()).isRouteCell()){
            heroCard.setPreviousX(cell.getX());
            heroCard.setPreviousY(cell.getY());
            return getCell(cell.getX()+1,cell.getY());
        }
        if(cell.getX()==heroCard.getPreviousX() &&
                cell.getY()-1==heroCard.getPreviousY() &&
                getCell(cell.getX(),cell.getY()+1).isRouteCell()){
            heroCard.setPreviousX(cell.getX());
            heroCard.setPreviousY(cell.getY());
            return getCell(cell.getX(),cell.getY()+1);

        }
        if(cell.getX()==heroCard.getPreviousX() &&
                cell.getY()+1==heroCard.getPreviousY() &&
                getCell(cell.getX(),cell.getY()-1).isRouteCell()){
            heroCard.setPreviousX(cell.getX());
            heroCard.setPreviousY(cell.getY());
            return getCell(cell.getX(),cell.getY()-1);
        }
        if(cell.getX()-1==heroCard.getPreviousX() &&
                cell.getY()==heroCard.getPreviousY() &&
                getCell(cell.getX(),cell.getY()+1).isRouteCell()){
            return nextHelp(cell, heroCard);
        }
        if(cell.getX()-1==heroCard.getPreviousX() &&
                cell.getY()==heroCard.getPreviousY() &&
                getCell(cell.getX(),cell.getY()-1).isRouteCell()){
            heroCard.setPreviousX(cell.getX());
            heroCard.setPreviousY(cell.getY());
            boolean rand = RandomHelper.nextBoolean();
            if(rand && getCell(cell.getX(),cell.getY()+1).isRouteCell()){
                return getCell(cell.getX(),cell.getY()+1);
            }
            return getCell(cell.getX(),cell.getY()-1);
        }
        heroCard.setPreviousX(cell.getX());
        heroCard.setPreviousY(cell.getY());
        return getCell(cell.getX()-1,cell.getY());
    }
    public int getDistance(TowerCard towerCard,HeroCard heroCard){

        return getManhattanDistance(towerCard.getCurrentX(),towerCard.getCurrentY(),
                heroCard.getCurrentX(),heroCard.getCurrentY());
    }
    public int getDistance(HeroCard heroCard1,HeroCard heroCard2){
        return getManhattanDistance(heroCard1.getCurrentX(),heroCard1.getCurrentY(),
                heroCard2.getCurrentX(),heroCard2.getCurrentY());
    }
    public int getManhattanDistance(int x1,int y1,int x2,int y2){
        int xDistance = Math.abs(x1 - x2);
        int yDistance = Math.abs(y1 - y2);
        return (xDistance > yDistance) ? xDistance:yDistance;
    }

    public void setPlayer1HeroCardNames(ArrayList<HeroCardName> player1HeroCardNames) {
        this.player1HeroCardNames = player1HeroCardNames;
    }

    public void setPlayer2HeroCardNames(ArrayList<HeroCardName> player2HeroCardNames) {
        this.player2HeroCardNames = player2HeroCardNames;
    }

    public ArrayList<HeroCardName> getPlayer1HeroCardNames() {
        return player1HeroCardNames;
    }

    public ArrayList<HeroCardName> getPlayer2HeroCardNames() {
        return player2HeroCardNames;
    }
}
