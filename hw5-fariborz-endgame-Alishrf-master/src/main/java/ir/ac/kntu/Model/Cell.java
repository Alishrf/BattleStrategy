package ir.ac.kntu.Model;

import java.io.Serializable;

public class Cell implements Serializable {
    private int x;
    private int y;
    private CellType cellType;
    private boolean isTeam1;
    private boolean isTeam2;

    public Cell(int x, int y, CellType cellType,
                boolean isTeam1, boolean isTeam2) {
        this.x = x;
        this.y = y;
        this.cellType = cellType;
        this.isTeam1 = isTeam1;
        this.isTeam2 = isTeam2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public boolean isTeam1(){
        return isTeam1;
    }
    public boolean isTeam2(){
        return isTeam2;
    }
    public boolean isRespawnZone(){

        return (cellType == CellType.RESPAWNZONE);
    }
    public boolean isTowerZone(){

        return (cellType == CellType.TOWERZONE);
    }
    public boolean isCampZone(){

        return (cellType == CellType.CAMPZONE);
    }
    public boolean isNormalCell(){

        return (cellType == CellType.NORMALCELL);
    }
    public boolean isRouteCell(){
        return (cellType == CellType.ROUTECELL || cellType == CellType.RESPAWNZONE);
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

}
