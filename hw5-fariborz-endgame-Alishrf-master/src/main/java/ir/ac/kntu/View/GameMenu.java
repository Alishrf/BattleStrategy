package ir.ac.kntu.View;

import ir.ac.kntu.Controller.GameController;
import ir.ac.kntu.Model.Map;
import ir.ac.kntu.Model.MapCreator;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


import java.io.File;
import java.io.FileInputStream;

public class GameMenu {
    public static void makeMenu(Pane pane , Scene scene){
        try {
            String path = "F:/APprojects/java_assignments/hw5-fariborz-endgame-Alishrf/07 Zen Garden.mp3";
            javafx.scene.media.Media media = new Media(new File(path).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            //MediaView mediaView = new MediaView(mediaPlayer);
            //get Background_image
            FileInputStream inputStream =
                    new FileInputStream("2743.jpg");
            Image image = new Image(inputStream,1000,880,false,true);
            ImageView imageView = new ImageView(image);
            //add image to pane
            pane.getChildren().add(imageView);
            //add choices
            Label start_game = new Label("START GAME");
            Label setName = new Label("Set Names");
            Label exit = new Label("Exit");

            //add To vBox
            VBox vBox = new VBox(20,start_game,setName,exit);
            //adjust style v_box
            vBox.setAlignment(Pos.CENTER);
            vBox.setLayoutX(400);
            vBox.setLayoutY(370);
            //adjust style Child of v_box
            for (int i = 0; i < vBox.getChildren().size(); i++) {
                Label l = (Label)vBox.getChildren().get(i);
                l.setFont(Font.font("Lucida Bright", FontWeight.BOLD,30));
                l.setTextFill(Color.rgb(212,175,75,1));
            }
            for (int i = 0; i < vBox.getChildren().size(); i++) {
                Label l = (Label)vBox.getChildren().get(i);
                l.setOnMouseEntered(event -> {
                    scene.setCursor(Cursor.HAND);
                    l.setTextFill(Color.rgb(212,175,75,0.5));
                });
                l.setOnMouseExited(event -> {
                    scene.setCursor(Cursor.DEFAULT);
                    l.setTextFill(Color.rgb(212, 175, 75, 1));

                });
            }
            exit.setOnMouseClicked(event -> Platform.exit());
            start_game.setOnMouseClicked(event -> {
                Pane PickPane=new Pane();
                Program.pickHeroMenu(PickPane,scene,mediaPlayer);
                scene.setRoot(PickPane);
            });
            setName.setOnMouseClicked(event ->
            {
                Pane setNamePane=new Pane();
                setNameLayout(pane,setNamePane,scene);
                scene.setRoot(setNamePane);

            });
            pane.getChildren().add(vBox);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    private static void setNameLayout(Pane mapPane,Pane setNamePane,Scene scene){
        try {
            //get Background_image
            FileInputStream inputStream =
                    new FileInputStream("2743.jpg");
            Image image = new Image(inputStream,1000,880,false,true);
            ImageView imageView = new ImageView(image);
            //add image to pane
            setNamePane.getChildren().add(imageView);
            //add Nodes
            TextField name1 =new TextField(GameController.getBattleField().getPlayer1Name());
            TextField name2 = new TextField(GameController.getBattleField().getPlayer2Name());
            Label  lplayer1= new Label("Enter Player One Name :");
            Label  lplayer2= new Label("Enter Player Two Name :");
            Button button = new Button("Save");
            Button button1 = new Button("Back");
            //adjust style
            button.setStyle("-fx-background-color: #658823; ");
            button1.setStyle("-fx-background-color: #248e86; ");
            button.setMinWidth(100);
            button.setMinHeight(40);
            button1.setMinWidth(100);
            button1.setMinHeight(40);
            button.setFont(Font.font("Lucida Bright", FontWeight.BOLD,18));
            button1.setFont(Font.font("Lucida Bright", FontWeight.BOLD,18));
            lplayer1.setFont(Font.font("Lucida Bright", FontWeight.BOLD,30));
            lplayer1.setTextFill(Color.rgb(212,175,75,1));
            lplayer2.setFont(Font.font("Lucida Bright", FontWeight.BOLD,30));
            lplayer2.setTextFill(Color.rgb(212,175,75,1));
            name1.setFont(Font.font("Lucida Bright", FontWeight.BOLD,20));
            name2.setFont(Font.font("Lucida Bright", FontWeight.BOLD,20));
            VBox player1 = new VBox(12,lplayer1,name1);
            VBox player2 = new VBox(12,lplayer2,name2);
            HBox hBox =new HBox(250,button,button1);
            VBox vBox=new VBox(40,player1,player2,hBox);
            vBox.setLayoutX(300);
            vBox.setLayoutY(270);
            vBox.setAlignment(Pos.CENTER);
            setNamePane.getChildren().add(vBox);
            //adjust buttons
            button.setOnMouseEntered(event -> {
                scene.setCursor(Cursor.HAND);
                button.setStyle("-fx-background-color: #506f1f; ");
            });
            button1.setOnMouseEntered(event -> {
                scene.setCursor(Cursor.HAND);
                button1.setStyle("-fx-background-color: #1b655d; ");
            });
            button.setOnMouseExited(event -> {
                scene.setCursor(Cursor.DEFAULT);
                button.setStyle("-fx-background-color: #658823; ");
            });
            button1.setOnMouseExited(event -> {
                scene.setCursor(Cursor.DEFAULT);
                button1.setStyle("-fx-background-color: #248e86; ");
            });
            button.setOnAction(event -> {
                GameController.getBattleField().setPlayer1Name(name1.getText());
                GameController.getBattleField().setPlayer2Name(name2.getText());
            });
            button1.setOnAction(event -> {
                scene.setRoot(mapPane);
            });




        }catch (Exception e){
            System.out.println(e.getMessage());
        }



    }
}
