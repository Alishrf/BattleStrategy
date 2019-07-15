package ir.ac.kntu.Model;

import java.io.*;

public class MapCreator {

    public static void main(String[] args){
        creatAndSaveMap();
    }


    public static void creatAndSaveMap(){
        //normal -> 0
        //respawn -> 1
        // route -> 2
        //tower -> 3
        //camp   -> 4

      int[][] digitMap={{0,0,0,0,3,1,3,0,3,1,3,0,0,1,3,0,0,0,0,0},
              {3,3,3,3,3,2,3,0,3,2,3,0,0,2,3,3,3,3,3,0},
              {3,2,2,2,2,2,3,0,3,2,3,0,0,2,2,2,2,2,3,0},
              {3,2,3,3,3,3,3,3,3,2,3,3,3,3,3,3,3,2,3,0},
              {3,2,3,0,3,2,2,2,2,2,2,2,2,2,3,0,3,2,3,0},
              {3,2,3,0,3,2,3,3,3,4,3,3,3,2,3,0,3,2,3,0},
              {3,2,3,0,3,2,3,0,0,0,0,0,3,2,3,0,3,2,3,0},
              {3,2,3,0,3,2,3,0,0,0,0,0,3,2,3,0,3,2,3,0},
              {0,2,0,0,0,2,0,0,0,0,0,0,0,2,0,0,0,2,0,0},
              {0,2,0,0,0,2,0,0,0,0,0,0,0,2,0,0,0,2,0,0},
              {0,2,0,0,0,2,0,0,0,0,0,0,0,2,0,0,0,2,0,0},
              {0,2,0,0,0,2,0,0,0,0,0,0,0,2,0,0,0,2,0,0},
              {3,2,3,0,3,2,3,0,0,0,0,0,3,2,3,0,3,2,3,0},
              {3,2,3,0,3,2,3,0,0,0,0,0,3,2,3,0,3,2,3,0},
              {3,2,3,0,3,2,3,3,3,4,3,3,3,2,3,0,3,2,3,0},
              {3,2,3,0,3,2,2,2,2,2,2,2,2,2,3,0,3,2,3,0},
              {3,2,3,3,3,3,3,3,3,2,3,3,3,3,3,3,3,2,3,0},
              {3,2,2,2,2,2,3,0,3,2,3,0,3,2,2,2,2,2,3,0},
              {3,3,3,3,3,2,3,0,3,2,3,0,3,2,3,3,3,3,3,0},
              {0,0,0,0,3,1,3,0,3,1,3,0,3,1,3,0,0,0,0,0}};
    Cell[][] cells= new Cell[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if(digitMap[i][j]==0){
                    cells[i][j] = new Cell(i,j,CellType.NORMALCELL,!(i<10),(i<10));
                }else if(digitMap[i][j]==1){
                    cells[i][j] = new Cell(i,j,CellType.RESPAWNZONE,!(i<10),(i<10));
                }else if(digitMap[i][j]==2){
                    cells[i][j] = new Cell(i,j,CellType.ROUTECELL,!(i<10),(i<10));
                }else if(digitMap[i][j]==3){
                    cells[i][j] = new Cell(i,j,CellType.TOWERZONE,!(i<10),(i<10));
                }else if(digitMap[i][j]==4){
                    cells[i][j] = new Cell(i,j,CellType.CAMPZONE,!(i<10),(i<10));
                }
            }

        }
        Map map=new Map(cells);
        File f = new File("Map.info");
        try {
            FileOutputStream fileOutputStream =new FileOutputStream(f);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(map);

        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public static Map loadMap(){
        try {
            FileInputStream fileInputStream=new FileInputStream(new File("Map.info"));
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
            Map map = (Map) objectInputStream.readObject();
            return map;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
