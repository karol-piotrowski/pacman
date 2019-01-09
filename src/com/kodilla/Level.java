package com.kodilla;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Level {
    private char[][] layout;
    private int warpAPosX, warpAPosY, warpaPosX, warpaPosY;
    private int fencePosX, fencePosY;
    private int pacmanPosX, pacmanPosY;
    private int dotsLeft;


    public void loadLevel() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("level1.txt").getFile());
        Path path = Paths.get(file.getPath());
        List<String> linesList = new ArrayList<>();

        try {
            Stream<String> fileLines = Files.lines(path);
            fileLines.forEach(linesList::add);
        } catch (IOException e) {
            System.out.println("No level file present!");
        }

        if (linesList.get(0).contains("\uFEFF")) {
            linesList.set(0, linesList.get(0).substring(1));
        }
        String[] lines = linesList.toArray(new String[linesList.size()]);

        layout = new char[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) {
            layout[i] = lines[i].toCharArray();
        }
        countDots();
    }

    public int countDots(){
        dotsLeft=0;
        for(char[] chArray : layout) {
            for(char c : chArray) {
                if(c=='.' || c=='O') {
                    dotsLeft++;
                }
            }
        }
        return dotsLeft;
    }

    public int getDotsLeft() {
        return dotsLeft;
    }

    public char[][] getLayout() {
        return layout;
    }

    public void setLayoutCell(int x, int y, char ch) {
        layout[y][x] = ch;
    }

    public int getWarpAPosX() {
        return warpAPosX;
    }

    public void setWarpAPosX(int warpAPosX) {
        this.warpAPosX = warpAPosX;
    }

    public int getWarpAPosY() {
        return warpAPosY;
    }

    public void setWarpAPosY(int warpAPosY) {
        this.warpAPosY = warpAPosY;
    }

    public int getWarpaPosX() {
        return warpaPosX;
    }

    public void setWarpaPosX(int warpaPosX) {
        this.warpaPosX = warpaPosX;
    }

    public int getWarpaPosY() {
        return warpaPosY;
    }

    public void setWarpaPosY(int warpaPosY) {
        this.warpaPosY = warpaPosY;
    }

    public int getFencePosX() {
        return fencePosX;
    }

    public void setFencePosX(int fencePosX) {
        this.fencePosX = fencePosX;
    }

    public int getFencePosY() {
        return fencePosY;
    }

    public void setFencePosY(int fencePosY) {
        this.fencePosY = fencePosY;
    }

    public int getPacmanPosX() {
        return pacmanPosX;
    }

    public void setPacmanPosX(int pacmanPosX) {
        this.pacmanPosX = pacmanPosX;
    }

    public int getPacmanPosY() {
        return pacmanPosY;
    }

    public void setPacmanPosY(int pacmanPosY) {
        this.pacmanPosY = pacmanPosY;
    }
}
