package com.kodilla;


import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.kodilla.Pacman.*;

public class MovingObject {
    private boolean isPacman;
    private int posX;
    private int posY;
    private int destX;
    private int destY;
    private int points;
    private int movesCount;
    private boolean bonusWorking;
    private int bonusTurnsCount;
    private Image imageLeft;
    private Image imageRight;
    private Image imageUp;
    private Image imageDown;
    private Image imageVul;
    private Level level;
    private boolean justLostLife;
    private int livesLeft;
    private Direction lastDirection;

    public MovingObject(boolean isPacman, ImageSet imageSet, Level level) {
        this.isPacman = isPacman;
        if (isPacman) {
            posX = level.getPacmanPosX();
            posY = level.getPacmanPosY();
        } else if (!isPacman) {
            posX = level.getFencePosX();
            posY = level.getFencePosY();
        }
        this.destX = posX;
        this.destY = posY;
        this.imageLeft = imageSet.getImageLeft();
        this.imageRight = imageSet.getImageRight();
        this.imageUp = imageSet.getImageUp();
        this.imageDown = imageSet.getImageDown();
        this.imageVul = imageSet.getImageVul();
        this.level = level;
        points = 0;
        movesCount = 0;
        this.bonusWorking = false;
        this.bonusTurnsCount = 0;
        this.justLostLife = false;
        this.livesLeft = 3;


    }

    public MovingObject(MovingObject another) {
        this.isPacman = another.isPacman;
        this.posX = another.posX;
        this.posY = another.posY;
        this.destX = another.posX;
        this.destY = another.posY;
        this.imageLeft = another.imageLeft;
        this.imageRight = another.imageRight;
        this.imageUp = another.imageUp;
        this.imageDown = another.imageDown;
        this.imageVul = another.imageVul;
        this.level = another.level;
        points = 0;
        movesCount = 0;
        this.bonusWorking = false;
        this.bonusTurnsCount = 0;
        this.justLostLife = false;
        this.livesLeft = 3;

    }

    public boolean moveOne(GridPane grid, Direction direction) {
        destX = posX;
        destY = posY;
        ImageView objectNew = null;

        switch (direction) {
            case LEFT:
                destX = posX - 1;
                objectNew = (!isPacman && bonusWorking) ? new ImageView(imageVul) : new ImageView(imageLeft);
                break;
            case RIGHT:
                destX = posX + 1;
                objectNew = (!isPacman && bonusWorking) ? new ImageView(imageVul) : new ImageView(imageRight);
                break;
            case UP:
                destY = posY - 1;
                objectNew = (!isPacman && bonusWorking) ? new ImageView(imageVul) : new ImageView(imageUp);
                break;
            case DOWN:
                destY = posY + 1;
                objectNew = (!isPacman && bonusWorking) ? new ImageView(imageVul) : new ImageView(imageDown);
                break;
        }

        if (bonusWorking && bonusTurnsCount > 0) {
            bonusTurnsCount--;
        } else if (bonusWorking && bonusTurnsCount <= 0) {
            bonusWorking = false;
        }

        if (isPacman && level.getLayout()[posY][posX] == 'A' && (destX < 0 || destX >= level.getLayout()[posY].length || destY < 0 || destY >= level.getLayout().length)) {
            Node node = getNodeByRowAndColumn(posY + 2, posX, grid);
            grid.getChildren().remove(node);
            posX = level.getWarpaPosX();
            posY = level.getWarpaPosY();
            grid.add(objectNew, posX, posY + 2, 1, 1);

        } else if (isPacman && level.getLayout()[posY][posX] == 'a' && (destX < 0 || destX >= level.getLayout()[posY].length || destY < 0 || destY >= level.getLayout().length)) {
            Node node = getNodeByRowAndColumn(posY + 2, posX, grid);
            grid.getChildren().remove(node);
            posX = level.getWarpAPosX();
            posY = level.getWarpAPosY();
            grid.add(objectNew, posX, posY + 2, 1, 1);
        } else {

            char approaching = level.getLayout()[destY][destX];

            if (approaching == 'A' || approaching == 'a') {
                level.setLayoutCell(posX, posY, '_');
                Node node = getNodeByRowAndColumn(posY + 2, posX, grid);
                grid.getChildren().remove(node);
                posX = destX;
                posY = destY;
                grid.add(objectNew, posX, posY + 2, 1, 1);

            } else if (approaching != 'X' && approaching != 'F') {
                Node node = getNodeByRowAndColumn(posY + 2, posX, grid);
                grid.getChildren().remove(node);
                node = getNodeByRowAndColumn(posY + 2, posX, grid);
                while (node != null) {
                    grid.getChildren().remove(node);
                    node = getNodeByRowAndColumn(posY + 2, posX, grid);
                }

                switch (level.getLayout()[posY][posX]) {
                    case 'a':
                        break;
                    case 'A':
                        break;
                    case 'S':
                        break;
                    default:
                        if (isPacman) level.setLayoutCell(posX, posY, '_');
                }

                if (level.getLayout()[posY][posX] == '.') {
                    grid.add(new ImageView(dotImg), posX, posY + 2, 1, 1);
                } else if (level.getLayout()[posY][posX] == 'O') {
                    grid.add(new ImageView(bonusImg), posX, posY + 2, 1, 1);
                } else if (level.getLayout()[posY][posX] == 'F') {
                    grid.add(new ImageView(fenceImg), posX, posY + 2, 1, 1);
                }


                if (isPacman && approaching == '.') {
                    level.setLayoutCell(destX, destY, '_');
                    points += 10;
                    System.out.println(level.countDots());
                } else if (isPacman && approaching == 'O') {
                    level.setLayoutCell(destX, destY, '_');
                    points += 50;
                    System.out.println(level.countDots());

                    for (MovingObject m : monsters) {
                        m.bonusWorking = true;
                        m.bonusTurnsCount = bonusDuration;
                        node = getNodeByRowAndColumn(m.posY + 2, m.posX, grid);
                        grid.getChildren().remove(node);
                        grid.add(new ImageView(imageVul), m.posX, m.posY + 2, 1, 1);
                    }
                } else if (isPacman && points >= 0) {
                    points--;
                }

                if (isPacman) {
                    Iterator<MovingObject> monstersIterator = monsters.iterator();
                    while (monstersIterator.hasNext()) {
                        MovingObject m = monstersIterator.next();
                        if (destX == m.posX && destY == m.posY) {
                            if (m.bonusWorking) {
                                monstersIterator.remove();
                            } else {
                                loseLife(grid);
                                return true;

                            }
                        }
                    }

                } else {
                    if (destX == pacman.posX && destY == pacman.posY) {
                        if (bonusWorking) {
                            monsters.remove(this);
                            lastDirection = direction;
                            return true;
                        } else {
                            loseLife(grid);
                            lastDirection = direction;
                            return true;
                        }
                    }
                }
                node = getNodeByRowAndColumn(destY + 2, destX, grid);
                grid.getChildren().remove(node);
                grid.add(objectNew, destX, destY + 2, 1, 1);
                posX = destX;
                posY = destY;

                lastDirection = direction;
                return true;
            }
        }
        return false;
    }


