package ir.ac.kntu.Controller;

import ir.ac.kntu.Model.BattleField;
import ir.ac.kntu.Model.Map;
import ir.ac.kntu.Model.MapCreator;
import ir.ac.kntu.RandomHelper;
import ir.ac.kntu.View.Program;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameController {
    private static int loopCounter=0;
    private static Map map= MapCreator.loadMap();
    private static BattleField battleField=new BattleField(map);

    public static BattleField getBattleField() {
        return battleField;
    }

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            javafx.application.Application.launch(Program.class);
        });
        executorService.shutdown();
    }
    public static boolean startGame(){
        loopCounter++;
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            battleField.shootTowers();
        });
        executorService.execute(() -> {
            battleField.deleteDeadBodys();
        });
        try {
            Thread.sleep(50);
        }catch (Exception e){
            e.printStackTrace();
        }

        executorService.execute(() -> {
           battleField.AttackHeroes();
           battleField.deleteDeadBodys();
           battleField.moveHeroes();
           if(loopCounter %5 ==0){
               battleField.getHeroCamp1();
           }
            if(loopCounter %5==1) {
                battleField.getHeroCamp2();
            }
        });



        executorService.execute(() -> {
            battleField.addMana();
        });
        boolean isEndGame=battleField.checkEndGame();
        battleField.deleteDeadBodys();
        executorService.shutdown();
        return isEndGame;
    }

}
