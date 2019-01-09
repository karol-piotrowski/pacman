package com.kodilla;

import javafx.scene.layout.GridPane;

import static com.kodilla.Pacman.*;

public class GhostDen {
    private int releasePeriod;
    private int stepsToRelease;
    private Level level;
    private GridPane grid;

    private ImageSet redGhostImages = new ImageSet(ghRedL1Img, ghRedR1Img, ghRedU1Img, ghRedD1Img, ghVul2Img);
    private ImageSet pinkGhostImages = new ImageSet(ghPinkL1Img, ghPinkR1Img, ghPinkU1Img, ghPinkD1Img, ghVul2Img);
    private ImageSet orangeGhostImages = new ImageSet(ghOrangeL1Img, ghOrangeR1Img, ghOrangeU1Img, ghOrangeD1Img, ghVul2Img);
    private ImageSet cyanGhostImages = new ImageSet(ghCyanL1Img, ghCyanR1Img, ghCyanU1Img, ghCyanD1Img, ghVul2Img);

    private MovingObject redGhost;
    private MovingObject orangeGhost;
    private MovingObject cyanGhost;
    private MovingObject pinkGhost;

    public GhostDen(int releasePeriod, Level level, GridPane grid) {
        this.releasePeriod = releasePeriod;
        this.level = level;
        this.grid = grid;
        stepsToRelease = releasePeriod;
        redGhost = new MovingObject(false, redGhostImages, level);
        orangeGhost = new MovingObject(false, orangeGhostImages, level);
        cyanGhost = new MovingObject(false, cyanGhostImages, level);
        pinkGhost = new MovingObject(false, pinkGhostImages, level);
    }

    public MovingObject tryToRelease() {
        MovingObject ghostToRelease;
        if (monsters.size() >= maxGhosts) {
            return null;
        } else if (stepsToRelease-- > 0) {
            return null;
        }
        switch (monsters.size() % 4) {
            case 0:
                ghostToRelease = new MovingObject(redGhost);
                System.out.println("red");
                break;
            case 1:
                ghostToRelease = new MovingObject(orangeGhost);
                System.out.println("orange");
                break;
            case 2:
                ghostToRelease = new MovingObject(cyanGhost);
                System.out.println("cyan");
                break;
            case 3:
                ghostToRelease = new MovingObject(pinkGhost);
                System.out.println("pink");
                break;
            default:
                ghostToRelease = null;
        }
        stepsToRelease = releasePeriod;
        return ghostToRelease;


    }
}