    public List<Direction> possibleMoves() {
        List<Direction> directionList = new ArrayList<>();

        if (posY >= 0 && posX >= 1 && posY < level.getLayout().length && posX < level.getLayout()[posY].length) {
            if (level.getLayout()[posY][posX - 1] == '.' || level.getLayout()[posY][posX - 1] == 'O' || level.getLayout()[posY][posX - 1] == '_' || level.getLayout()[posY][posX - 1] == 'a' || level.getLayout()[posY][posX - 1] == 'A')
                directionList.add(Direction.LEFT);
        }

        if (posY >= 0 && posX >= 0 && posY < level.getLayout().length && posX < level.getLayout()[posY].length - 1) {
            if (level.getLayout()[posY][posX + 1] == '.' || level.getLayout()[posY][posX + 1] == 'O' || level.getLayout()[posY][posX + 1] == '_' || level.getLayout()[posY][posX + 1] == 'a' || level.getLayout()[posY][posX + 1] == 'A')
                directionList.add(Direction.RIGHT);
        }

        if (posY >= 0 && posX >= 0 && posY < level.getLayout().length - 1 && posX < level.getLayout()[posY].length) {
            if (level.getLayout()[posY + 1][posX] == '.' || level.getLayout()[posY + 1][posX] == 'O' || level.getLayout()[posY + 1][posX] == '_' || level.getLayout()[posY + 1][posX] == 'a' || level.getLayout()[posY + 1][posX] == 'A')
                directionList.add(Direction.DOWN);
        }

        if (posY >= 1 && posX >= 0 && posY < level.getLayout().length && posX < level.getLayout()[posY].length) {
            if (level.getLayout()[posY - 1][posX] == '.' || level.getLayout()[posY - 1][posX] == 'O' || level.getLayout()[posY - 1][posX] == '_' || level.getLayout()[posY - 1][posX] == 'a' || level.getLayout()[posY - 1][posX] == 'A')
                directionList.add(Direction.UP);
        }

        return directionList;
    }

    public void loseLife(GridPane grid) {
        pacman.livesLeft--;
        monsters.clear();

        Node node = getNodeByRowAndColumn(pacman.getPosY() + 2, pacman.getPosX(), grid);
        while (node != null) {
            grid.getChildren().remove(node);
            node = getNodeByRowAndColumn(pacman.getPosY() + 2, pacman.getPosX(), grid);
        }
        grid.add(new ImageView(pacDead7Img), pacman.getPosX(), pacman.getPosY() + 2, 1, 1);
        pacman.justLostLife = true;

    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getPoints() {
        return points;
    }

    public int getMovesCount() {
        return movesCount;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isJustLostLife() {
        return justLostLife;
    }

    public void setJustLostLife(boolean justLostLife) {
        this.justLostLife = justLostLife;
    }

    public int getLivesLeft() {
        return livesLeft;
    }

    public Direction getLastDirection() {
        return lastDirection;
    }

    public Direction getOppositeDirection() {
        switch (lastDirection) {
            case UP:
                return Direction.DOWN;
            case DOWN:
                return Direction.UP;
            case LEFT:
                return Direction.RIGHT;
            case RIGHT:
                return Direction.LEFT;
            default:
                return null;

        }
    }


    public void setLastDirection(Direction lastDirection) {
        this.lastDirection = lastDirection;
    }
}
