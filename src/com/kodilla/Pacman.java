package com.kodilla;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

import static com.kodilla.Direction.*;

public class Pacman extends Application {

    static final Image imageback = new Image("file:resources/sprites/level/background.png");
    static final Image wallImg = new Image("file:resources/sprites/level/wall.png");
    static final Image dotImg = new Image("file:resources/sprites/level/dot.png");
    static final Image bonusImg = new Image("file:resources/sprites/level/bonus.png");
    static final Image fenceImg = new Image("file:resources/sprites/level/fence.png");


    static final Image pacR2Img = new Image("file:resources/sprites/pacman/pac_r2.png");
    static final Image pacL2Img = new Image("file:resources/sprites/pacman/pac_l2.png");
    static final Image pacU2Img = new Image("file:resources/sprites/pacman/pac_u2.png");
    static final Image pacD2Img = new Image("file:resources/sprites/pacman/pac_d2.png");
    static final Image pacDead7Img = new Image("file:resources/sprites/pacman/pac_dead7.png");


    static final Image ghCyanD1Img = new Image("file:resources/sprites/ghosts/ghcyan_d1.png");
    static final Image ghCyanL1Img = new Image("file:resources/sprites/ghosts/ghcyan_l1.png");
    static final Image ghCyanR1Img = new Image("file:resources/sprites/ghosts/ghcyan_r1.png");
    static final Image ghCyanU1Img = new Image("file:resources/sprites/ghosts/ghcyan_u1.png");

    static final Image ghOrangeD1Img = new Image("file:resources/sprites/ghosts/ghorange_d1.png");
    static final Image ghOrangeL1Img = new Image("file:resources/sprites/ghosts/ghorange_l1.png");
    static final Image ghOrangeR1Img = new Image("file:resources/sprites/ghosts/ghorange_r1.png");
    static final Image ghOrangeU1Img = new Image("file:resources/sprites/ghosts/ghorange_u1.png");

    static final Image ghPinkD1Img = new Image("file:resources/sprites/ghosts/ghpink_d1.png");
    static final Image ghPinkL1Img = new Image("file:resources/sprites/ghosts/ghpink_l1.png");
    static final Image ghPinkR1Img = new Image("file:resources/sprites/ghosts/ghpink_r1.png");
    static final Image ghPinkU1Img = new Image("file:resources/sprites/ghosts/ghpink_u1.png");

    static final Image ghRedD1Img = new Image("file:resources/sprites/ghosts/ghred_d1.png");
    static final Image ghRedL1Img = new Image("file:resources/sprites/ghosts/ghred_l1.png");
    static final Image ghRedR1Img = new Image("file:resources/sprites/ghosts/ghred_r1.png");
    static final Image ghRedU1Img = new Image("file:resources/sprites/ghosts/ghred_u1.png");

    static final Image ghVul2Img = new Image("file:resources/sprites/ghosts/ghvul_2.png");

    private ImageSet pacmanImages = new ImageSet(pacL2Img, pacR2Img, pacU2Img, pacD2Img);

    private Level level = new Level();
    static MovingObject pacman;
    private GhostDen ghostDen;
    static List<MovingObject> monsters = new ArrayList<>();

    private Label player1Label = new Label("1UP");
    private Label player1PointsLabel = new Label("" + 0);
    private Label endGameLabel = new Label("VICTORY!");

    private Label gameTitleLabel = new Label("P A C M A N");
    private Label gameSubtitle = new Label("TURN-BASED GAME");
    private Label difficultyLevelLabel = new Label("Difficulty level");
    private Label numberOfGhostsLabel = new Label("Number of ghosts:");
    private Label bonusDurationLabel = new Label("Bonus duration:");

    private Button playButton = new Button("Play");
    private Button hallOfFameButton = new Button("Hall of Fame");

    private static ImageView pacmanImgView = new ImageView(pacR2Img);

    static int maxGhosts;
    static int bonusDuration;
    private int pointsAchieved = 0;

    private List<HofEntry> hallOfFame = new ArrayList<>();
    private String nameEntered = "";

