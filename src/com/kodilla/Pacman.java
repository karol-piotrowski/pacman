package com.kodilla;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Pacman extends Application {

    private Image imageback = new Image("file:resources/sprites/level/background.png");
    private Image wallImg = new Image("file:resources/sprites/level/wall.png");
    private Image dotImg = new Image("file:resources/sprites/level/dot.png");
    private Image bonusImg = new Image("file:resources/sprites/level/bonus.png");
    private Image fenceImg = new Image("file:resources/sprites/level/fence.png");

    private Image pacC0Img = new Image("file:resources/sprites/pacman/pac_c0.png");
    private Image pacR1Img = new Image("file:resources/sprites/pacman/pac_r1.png");
    private Image pacR2Img = new Image("file:resources/sprites/pacman/pac_r2.png");
    private Image pacL1Img = new Image("file:resources/sprites/pacman/pac_l1.png");
    private Image pacL2Img = new Image("file:resources/sprites/pacman/pac_l2.png");
    private Image pacU1Img = new Image("file:resources/sprites/pacman/pac_u1.png");
    private Image pacU2Img = new Image("file:resources/sprites/pacman/pac_u2.png");
    private Image pacD1Img = new Image("file:resources/sprites/pacman/pac_d1.png");
    private Image pacD2Img = new Image("file:resources/sprites/pacman/pac_d2.png");
    private Image pacDead1Img = new Image("file:resources/sprites/pacman/pac_dead1.png");
    private Image pacDead2Img = new Image("file:resources/sprites/pacman/pac_dead2.png");
    private Image pacDead3Img = new Image("file:resources/sprites/pacman/pac_dead3.png");
    private Image pacDead4Img = new Image("file:resources/sprites/pacman/pac_dead4.png");
    private Image pacDead5Img = new Image("file:resources/sprites/pacman/pac_dead5.png");
    private Image pacDead6Img = new Image("file:resources/sprites/pacman/pac_dead6.png");
    private Image pacDead7Img = new Image("file:resources/sprites/pacman/pac_dead7.png");
    private Image pacDead8Img = new Image("file:resources/sprites/pacman/pac_dead8.png");
    private Image pacDead9Img = new Image("file:resources/sprites/pacman/pac_dead9.png");
    private Image pacDead10Img = new Image("file:resources/sprites/pacman/pac_dead10.png");
    private Image pacDead11Img = new Image("file:resources/sprites/pacman/pac_dead11.png");

    private Image ghCyanD1Img = new Image("file:resources/sprites/ghosts/ghcyan_d1.png");
    private Image ghCyanD2Img = new Image("file:resources/sprites/ghosts/ghcyan_d2.png");
    private Image ghCyanL1Img = new Image("file:resources/sprites/ghosts/ghcyan_l1.png");
    private Image ghCyanL2Img = new Image("file:resources/sprites/ghosts/ghcyan_l2.png");
    private Image ghCyanR1Img = new Image("file:resources/sprites/ghosts/ghcyan_r1.png");
    private Image ghCyanR2Img = new Image("file:resources/sprites/ghosts/ghcyan_r2.png");
    private Image ghCyanU1Img = new Image("file:resources/sprites/ghosts/ghcyan_u1.png");
    private Image ghCyanU2Img = new Image("file:resources/sprites/ghosts/ghcyan_u2.png");

    private Image ghOrangeD1Img = new Image("file:resources/sprites/ghosts/ghorange_d1.png");
    private Image ghOrangeD2Img = new Image("file:resources/sprites/ghosts/ghorange_d2.png");
    private Image ghOrangeL1Img = new Image("file:resources/sprites/ghosts/ghorange_l1.png");
    private Image ghOrangeL2Img = new Image("file:resources/sprites/ghosts/ghorange_l2.png");
    private Image ghOrangeR1Img = new Image("file:resources/sprites/ghosts/ghorange_r1.png");
    private Image ghOrangeR2Img = new Image("file:resources/sprites/ghosts/ghorange_r2.png");
    private Image ghOrangeU1Img = new Image("file:resources/sprites/ghosts/ghorange_u1.png");
    private Image ghOrangeU2Img = new Image("file:resources/sprites/ghosts/ghorange_u2.png");

    private Image ghPinkD1Img = new Image("file:resources/sprites/ghosts/ghpink_d1.png");
    private Image ghPinkD2Img = new Image("file:resources/sprites/ghosts/ghpink_d2.png");
    private Image ghPinkL1Img = new Image("file:resources/sprites/ghosts/ghpink_l1.png");
    private Image ghPinkL2Img = new Image("file:resources/sprites/ghosts/ghpink_l2.png");
    private Image ghPinkR1Img = new Image("file:resources/sprites/ghosts/ghpink_r1.png");
    private Image ghPinkR2Img = new Image("file:resources/sprites/ghosts/ghpink_r2.png");
    private Image ghPinkU1Img = new Image("file:resources/sprites/ghosts/ghpink_u1.png");
    private Image ghPinkU2Img = new Image("file:resources/sprites/ghosts/ghpink_u2.png");

    private Image ghRedD1Img = new Image("file:resources/sprites/ghosts/ghred_d1.png");
    private Image ghRedD2Img = new Image("file:resources/sprites/ghosts/ghred_d2.png");
    private Image ghRedL1Img = new Image("file:resources/sprites/ghosts/ghred_l1.png");
    private Image ghRedL2Img = new Image("file:resources/sprites/ghosts/ghred_l2.png");
    private Image ghRedR1Img = new Image("file:resources/sprites/ghosts/ghred_r1.png");
    private Image ghRedR2Img = new Image("file:resources/sprites/ghosts/ghred_r2.png");
    private Image ghRedU1Img = new Image("file:resources/sprites/ghosts/ghred_u1.png");
    private Image ghRedU2Img = new Image("file:resources/sprites/ghosts/ghred_u2.png");

    private Image ghVul1Img = new Image("file:resources/sprites/ghosts/ghvul_1.png");
    private Image ghVul2Img = new Image("file:resources/sprites/ghosts/ghvul_2.png");
    private Image ghVul3Img = new Image("file:resources/sprites/ghosts/ghvul_3.png");
    private Image ghVul4Img = new Image("file:resources/sprites/ghosts/ghvul_4.png");


    private Label player1Label = new Label("1UP");
    private Label player1PointsLabel = new Label("0");

    private char[][] levelLayout;

    public void loadLevel() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("level1.txt").getFile());
        Path path = Paths.get(file.getPath());
        List<String> linesList = new ArrayList<String>();

        try {
            Stream<String> fileLines = Files.lines(path);
            fileLines.forEach(linesList::add);
        } catch (IOException e) {
            System.out.println("No level file present!");
        }

        String[] lines = linesList.toArray(new String[linesList.size()]);

        levelLayout = new char[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) {
            levelLayout[i] = lines[i].toCharArray();
        }
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        player1Label.setFont(new Font("Courier New Bold", 16));
        player1Label.setTextFill(Color.WHITE);
        player1PointsLabel.setFont(new Font("Courier New Bold", 16));
        player1PointsLabel.setTextFill(Color.WHITE);

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        loadLevel();

        GridPane grid = new GridPane();
        grid.setBackground(background);
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setPadding(new Insets(0, 0, 0, 0));
        grid.setHgap(0);
        grid.setVgap(0);

        grid.add(player1Label, 0, 0, 4, 1);
        grid.add(player1PointsLabel, 0, 1, 4, 1);

        for (int i = 0; i < levelLayout.length; i++) {
            for (int j = 0; j < levelLayout[i].length; j++) {
                if (levelLayout[i][j] == 'X') {
                    grid.add(new ImageView(wallImg), j, i + 2, 1, 1);
                } else if (levelLayout[i][j] == '.') {
                    grid.add(new ImageView(dotImg), j, i + 2, 1, 1);
                } else if (levelLayout[i][j] == 'O') {
                    grid.add(new ImageView(bonusImg), j, i + 2, 1, 1);
                } else if (levelLayout[i][j] == 'F') {
                    grid.add(new ImageView(fenceImg), j, i + 2, 1, 1);
                } else if (levelLayout[i][j] == 'S') {
                    grid.add(new ImageView(pacR2Img), j, i + 2, 2, 1);
                } else if (levelLayout[i][j] == 'r') {
                    grid.add(new ImageView(ghRedD1Img), j, i + 2, 1, 1);
                } else if (levelLayout[i][j] == 'o') {
                    grid.add(new ImageView(ghOrangeD1Img), j, i + 2, 1, 1);
                } else if (levelLayout[i][j] == 'c') {
                    grid.add(new ImageView(ghCyanD1Img), j, i + 2, 1, 1);
                } else if (levelLayout[i][j] == 'p') {
                    grid.add(new ImageView(ghPinkD1Img), j, i + 2, 1, 1);
                }

            }
        }

        grid.add(new ImageView(pacL2Img), 0, levelLayout.length+2, 1, 1);
        grid.add(new ImageView(pacL2Img), 1, levelLayout.length+2, 1, 1);
        grid.add(new ImageView(pacL2Img), 2, levelLayout.length+2, 1, 1);



        Scene scene = new Scene(grid, 448, 552, Color.BLACK);

        primaryStage.setTitle("Pacman");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
