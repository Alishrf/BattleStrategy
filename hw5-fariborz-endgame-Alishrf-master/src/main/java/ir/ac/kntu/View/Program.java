package ir.ac.kntu.View;

import ir.ac.kntu.Controller.GameController;
import ir.ac.kntu.Model.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Program extends Application {

    @Override
    public void start(Stage primaryStage)  {
        try {
            FileInputStream inputStream =
                    new FileInputStream("images/logo.jpg");
            Pane mainRoot = new Pane();
            Scene scene = new Scene(mainRoot, 988, 868);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Battle Strategy");
            primaryStage.setResizable(false);
            primaryStage.getIcons().add(new Image(inputStream));
            primaryStage.show();
            GameMenu.makeMenu(mainRoot, scene);
        }catch (Exception e){
            e.printStackTrace();
        }





    }

    public static void startGame(Scene scene){
        try {
            String path = "F:/APprojects/java_assignments/hw5-fariborz-endgame-Alishrf/Gerard Marino - God Of War III Overture [www.vmusic.ir].mp3";
            javafx.scene.media.Media media = new Media(new File(path).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            Pane pane = new Pane();
            scene.setRoot(pane);
            FileInputStream inputStream =
                    new FileInputStream("Game Background-14.png");
            Image image = new Image(inputStream, 1000, 880, false, true);
            ImageView imageView = new ImageView(image);
            pane.getChildren().addAll(imageView);
            Rectangle rectangle = new Rectangle(0, 0, 1000, 880);
            rectangle.setStroke(Color.BLACK);
            rectangle.setFill(Color.rgb(0, 0, 0, 0.9));
            pane.getChildren().add(rectangle);
            Map map = MapCreator.loadMap();
            drawMap2(pane, map);
            VBox vBox1 = new VBox(20);
            String[] hero1 = {"C", "V", "B", "N"};
            showHeroes(pane, hero1, vBox1, GameController.getBattleField().getMap().getPlayer1HeroCardNames(), 470);
            vBox1.setAlignment(Pos.CENTER);
            vBox1.relocate(840, 470);
            pane.getChildren().add(vBox1);
            VBox vBox2 = new VBox(20);
            String[] hero2 = {"1", "2", "3", "5"};
            showHeroes(pane, hero2, vBox2, GameController.getBattleField().getMap().getPlayer2HeroCardNames(), 40);
            vBox2.relocate(840, 40);
            pane.getChildren().add(vBox2);
            FileInputStream inputStream2 =
                    new FileInputStream("images/mana.jpg");
            Image image2 = new Image(inputStream2, 30, 30, false, true);
            ImageView mana1 = new ImageView(image2);
            Label mana1Lable = new Label(String.valueOf(GameController.getBattleField().getPlayer1Mana()));
            mana1Lable.setFont(Font.font("Lucida Bright", FontWeight.BOLD, 25));
            mana1Lable.setTextFill(Color.rgb(0, 255, 255));
            HBox hBox = new HBox(20, mana1, mana1Lable);
            hBox.setAlignment(Pos.CENTER);
            hBox.relocate(650, 850);
            Label mana2Lable = new Label(String.valueOf(GameController.getBattleField().getPlayer2Mana()));
            mana2Lable.setFont(Font.font("Lucida Bright", FontWeight.BOLD, 25));
            mana2Lable.setTextFill(Color.rgb(255, 0, 255));
            ImageView mana2 = new ImageView(image2);
            HBox hBox2 = new HBox(20, mana2, mana2Lable);
            hBox2.relocate(650, 10);
            pane.getChildren().add(hBox);
            pane.getChildren().add(hBox2);
            Label health2 = new Label(String.valueOf(GameController.getBattleField().getPlayer2Health()));
            health2.setTextFill(Color.rgb(203, 195, 36));
            health2.relocate(10, 10);
            health2.setFont(Font.font("Lucida Bright", FontWeight.BOLD, 25));
            Label health1 = new Label(String.valueOf(GameController.getBattleField().getPlayer2Health()));
            health1.setTextFill(Color.rgb(203, 195, 36));
            health1.relocate(10, 850);
            health1.setFont(Font.font("Lucida Bright", FontWeight.BOLD, 25));
            pane.getChildren().addAll(health1, health2);
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(() -> {
                while (true) {
                    try {
                        if (!GameController.startGame()) {
                            mediaPlayer.stop();
                            String path1 = "F:/APprojects/java_assignments/hw5-fariborz-endgame-Alishrf/mp3_87046.mp3";
                            Media media1 = new Media(new File(path1).toURI().toString());
                            MediaPlayer mediaPlayer1 = new MediaPlayer(media1);
                            mediaPlayer1.setAutoPlay(true);
                            executorService.shutdownNow();
                            Rectangle rectangle1 = new Rectangle(0, 0, 1000, 880);
                            rectangle1.setFill(Color.rgb(0, 0, 0));
                            Platform.runLater(() -> {
                                pane.getChildren().addAll(rectangle1);
                            });
                            Label label;
                            int score = 0;
                            if (GameController.getBattleField().getPlayer1Health() > 0) {
                                score = (int) GameController.getBattleField().getPlayer1Health();
                                label = new Label(GameController.getBattleField().getPlayer1Name() + " Wins The Game");
                            } else {
                                score = (int) GameController.getBattleField().getPlayer2Health();
                                label = new Label(GameController.getBattleField().getPlayer2Name() + " Wins The Game");
                            }
                            label.setFont(Font.font("Lucida Bright", FontWeight.BOLD, 70));
                            label.setTextFill(Color.rgb(197, 172, 42));
                            label.relocate(90, 100);
                            Platform.runLater(() -> {
                                pane.getChildren().addAll(label);
                            });
                            HBox hBox1 = new HBox(20);
                            FileInputStream inputStream4 =
                                    new FileInputStream("images/winner.png");
                            Image image4 = new Image(inputStream4, 250, 250, false, true);
                            ImageView imageView4 = new ImageView(image4);
                            imageView4.relocate(380, 230);
                            Platform.runLater(() -> {
                                pane.getChildren().addAll(imageView4);
                            });
                            for (int i = 0; i < score; i++) {
                                FileInputStream inputStream3 =
                                        new FileInputStream("images/star.png");
                                Image image3 = new Image(inputStream3, 250, 250, false, true);
                                ImageView imageView3 = new ImageView(image3);
                                hBox1.getChildren().addAll(imageView3);
                            }
                            hBox1.relocate(150, 500);
                            Platform.runLater(() -> {
                                pane.getChildren().addAll(hBox1);
                            });
                            executorService.shutdownNow();
                            break;
                        }
                        ArrayList<ImageView> imageShows = new ArrayList<>();
                        ArrayList<Rectangle> rectangleShows = new ArrayList<>();
                        if(GameController.getBattleField().getMap().getTeam1Camp().size()==1) {
                            FileInputStream inputStream3 =
                                    new FileInputStream("images/camp.jpg");
                            Image image3 = new Image(inputStream3, 40, 40, false, true);
                            ImageView imageView3 = new ImageView(image3);
                            imageView3.relocate(9 *40, 15*40);
                            Rectangle rectangle1=new Rectangle(9*40,15*40,40,40);
                            rectangle1.setStroke(Color.rgb(255,0,255));
                            rectangle1.setFill(Color.TRANSPARENT);
                            rectangleShows.add(rectangle1);
                            imageShows.add(imageView3);
                            Platform.runLater(() -> {
                                pane.getChildren().addAll(rectangle1,imageView3);
                            });
                        }
                        if(GameController.getBattleField().getMap().getTeam2Camp().size()==1) {
                            FileInputStream inputStream3 =
                                    new FileInputStream("images/camp.jpg");
                            Image image3 = new Image(inputStream3, 40, 40, false, true);
                            ImageView imageView3 = new ImageView(image3);
                            imageView3.relocate(9*40, 6*40);
                            imageShows.add(imageView3);
                            Rectangle rectangle1=new Rectangle(9*40,6*40,41,41);
                            rectangle1.setStroke(Color.rgb(0,255,255));
                            rectangle1.setFill(Color.TRANSPARENT);
                            rectangleShows.add(rectangle1);
                            Platform.runLater(() -> {
                                pane.getChildren().addAll(rectangle1,imageView3);
                            });

                        }



                        for (TowerCard t : GameController.getBattleField().getMap().getTeam1TowerCards()) {
                            FileInputStream inputStream1 =
                                    new FileInputStream("images/" + t.getTowerName() + ".jpg");
                            Image image1 = new Image(inputStream1, 40, 40, false, true);
                            ImageView imageView1 = new ImageView(image1);
                            Rectangle rectangle1 = new Rectangle(t.getCurrentY()*40, (t.getCurrentX()+1)*40, 41, 41);
                            rectangle1.setStroke(Color.rgb(0, 255, 255));
                            rectangle1.setFill(Color.rgb(0, 255, 255));
                            imageView1.setX(t.getCurrentY()*40);
                            imageView1.setY((t.getCurrentX()+1)*40);
                            imageShows.add(imageView1);
                            rectangleShows.add(rectangle1);
                            Platform.runLater(() -> {
                                pane.getChildren().addAll(rectangle1,imageView1 );
                            });
                        }
                        for (TowerCard t : GameController.getBattleField().getMap().getTeam2TowerCards()) {
                            FileInputStream inputStream1 =
                                    new FileInputStream("images/" + t.getTowerName() + ".jpg");
                            Image image1 = new Image(inputStream1, 40, 40, false, true);
                            ImageView imageView1 = new ImageView(image1);
                            Rectangle rectangle1 = new Rectangle(t.getCurrentY()*40, (t.getCurrentX()+1)*40, 41, 41);
                            rectangle1.setStroke(Color.rgb(150, 0, 150));
                            rectangle1.setFill(Color.rgb(150, 0, 150));
                            imageView1.setX(t.getCurrentY()*40);
                            imageView1.setY((t.getCurrentX()+1)*40);
                            imageShows.add(imageView1);
                            rectangleShows.add(rectangle1);
                            Platform.runLater(() -> {
                                pane.getChildren().addAll(rectangle1, imageView1);
                            });

                        }
                        for (HeroCard h : GameController.getBattleField().getMap().getTeam1AliveHeroCards()) {
                            FileInputStream inputStream1 =
                                    new FileInputStream("images/" + h.getHeroCardName() + "logo.jpg");
                            Image image1 = new Image(inputStream1, 40, 40, false, true);
                            ImageView imageView1 = new ImageView(image1);
                            Rectangle rectangle1 = new Rectangle(h.getCurrentY()*40, (h.getCurrentX()+1)*40, 41, 41);
                            rectangle1.setStroke(Color.rgb(0, 254, 254));
                            rectangle1.setFill(Color.rgb(0, 254, 254));
                            System.out.println(h.getCurrentY() + "  %%%%%"  + h.getCurrentX());
                            imageView1.relocate(h.getCurrentY()*40,(h.getCurrentX()+1)*40 );
                            imageShows.add(imageView1);
                            rectangleShows.add(rectangle1);
                            Platform.runLater(() -> {
                                pane.getChildren().addAll(rectangle1, imageView1);
                            });

                        }
                        for (HeroCard h : GameController.getBattleField().getMap().getTeam2AliveHeroCards()) {
                            FileInputStream inputStream1 =
                                    new FileInputStream("images/" + h.getHeroCardName() + "logo.jpg");
                            Image image1 = new Image(inputStream1, 40, 40, false, true);
                            ImageView imageView1 = new ImageView(image1);
                            Rectangle rectangle1 = new Rectangle(h.getCurrentY()*40, (h.getCurrentX()+1)*40, 41, 41);
                            rectangle1.setStroke(Color.rgb(160, 0, 160));
                            rectangle1.setFill(Color.rgb(160, 0, 160));
                            imageView1.relocate(h.getCurrentY()*40, (h.getCurrentX()+1)*40);
                            imageShows.add(imageView1);
                            rectangleShows.add(rectangle1);
                            Platform.runLater(() -> {
                                pane.getChildren().addAll(rectangle1, imageView1);
                            });
                        }
                        Platform.runLater(() -> {
                            health1.setText(String.valueOf(GameController.getBattleField().getPlayer1Health()));
                            health2.setText(String.valueOf(GameController.getBattleField().getPlayer2Health()));
                            mana1Lable.setText(String.valueOf(GameController.getBattleField().getPlayer1Mana()));
                            mana2Lable.setText(String.valueOf(GameController.getBattleField().getPlayer2Mana()));
                        });
                        try {
                            Thread.sleep(1000);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        for (ImageView i:imageShows){
                            Platform.runLater(() -> {
                                pane.getChildren().remove(i);
                                pane.getChildren().remove(rectangleShows.get(imageShows.indexOf(i)));
                            });

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });

            scene.setOnKeyPressed(event -> {
                System.out.println(event.getCode());
                KeyCode k=event.getCode();
                HandleKeyValue.addKey(k);
            });



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void  showHeroes(Pane pane,String[] keys,VBox vBox1,ArrayList<HeroCardName> names,int y)throws Exception{
        int index1 =0;
        for(HeroCardName h:names){
            Rectangle rectangle1=new Rectangle(840,y+index1*100,90,90);
            rectangle1.setFill(Color.BLACK);
            rectangle1.setStroke(Color.BLACK);
            pane.getChildren().add(rectangle1);
            FileInputStream inputStream1 =
                    new FileInputStream("images/"+h+"logo.jpg");
            Image image1 = new Image(inputStream1, 80, 80, false, true);
            ImageView imageView1 = new ImageView(image1);
            Label label=new Label(keys[index1]);
            index1 ++;
            label.setFont(Font.font("Lucida Bright", FontWeight.BOLD,30));
            label.setTextFill(Color.WHITE);
            HBox hBox=new HBox(20,imageView1,label);
            hBox.setAlignment(Pos.CENTER);
            vBox1.getChildren().add(hBox);
        }
    }
    public static void pickTower(Pane pane, Scene scene,MediaPlayer mediaPlayer){
        try {
            scene.setRoot(pane);
            Map map=MapCreator.loadMap();
            FileInputStream inputStream =
                    new FileInputStream("Game Background-14.png");
            Image image = new Image(inputStream, 1000, 880, false, true);
            ImageView imageView = new ImageView(image);
            pane.getChildren().addAll(imageView);
            drawMap(pane,map);
            Rectangle rectangle =new Rectangle(800,0,200,900);
            rectangle.setStroke(Color.BLACK);
            rectangle.setFill(Color.rgb(0,0,0,0.8));
            pane.getChildren().add(rectangle);

            ArrayList<ImageView> imageViews=new ArrayList<>();
            for (int i = 0; i < TowerName.values().length - 1; i++) {
                FileInputStream inputStream1 =
                        new FileInputStream("images/"+TowerName.values()[i]+".jpg");
                Image image1 = new Image(inputStream1, 170, 170, false, true);
                ImageView imageView1 = new ImageView(image1);
                imageViews.add(imageView1);
                imageView1.relocate(810,450+i*190);
                pane.getChildren().add(imageView1);
            }

            for (ImageView i:imageViews) {
                i.setOnMouseClicked(event -> {
                    TowerName t= (event.getSceneY()<630)? TowerName.BLACK:TowerName.ELECTRIC;
                    pane.setOnMouseClicked(event1 -> {
                        if(event1.getSceneX()>=0 &&event1.getSceneX()<=800 &&
                                event1.getSceneY()>=40 &&event1.getSceneY()<=840)
                        try {
                            int xIndex = (int)event1.getSceneX();
                            int yIndex = (int)event1.getSceneY();
                            xIndex/=40;
                            yIndex/=40;
                            yIndex --;
                            System.out.println("x " + xIndex + " y " + yIndex + " ");
                            if(xIndex <20 && xIndex>=0 && yIndex>=10 && yIndex <20) {
                                Cell cell = GameController.getBattleField().getMap().getCell(yIndex, xIndex);
                                if (GameController.getBattleField().addTeam1Tower(t, cell)) {
                                    FileInputStream inputStream1 =
                                            new FileInputStream("images/" + t + ".jpg");
                                    Image image1 = new Image(inputStream1, 40, 40, false, true);
                                    ImageView imageView1 = new ImageView(image1);
                                    imageView1.relocate((xIndex) * 40, (yIndex+1) * 40);
                                    pane.getChildren().add(imageView1);
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    });


                });
            }
            Button ready=new Button("Ready");
            ready.setStyle("-fx-background-color: #658823; ");
            ready.relocate(810,40);
            ready.setMinHeight(400);
            ready.setMinWidth(170);
            ready.setFont(Font.font("Lucida Bright", FontWeight.BOLD,20));
            pane.getChildren().add(ready);
            ready.setOnMouseClicked(event -> {
                for (ImageView i : imageViews) {
                    i.setY(i.getY() - 450);
                }
                pane.getChildren().remove(ready);
                Button ready1 = new Button("Ready");
                ready1.setStyle("-fx-background-color: #658823; ");
                ready1.relocate(810, 450);
                ready1.setMinHeight(400);
                ready1.setMinWidth(170);
                ready1.setFont(Font.font("Lucida Bright", FontWeight.BOLD, 20));
                pane.getChildren().add(ready1);
                for (ImageView i : imageViews) {
                    i.setOnMouseClicked(event2 -> {
                        TowerName t = (event2.getSceneY() < 180) ? TowerName.BLACK : TowerName.ELECTRIC;
                        scene.setOnMouseClicked(event1 -> {
                            if (event1.getSceneX() >= 0 && event1.getSceneX() <= 800 &&
                                    event1.getSceneY() >= 40 && event1.getSceneY() <= 840)
                                try {
                                    int xIndex = (int) event1.getSceneX();
                                    int yIndex = (int) event1.getSceneY();
                                    xIndex /= 40;
                                    yIndex /= 40;
                                    yIndex--;
                                    System.out.println("x " + xIndex + " y " + yIndex + " ");
                                    if (xIndex < 20 && xIndex >= 0 && yIndex >= 0 && yIndex < 10) {
                                        Cell cell = GameController.getBattleField().getMap().getCell(yIndex, xIndex);
                                        if (GameController.getBattleField().addTeam2Tower(t, cell)) {
                                            FileInputStream inputStream1 =
                                                    new FileInputStream("images/" + t + ".jpg");
                                            Image image1 = new Image(inputStream1, 40, 40, false, true);
                                            ImageView imageView1 = new ImageView(image1);
                                            imageView1.relocate((xIndex) * 40, (yIndex + 1) * 40);
                                            pane.getChildren().add(imageView1);
                                        }
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                        });


                    });
                }
                ready1.setOnMouseClicked(event1 -> {
                    mediaPlayer.stop();
                    startGame(scene);
                });
            });
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void pickHeroMenu(Pane pane, Scene scene, MediaPlayer mediaPlayer){
        try {
            FileInputStream inputStream =
                    new FileInputStream("Game Background-14.png");
            Image image = new Image(inputStream, 1000, 880, false, true);
            ImageView imageView = new ImageView(image);
            //add image to pane
            pane.getChildren().add(imageView);
            Map map = MapCreator.loadMap();
            drawMap(pane, map);
            Rectangle rectangle1 = new Rectangle(0, 0, 1000, 880);
            rectangle1.setStroke(Color.BLACK);
            rectangle1.setFill(Color.rgb(0, 0, 0, 0.9));
            pane.getChildren().addAll(rectangle1);
            ArrayList<Rectangle> rectangles = new ArrayList<>();
            drawPikcs(pane,rectangles);
            for (Rectangle r : rectangles) {
                r.setOnMouseEntered(event -> {
                    scene.setCursor(Cursor.HAND);
                    r.setStroke(Color.RED);
                    r.setFill(Color.rgb(0, 0, 0, 0.5));
                });
                r.setOnMouseExited(event -> {
                    scene.setCursor(Cursor.DEFAULT);
                    r.setStroke(Color.BLACK);
                    r.setFill(Color.TRANSPARENT);
                });
            }
            HBox hBox = new HBox(25);
            ArrayList<ImageView> imageViewsChoices = new ArrayList<>();
            drawChoosen(hBox,imageViewsChoices);
            hBox.setLayoutX(100);
            hBox.setLayoutY(550);
            pane.getChildren().addAll(hBox);
            ArrayList<HeroCardName> heroCardChoices = new ArrayList<>();

            for(Rectangle r:rectangles){
                r.setOnMouseClicked(event -> {
                    int index=(int)(r.getX()/r.getWidth());
                    System.out.println(HeroCardName.values()[index]);
                    if(heroCardChoices.size()<4) {
                        heroCardChoices.add(HeroCardName.values()[index]);

                        try {

                            FileInputStream inputStream2 =
                                    new FileInputStream("images/" + HeroCardName.values()[index] + "logo.jpg");
                            Image image2 = new Image(inputStream2, 200, 200, false, true);
                            imageViewsChoices.get(heroCardChoices.size() - 1).setImage(image2);

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                });

            }
            Label pickPlayer1=new Label(GameController.getBattleField().getPlayer1Name() + " Pick Your Heroes");
            Button ready=new Button("Ready");
            HBox footer=new HBox(70,pickPlayer1,ready);
            pickPlayer1.setFont(Font.font("Lucida Bright", FontWeight.BOLD,25));
            pickPlayer1.setTextFill(Color.rgb(212,175,75));
            ready.setStyle("-fx-background-color: #658823; ");
            ready.setFont(Font.font("Lucida Bright", FontWeight.BOLD,20));
            ready.setMinWidth(500);
            ready.setMinHeight(60);
            ready.setTextFill(Color.WHITE);
            footer.setAlignment(Pos.CENTER);
            footer.setLayoutX(20);
            footer.setLayoutY(800);
            pane.getChildren().addAll(footer);
            ready.setOnMouseEntered(event -> {
                scene.setCursor(Cursor.HAND);
                ready.setStyle("-fx-background-color: #4a641a; ");

            });
            ready.setOnMouseExited(event -> {
                scene.setCursor(Cursor.DEFAULT);
                ready.setStyle("-fx-background-color: #658823; ");

            });
            Pane pane2=new Pane();
            ArrayList<Rectangle> rectanglesplayer2=new ArrayList<>();
            ArrayList<ImageView> imageViewChoices2=new ArrayList<>();
            HBox hBox2=new HBox(25);
            ready.setOnMouseClicked(event -> {
                if(heroCardChoices.size()==4){
                    GameController.getBattleField().getMap().setPlayer1HeroCardNames(heroCardChoices);
                    pane2.getChildren().addAll(imageView);
                    drawMap(pane2,map);
                    pane2.getChildren().addAll(rectangle1);
                    drawPikcs(pane2,rectanglesplayer2);
                    scene.setRoot(pane2);
                    drawChoosen(hBox2,imageViewChoices2);
                    hBox2.setLayoutX(100);
                    hBox2.setLayoutY(500);
                    pane2.getChildren().addAll(hBox2);
                    for (Rectangle r : rectanglesplayer2) {
                        r.setOnMouseEntered(event1 -> {
                            scene.setCursor(Cursor.HAND);
                            r.setStroke(Color.RED);
                            r.setFill(Color.rgb(0, 0, 0, 0.5));
                        });
                        r.setOnMouseExited(event1 -> {
                            scene.setCursor(Cursor.DEFAULT);
                            r.setStroke(Color.BLACK);
                            r.setFill(Color.TRANSPARENT);
                        });
                    }
                    ArrayList<HeroCardName> heroCardNamesPlayer2=new ArrayList<>();
                    for(Rectangle r:rectanglesplayer2) {
                        r.setOnMouseClicked(event1 -> {
                            int index = (int) (r.getX() / r.getWidth());
                            System.out.println(HeroCardName.values()[index]);
                            if (heroCardNamesPlayer2.size() < 4) {
                                heroCardNamesPlayer2.add(HeroCardName.values()[index]);
                                try {

                                    FileInputStream inputStream2 =
                                            new FileInputStream("images/" + HeroCardName.values()[index] + "logo.jpg");
                                    Image image2 = new Image(inputStream2, 200, 200, false, true);
                                    imageViewChoices2.get(heroCardNamesPlayer2.size() - 1).setImage(image2);

                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        });
                    }
                    Label pickPlayer2=new Label(GameController.getBattleField().getPlayer2Name() + " Pick Your Heroes");
                    Button ready2=new Button("Ready");
                    HBox footer2=new HBox(70,pickPlayer2,ready2);
                    pickPlayer2.setFont(Font.font("Lucida Bright", FontWeight.BOLD,25));
                    pickPlayer2.setTextFill(Color.rgb(212,175,75));
                    ready2.setStyle("-fx-background-color: #658823; ");
                    ready2.setFont(Font.font("Lucida Bright", FontWeight.BOLD,20));
                    ready2.setMinWidth(500);
                    ready2.setMinHeight(60);
                    ready2.setTextFill(Color.WHITE);
                    footer2.setAlignment(Pos.CENTER);
                    footer2.setLayoutX(20);
                    footer2.setLayoutY(800);
                    pane2.getChildren().addAll(footer2);
                    ready2.setOnMouseEntered(event1 -> {
                        scene.setCursor(Cursor.HAND);
                        ready.setStyle("-fx-background-color: #4a641a; ");

                    });
                    ready2.setOnMouseExited(event1 -> {
                        scene.setCursor(Cursor.DEFAULT);
                        ready.setStyle("-fx-background-color: #658823; ");

                    });
                    ready2.setOnMouseClicked(event1 -> {
                        if(heroCardNamesPlayer2.size()==4){
                            GameController.getBattleField().getMap().setPlayer2HeroCardNames(heroCardNamesPlayer2);
                            Pane newPane=new Pane();
                            pickTower(newPane,scene,mediaPlayer);
                        }else {
                            System.out.println("please pick your heroes");
                        }
                    });
                }else {
                    System.out.println("pick your Heroes");
                }
            });


        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    public static void drawPikcs(Pane pane,ArrayList<Rectangle> rectangles){
        try {
            HeroCardName[] heroNames = HeroCardName.values();
            for (int i = 0; i < HeroCardName.values().length - 1; i++) {
                FileInputStream inputStream1 =
                        new FileInputStream("images/" + heroNames[i] + ".jpg");
                Image image1 = new Image(inputStream1, 1000 / (HeroCardName.values().length - 1)
                        , 440, false, true);
                ImageView imageView1 = new ImageView(image1);
                Label label = new Label(heroNames[i].toString());
                label.setFont(Font.font("Lucida Bright", FontWeight.BOLD, 20));
                label.setTextFill(Color.rgb(212, 175, 75));
                Rectangle rectangle = new Rectangle(1000 / (HeroCardName.values().length - 1), 440);
                rectangle.setStroke(Color.BLACK);
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setX(i * (1000 / (HeroCardName.values().length - 1)));
                rectangle.setY(0);
                VBox vBox = new VBox(20, imageView1, label);
                vBox.setAlignment(Pos.CENTER);
                vBox.setLayoutX(i * (1000 / (HeroCardName.values().length - 1)));
                vBox.setLayoutY(0);
                pane.getChildren().addAll(vBox);
                pane.getChildren().addAll(rectangle);
                rectangles.add(rectangle);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void drawChoosen(HBox hBox,ArrayList<ImageView> imageViewsChoices){
        for (int i = 1; i <= 4; i++) {
            try {
                FileInputStream inputStream2 =
                        new FileInputStream("images/unknown.jpg");
                Image image2 = new Image(inputStream2, 200, 200, false, true);
                ImageView imageView2 = new ImageView(image2);
                imageViewsChoices.add(imageView2);
                Label label = new Label("HeroCard " + i);
                label.setFont(Font.font("Lucida Bright", FontWeight.BOLD, 20));
                VBox vBox = new VBox(15, imageView2, label);
                vBox.setAlignment(Pos.CENTER);
                hBox.getChildren().addAll(vBox);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void drawMap(Pane pane , Map map){
        for (int i = 0; i < 20; i++) {
            for (int j =0; j <20 ; j++) {
                Rectangle rectangle=new Rectangle(j*40,(i+1)*40,40,40);
                rectangle.setStroke(Color.BLACK);
                if(map.getCells()[i][j].isTeam1()){
                    rectangle.setFill(Color.rgb(125,125,125));
                }
                if(map.getCells()[i][j].isTeam2()){
                    rectangle.setFill(Color.rgb(90,90,90));
                }
                if(map.getCells()[i][j].isRouteCell()){
                    rectangle.setFill(Color.YELLOW);
                }
                if(map.getCells()[i][j].isTowerZone()){
                    rectangle.setFill(Color.BLUE);
                }
                if(map.getCells()[i][j].isCampZone()){
                    rectangle.setFill(Color.GREEN);
                }
                if(map.getCells()[i][j].isRespawnZone()){
                    rectangle.setFill(Color.RED);
                }
                pane.getChildren().add(rectangle);
            }
        }
    }

    public static void drawMap2(Pane pane , Map map){
        String[] respawn1={"A","S","D"};
        String[] respawn2={"J","K","L"};
        int respawn1I=0,respawn2I=0;
        int camp1I=0,camp2I=0;
        for (int i = 0; i < 20; i++) {
            for (int j =0; j <20 ; j++) {
                Rectangle rectangle=new Rectangle(j*40,(i+1)*40,40,40);
                rectangle.setStroke(Color.BLACK);
                if(map.getCells()[i][j].isTeam1()){
                    rectangle.setFill(Color.rgb(125,125,125));

                }
                if(map.getCells()[i][j].isTeam2()){
                    rectangle.setFill(Color.rgb(90,90,90));
                }
                if(map.getCells()[i][j].isRouteCell()){
                    rectangle.setFill(Color.YELLOW);
                }
                if(map.getCells()[i][j].isTowerZone()) {

                    rectangle.setFill(Color.BLUE);

                }
                if(map.getCells()[i][j].isCampZone()){
                    rectangle.setFill(Color.GREEN);
                }
                if(map.getCells()[i][j].isRespawnZone()){
                    rectangle.setFill(Color.RED);
                }

                pane.getChildren().add(rectangle);
                if(map.getCells()[i][j].isTeam1() && map.getCells()[i][j].isRespawnZone()){
                    Label label=new Label(respawn1[respawn1I]);
                    label.relocate(j*40 +8,(i+1)*40 +8);
                    label.setFont(Font.font("Lucida Bright", FontWeight.BOLD,30));
                    respawn1I++;
                    pane.getChildren().add(label);
                }
                if(map.getCells()[i][j].isTeam2() && map.getCells()[i][j].isRespawnZone()){
                    Label label=new Label(respawn2[respawn2I]);
                    label.relocate(j*40 +8,(i+1)*40 +8);
                    label.setFont(Font.font("Lucida Bright", FontWeight.BOLD,30));
                    respawn2I ++;
                    pane.getChildren().add(label);
                }
                if(map.getCells()[i][j].isTeam2() && map.getCells()[i][j].isCampZone()){
                    Label label=new Label("8");
                    label.relocate(j*40 +8,(i+1)*40 +8);
                    label.setFont(Font.font("Lucida Bright", FontWeight.BOLD,30));
                    pane.getChildren().add(label);
                }
                if(map.getCells()[i][j].isTeam1() && map.getCells()[i][j].isCampZone()){
                    Label label=new Label("Q");
                    label.setFont(Font.font("Lucida Bright", FontWeight.BOLD,30));
                    label.relocate(j*40 +8,(i+1)*40 +8);
                    pane.getChildren().add(label);
                }
            }
        }
        Label label=new Label(GameController.getBattleField().getPlayer1Name());
        label.setFont(Font.font("Lucida Bright", FontWeight.BOLD,30));
        label.relocate(300,840);
        label.setTextFill(Color.rgb(0,255,255));
        pane.getChildren().add(label);
        Label label1=new Label(GameController.getBattleField().getPlayer2Name());
        label1.setFont(Font.font("Lucida Bright", FontWeight.BOLD,30));
        label1.setTextFill(Color.rgb(202,0,202));
        label1.relocate(300,0);
        pane.getChildren().add(label1);
    }
}