    @Override
    public void start(Stage primaryStage) throws Exception {

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        // Menu Scene
        gameTitleLabel.setFont(new Font("Courier New Bold", 40));
        gameTitleLabel.setTextFill(Color.YELLOW);
        gameSubtitle.setFont(new Font("Courier New", 20));
        gameSubtitle.setTextFill(Color.YELLOW);
        difficultyLevelLabel.setFont(new Font("Courier New Bold", 20));
        difficultyLevelLabel.setTextFill(Color.WHITE);
        numberOfGhostsLabel.setFont(new Font("Courier New Bold", 16));
        numberOfGhostsLabel.setTextFill(Color.WHITE);
        bonusDurationLabel.setFont(new Font("Courier New Bold", 16));
        bonusDurationLabel.setTextFill(Color.WHITE);

        Slider numberOfGhostsSlider = new Slider();
        numberOfGhostsSlider.setMin(0);
        numberOfGhostsSlider.setMax(100);
        numberOfGhostsSlider.setValue(4);
        numberOfGhostsSlider.setShowTickLabels(true);
        numberOfGhostsSlider.setShowTickMarks(true);
        numberOfGhostsSlider.setMajorTickUnit(10);
        numberOfGhostsSlider.setMinorTickCount(1);
        numberOfGhostsSlider.setBlockIncrement(1);
        TextField numberOfGhostsValueText = new TextField("" + (int) numberOfGhostsSlider.getValue());
        numberOfGhostsValueText.setEditable(false);

        numberOfGhostsSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                maxGhosts = new_val.intValue();
                numberOfGhostsValueText.setText("" + new_val.intValue());
            }
        });

        Slider bonusDurationSlider = new Slider();
        bonusDurationSlider.setMin(0);
        bonusDurationSlider.setMax(100);
        bonusDurationSlider.setValue(40);
        bonusDurationSlider.setShowTickLabels(true);
        bonusDurationSlider.setShowTickMarks(true);
        bonusDurationSlider.setMajorTickUnit(10);
        bonusDurationSlider.setMinorTickCount(1);
        bonusDurationSlider.setBlockIncrement(5);
        bonusDurationSlider.setSnapToTicks(true);
        TextField bonusDurationValueText = new TextField("" + (int) bonusDurationSlider.getValue());
        bonusDurationValueText.setEditable(false);
        bonusDurationSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                bonusDuration = new_val.intValue();
                bonusDurationValueText.setText("" + new_val.intValue());
            }
        });


        playButton.setPrefWidth(200);

        hallOfFameButton.setPrefWidth(200);

        loadHallOfFame();
        GridPane menuGrid = new GridPane();
        Scene menuScene = new Scene(menuGrid, 448, 552, Color.BLACK);
        menuGrid.getChildren().removeAll();
        menuGrid.setBackground(background);
        menuGrid.setAlignment(Pos.CENTER);
        menuGrid.setPadding(new Insets(0, 0, 0, 0));
        menuGrid.setHgap(10);
        menuGrid.setVgap(10);


        menuGrid.add(gameTitleLabel, 0, 0, 2, 1);
        GridPane.setHalignment(gameTitleLabel, HPos.CENTER);
        menuGrid.add(gameSubtitle, 0, 1, 2, 1);
        GridPane.setHalignment(gameSubtitle, HPos.CENTER);
        menuGrid.add(difficultyLevelLabel, 0, 4);
        menuGrid.add(numberOfGhostsLabel, 0, 5, 1, 1);
        menuGrid.add(numberOfGhostsValueText, 1, 5, 1, 1);
        menuGrid.add(numberOfGhostsSlider, 0, 6, 2, 1);
        menuGrid.add(bonusDurationLabel, 0, 7, 1, 1);
        menuGrid.add(bonusDurationValueText, 1, 7, 1, 1);
        menuGrid.add(bonusDurationSlider, 0, 8, 2, 1);
        menuGrid.add(playButton, 0, 10, 2, 1);
        GridPane.setHalignment(playButton, HPos.CENTER);
        menuGrid.add(hallOfFameButton, 0, 11, 2, 1);
        GridPane.setHalignment(hallOfFameButton, HPos.CENTER);

        //

        // Hall of Fame scene
        GridPane hofGrid = new GridPane();


        drawHallOfFame(background, hofGrid);


        Scene hofScene = new Scene(hofGrid, 448, 552, Color.BLACK);

        //

        // Enter new result

        Label enterYourNameLabel = new Label("Enter your initials:");
        enterYourNameLabel.setFont(new Font("Courier New Bold", 30));
        enterYourNameLabel.setTextFill(Color.WHITE);

        Label nameLabel = new Label("___");
        nameLabel.setFont(new Font("Courier New Bold", 50));
        nameLabel.setTextFill(Color.GOLD);

        Label pointsLabel = new Label("" + pointsAchieved);
        pointsLabel.setFont(new Font("Courier New Bold", 50));
        pointsLabel.setTextFill(Color.GOLD);

        GridPane enterResultGrid = new GridPane();
        enterResultGrid.getChildren().removeAll();
        enterResultGrid.setBackground(background);
        enterResultGrid.setAlignment(Pos.CENTER);
        enterResultGrid.setPadding(new Insets(40, 40, 10, 10));
        enterResultGrid.setHgap(40);
        enterResultGrid.setVgap(40);

        enterResultGrid.add(enterYourNameLabel, 0, 0, 2, 1);
        GridPane.setHalignment(enterYourNameLabel, HPos.CENTER);

        enterResultGrid.add(nameLabel, 0, 1, 1, 1);
        GridPane.setHalignment(nameLabel, HPos.LEFT);
        enterResultGrid.add(pointsLabel, 1, 1, 1, 1);
        GridPane.setHalignment(pointsLabel, HPos.RIGHT);

        Scene enterNameScene = new Scene(enterResultGrid, 448, 552, Color.BLACK);

        //


        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 448, 552, Color.BLACK);


        Label finalPoints = new Label();
        finalPoints.setFont(new Font("Courier New Bold", 24));
        finalPoints.setTextFill(Color.YELLOW);
        finalPoints.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0), new Insets(0, 0, 0, 0))));
        finalPoints.setAlignment(Pos.CENTER);
        finalPoints.setPadding(new Insets(20, 20, 20, 20));
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);
        vbox.getChildren().add(endGameLabel);
        vbox.getChildren().add(finalPoints);


        Scene endScene = new Scene(vbox, 448, 552, Color.BLACK);

        primaryStage.setTitle("Pacman");
        primaryStage.setScene(menuScene);
        primaryStage.show();

        hallOfFameButton.setOnAction(e -> {
            drawHallOfFame(background, hofGrid);
            primaryStage.setScene(hofScene);
        });

        playButton.setOnAction(e -> {
                    pointsAchieved = 0;
                    maxGhosts = (int) numberOfGhostsSlider.getValue();
                    bonusDuration = (int) bonusDurationSlider.getValue();
                    monsters.clear();
                    player1Label.setFont(new Font("Courier New Bold", 16));
                    player1Label.setTextFill(Color.WHITE);
                    player1PointsLabel.setFont(new Font("Courier New Bold", 16));
                    player1PointsLabel.setTextFill(Color.WHITE);
                    endGameLabel.setFont(new Font("Courier New Bold", 24));
                    endGameLabel.setTextFill(Color.YELLOW);
                    endGameLabel.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0), new Insets(0, 0, 0, 0))));
                    endGameLabel.setAlignment(Pos.CENTER);
                    endGameLabel.setPadding(new Insets(20, 20, 20, 20));

                    level.loadLevel();
                    pacman = new MovingObject(true, pacmanImages, level);

                    grid.getChildren().removeAll();
                    grid.setBackground(background);
                    grid.setAlignment(Pos.TOP_LEFT);
                    grid.setPadding(new Insets(0, 0, 0, 0));
                    grid.setHgap(0);
                    grid.setVgap(0);

                    drawLevel(level, grid);

                    ghostDen = new GhostDen(8, level, grid);
                    primaryStage.setScene(scene);
                }
        );

        scene.setOnKeyPressed(ke -> {
            if (pacman.isJustLostLife()) {
                pacman.setJustLostLife(false);
                grid.getChildren().clear();
                drawLevel(level, grid);
            } else {
                boolean moved = false;
                switch (ke.getCode()) {
                    case UP:
                        moved = pacman.moveOne(grid, UP);
                        break;
                    case DOWN:
                        moved = pacman.moveOne(grid, DOWN);
                        break;
                    case LEFT:
                        moved = pacman.moveOne(grid, LEFT);
                        break;
                    case RIGHT:
                        moved = pacman.moveOne(grid, RIGHT);
                        break;
                }
                player1PointsLabel.setText("" + pacman.getPoints());
                if (level.getDotsLeft() == 0) {
                    pointsAchieved = pacman.getPoints();

                    finalPoints.setText("" + pointsAchieved);
                    primaryStage.setScene(endScene);
                } else if (moved) {
                    MovingObject newGhost = ghostDen.tryToRelease();
                    if (newGhost != null) {
                        monsters.add(newGhost);
                        System.out.println("Ghost added. Total ghosts: " + monsters.size());
                    }
                    Random rand = new Random();

                    for (int i = 0; i < monsters.size(); i++) { // standard loop used to avoid Concurrent Modification Exception
                        MovingObject monster = monsters.get(i);
                        Direction direction;
                        List<Direction> possibleMoves = monster.possibleMoves();
                        if (possibleMoves.size() == 2 && possibleMoves.contains(monster.getLastDirection()) && possibleMoves.contains(monster.getOppositeDirection())) {
                            direction = monster.getLastDirection();
                        } else if (possibleMoves.size() == 3 && possibleMoves.contains(monster.getLastDirection()) && possibleMoves.contains(monster.getOppositeDirection())) {
                            possibleMoves.remove(monster.getOppositeDirection());
                            direction = possibleMoves.get(rand.nextInt(possibleMoves.size()));
                        } else {
                            direction = possibleMoves.get(rand.nextInt(possibleMoves.size()));
                        }
                        monster.moveOne(grid, direction);
                        monster.setLastDirection(direction);

                    }

                    if (pacman.getLivesLeft() == 0) {
                        pointsAchieved = pacman.getPoints();
                        finalPoints.setText("" + pointsAchieved);
                        endGameLabel.setText("GAME OVER");

                        primaryStage.setScene(endScene);
                    }
                }
            }
        });

        endScene.setOnKeyPressed(ke -> {
            pointsLabel.setText("" + pointsAchieved);
            primaryStage.setScene(enterNameScene);
        });

        endScene.setOnMouseClicked(e -> {
            pointsLabel.setText("" + pointsAchieved);
            primaryStage.setScene(enterNameScene);
        });

        hofScene.setOnKeyPressed(ke -> {
            primaryStage.setScene(menuScene);
        });

        hofScene.setOnMouseClicked(e -> {
            primaryStage.setScene(menuScene);
        });

        enterNameScene.setOnKeyPressed(ke -> {
            if (ke.getCode().isLetterKey()) {
                String charEntered = ke.getCode().toString();
                nameEntered = nameEntered + charEntered;
                nameLabel.setText(nameEntered);
                if (nameEntered.length() >= 3) {
                    HofEntry hofEntry = new HofEntry(nameEntered, maxGhosts, bonusDuration, pointsAchieved);
                    hallOfFame.add(hofEntry);
                    Comparator<HofEntry> compareByScore = Comparator.comparingInt(HofEntry::getScore);
                    hallOfFame.sort(compareByScore.reversed());
                    if (hallOfFame.size() > 10) hallOfFame = hallOfFame.subList(0, 10);
                    saveHallOfFame();
                    nameEntered = "";
                    nameLabel.setText("__");
                    drawHallOfFame(background, hofGrid);
                    primaryStage.setScene(hofScene);

                }
            }


        });
    }

    public void drawHallOfFame(Background background, GridPane hofGrid) {
        Label hallOfFameTitle = new Label("Hall of Fame");
        hallOfFameTitle.setFont(new Font("Courier New Bold", 40));
        hallOfFameTitle.setTextFill(Color.WHITE);

        Label rankLabel = new Label("Rank");
        rankLabel.setFont(new Font("Courier New", 15));
        rankLabel.setTextFill(Color.RED);

        Label initialsLabel = new Label("Initials");
        initialsLabel.setFont(new Font("Courier New", 15));
        initialsLabel.setTextFill(Color.RED);

        Label numberOfGhostsLabel = new Label("Ghosts");
        numberOfGhostsLabel.setFont(new Font("Courier New", 15));
        numberOfGhostsLabel.setTextFill(Color.RED);

        Label bonusDurationLabel = new Label("Bonus Duration");
        bonusDurationLabel.setFont(new Font("Courier New", 15));
        bonusDurationLabel.setTextFill(Color.RED);

        Label scoreLabel = new Label("Score");
        scoreLabel.setFont(new Font("Courier New", 15));
        scoreLabel.setTextFill(Color.RED);

        hofGrid.getChildren().clear();
        hofGrid.getChildren().removeAll();
        hofGrid.setBackground(background);
        hofGrid.setAlignment(Pos.CENTER);
        hofGrid.setPadding(new Insets(10, 10, 10, 10));
        hofGrid.setHgap(20);
        hofGrid.setVgap(20);

        hofGrid.add(hallOfFameTitle, 0, 0, 5, 1);
        GridPane.setHalignment(hallOfFameTitle, HPos.CENTER);

        hofGrid.add(rankLabel, 0, 2, 1, 1);
        hofGrid.add(initialsLabel, 1, 2, 1, 1);
        hofGrid.add(numberOfGhostsLabel, 2, 2, 1, 1);
        hofGrid.add(bonusDurationLabel, 3, 2, 1, 1);
        hofGrid.add(scoreLabel, 4, 2, 1, 1);

        int hofPlace = 1;
        for (HofEntry hofentry : hallOfFame) {
            Label hofRank = new Label("" + hofPlace);
            hofRank.setFont(new Font("Courier New", 20));
            hofRank.setTextFill(Color.GOLD);
            Label hofNameEntry = new Label(hofentry.getInitials());
            hofNameEntry.setFont(new Font("Courier New", 20));
            hofNameEntry.setTextFill(Color.GOLD);
            Label hofMaxGhostsEntry = new Label("" + hofentry.getMaxGhosts());
            hofMaxGhostsEntry.setFont(new Font("Courier New", 20));
            hofMaxGhostsEntry.setTextFill(Color.GOLD);
            Label hofBonusDurationEntry = new Label("" + hofentry.getBonusDuration());
            hofBonusDurationEntry.setFont(new Font("Courier New", 20));
            hofBonusDurationEntry.setTextFill(Color.GOLD);
            Label hofPointsEntry = new Label("" + hofentry.getScore());
            hofPointsEntry.setFont(new Font("Courier New Bold", 20));
            hofPointsEntry.setTextFill(Color.GOLD);

            hofGrid.add(hofRank, 0, 2 + hofPlace, 1, 1);
            hofGrid.add(hofNameEntry, 1, 2 + hofPlace, 1, 1);
            hofGrid.add(hofMaxGhostsEntry, 2, 2 + hofPlace, 1, 1);
            hofGrid.add(hofBonusDurationEntry, 3, 2 + hofPlace, 1, 1);
            hofGrid.add(hofPointsEntry, 4, 2 + hofPlace, 1, 1);
            GridPane.setHalignment(hofPointsEntry, HPos.RIGHT);

            hofPlace++;
        }
    }

    public void drawLevel(Level level, GridPane grid) {
        grid.getChildren().clear();
        grid.add(player1Label, 0, 0, 4, 1);
        grid.add(player1PointsLabel, 0, 1, 4, 1);

        player1PointsLabel.setText("" + pacman.getPoints());

        for (int i = 0; i < level.getLayout().length; i++) {
            for (int j = 0; j < level.getLayout()[i].length; j++) {
                if (level.getLayout()[i][j] == 'X') {
                    grid.add(new ImageView(wallImg), j, i + 2, 1, 1);
                } else if (level.getLayout()[i][j] == '.') {
                    grid.add(new ImageView(dotImg), j, i + 2, 1, 1);
                } else if (level.getLayout()[i][j] == 'O') {
                    grid.add(new ImageView(bonusImg), j, i + 2, 1, 1);
                } else if (level.getLayout()[i][j] == 'F') {
                    grid.add(new ImageView(fenceImg), j, i + 2, 1, 1);
                    level.setFencePosX(j);
                    level.setFencePosY(i);
                } else if (level.getLayout()[i][j] == 'S') {
                    grid.add(pacmanImgView, j, i + 2, 1, 1);
                    level.setPacmanPosX(j);
                    level.setPacmanPosY(i);
                    pacman.setPosX(j);
                    pacman.setPosY(i);
                } else if (level.getLayout()[i][j] == 'r') {
                    grid.add(new ImageView(ghRedD1Img), j, i + 2, 1, 1);
                } else if (level.getLayout()[i][j] == 'o') {
                    grid.add(new ImageView(ghOrangeD1Img), j, i + 2, 1, 1);
                } else if (level.getLayout()[i][j] == 'c') {
                    grid.add(new ImageView(ghCyanD1Img), j, i + 2, 1, 1);
                } else if (level.getLayout()[i][j] == 'p') {
                    grid.add(new ImageView(ghPinkD1Img), j, i + 2, 1, 1);
                } else if (level.getLayout()[i][j] == 'A') {
                    level.setWarpAPosX(j);
                    level.setWarpAPosY(i);
                } else if (level.getLayout()[i][j] == 'a') {
                    level.setWarpaPosX(j);
                    level.setWarpaPosY(i);
                }
            }
        }

        for (int i = 0; i < pacman.getLivesLeft(); i++) {
            grid.add(new ImageView(pacL2Img), i, level.getLayout().length + 2, 1, 1);
        }
    }

    public void saveHallOfFame() {
        File savedHof = new File("halloffame");
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savedHof));
            oos.writeObject(hallOfFame);
            oos.close();
        } catch (IOException e) {
            System.out.println("I/O error: " + e);
        }
    }

    public void loadHallOfFame() {
        File savedHof = new File("halloffame");
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savedHof));
            Object readList = ois.readObject();
            if (readList instanceof ArrayList) {
                hallOfFame.addAll((ArrayList) readList);
            }
            ois.close();

        } catch (Exception e) {
            System.out.println("I/O error: " + e);

        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Node getNodeByRowAndColumn(int row, int column, GridPane gridPane) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                return node;
            }
        }
        return null;
    }


}
