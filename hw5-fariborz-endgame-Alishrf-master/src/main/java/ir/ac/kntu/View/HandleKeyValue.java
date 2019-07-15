package ir.ac.kntu.View;

import ir.ac.kntu.Controller.GameController;
import ir.ac.kntu.Model.Cell;
import ir.ac.kntu.Model.HeroCardName;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public class HandleKeyValue {
    private static KeyCode keyCode1;
    private static KeyCode keyCode2;
    private static boolean isKey1=true;
    public static void addKey(KeyCode k){
        if(isKey1){
            keyCode1=k;
            isKey1=false;
        }else {
            keyCode2=k;
            isKey1=true;
            handleKeys(keyCode1,keyCode2);
        }
    }
    public static void handleKeys(KeyCode keyCode1,KeyCode keyCode2){
        Cell team1Respawn1=GameController.getBattleField().getMap().getCell(19,5);
        Cell team1Respawn2=GameController.getBattleField().getMap().getCell(19,9);
        Cell team1Respawn3=GameController.getBattleField().getMap().getCell(19,13);
        Cell team2Respawn1=GameController.getBattleField().getMap().getCell(0,5);
        Cell team2Respawn2=GameController.getBattleField().getMap().getCell(0,9);
        Cell team2Respawn3=GameController.getBattleField().getMap().getCell(0,13);
        ArrayList<HeroCardName> heroCardNamesTeam1=GameController.getBattleField().getMap().getPlayer1HeroCardNames();
        ArrayList<HeroCardName> heroCardNamesTeam2=GameController.getBattleField().getMap().getPlayer2HeroCardNames();

        switch (keyCode1) {
            case C:
                switch (keyCode2) {
                    case A:
                        GameController.getBattleField().addTeam1hero(heroCardNamesTeam1.get(0), team1Respawn1);
                        break;
                    case S:
                        GameController.getBattleField().addTeam1hero(heroCardNamesTeam1.get(0), team1Respawn2);
                        break;
                    case D:
                        GameController.getBattleField().addTeam1hero(heroCardNamesTeam1.get(0), team1Respawn3);
                        break;
                }
                break;
            case V:
                switch (keyCode2) {
                    case A:
                        GameController.getBattleField().addTeam1hero(heroCardNamesTeam1.get(1), team1Respawn1);
                        break;
                    case S:
                        GameController.getBattleField().addTeam1hero(heroCardNamesTeam1.get(1), team1Respawn2);
                        break;
                    case D:
                        GameController.getBattleField().addTeam1hero(heroCardNamesTeam1.get(1), team1Respawn3);
                        break;
                }
                break;
            case B:
                switch (keyCode2) {
                    case A:
                        GameController.getBattleField().addTeam1hero(heroCardNamesTeam1.get(2), team1Respawn1);
                        break;
                    case S:
                        GameController.getBattleField().addTeam1hero(heroCardNamesTeam1.get(2), team1Respawn2);
                        break;
                    case D:
                        GameController.getBattleField().addTeam1hero(heroCardNamesTeam1.get(2), team1Respawn3);
                        break;
                }
                break;
            case N:
                switch (keyCode2) {
                    case A:
                        GameController.getBattleField().addTeam1hero(heroCardNamesTeam1.get(3), team1Respawn1);
                        break;
                    case S:
                        GameController.getBattleField().addTeam1hero(heroCardNamesTeam1.get(3), team1Respawn2);
                        break;
                    case D:
                        GameController.getBattleField().addTeam1hero(heroCardNamesTeam1.get(3), team1Respawn3);
                        break;
                }
                break;
            case NUMPAD1:
                switch (keyCode2) {
                    case J:
                        GameController.getBattleField().addTeam2hero(heroCardNamesTeam2.get(0), team2Respawn1);
                        break;
                    case K:
                        GameController.getBattleField().addTeam2hero(heroCardNamesTeam2.get(0), team2Respawn2);
                        break;
                    case L:
                        GameController.getBattleField().addTeam2hero(heroCardNamesTeam2.get(0), team2Respawn3);
                        break;
                }
                break;
            case NUMPAD2:
                switch (keyCode2) {
                    case J:
                        GameController.getBattleField().addTeam2hero(heroCardNamesTeam2.get(1), team2Respawn1);
                        break;
                    case K:
                        GameController.getBattleField().addTeam2hero(heroCardNamesTeam2.get(1), team2Respawn2);
                        break;
                    case L:
                        GameController.getBattleField().addTeam2hero(heroCardNamesTeam2.get(1), team2Respawn3);
                        break;
                }
            case NUMPAD3:
                switch (keyCode2) {
                    case J:
                        GameController.getBattleField().addTeam2hero(heroCardNamesTeam2.get(2), team2Respawn1);
                        break;
                    case K:
                        GameController.getBattleField().addTeam2hero(heroCardNamesTeam2.get(2), team2Respawn2);
                        break;
                    case L:
                        GameController.getBattleField().addTeam2hero(heroCardNamesTeam2.get(2), team2Respawn3);
                        break;
                }
            case NUMPAD5:
                switch (keyCode2) {
                    case J:
                        GameController.getBattleField().addTeam2hero(heroCardNamesTeam2.get(3), team2Respawn1);
                        break;
                    case K:
                        GameController.getBattleField().addTeam2hero(heroCardNamesTeam2.get(3), team2Respawn2);
                        break;
                    case L:
                        GameController.getBattleField().addTeam2hero(heroCardNamesTeam2.get(3), team2Respawn3);
                        break;
                }
            case Q:
                if(GameController.getBattleField().getMap().getTeam1Camp().size()<1) {
                    Cell cell = GameController.getBattleField().getMap().getCell(14, 9);
                    GameController.getBattleField().addTeam1Camp(cell);
                }
                break;
            case NUMPAD8:
                if(GameController.getBattleField().getMap().getTeam2Camp().size()<1) {
                    Cell cell = GameController.getBattleField().getMap().getCell(5, 9);
                    GameController.getBattleField().addTeam2Camp(cell);
                }
                break;
        }

    }
}