package com.kodilla;


import javafx.scene.image.Image;

public class ImageSet {
    private Image imageLeft;
    private Image imageRight;
    private Image imageUp;
    private Image imageDown;
    private Image imageVul;

    public ImageSet(Image imageLeft, Image imageRight, Image imageUp, Image imageDown) {
        this.imageLeft = imageLeft;
        this.imageRight = imageRight;
        this.imageUp = imageUp;
        this.imageDown = imageDown;
    }

    public ImageSet(Image imageLeft, Image imageRight, Image imageUp, Image imageDown, Image imageVul) {
        this.imageLeft = imageLeft;
        this.imageRight = imageRight;
        this.imageUp = imageUp;
        this.imageDown = imageDown;
        this.imageVul = imageVul;
    }

    public Image getImageLeft() {
        return imageLeft;
    }

    public Image getImageRight() {
        return imageRight;
    }

    public Image getImageUp() {
        return imageUp;
    }

    public Image getImageDown() {
        return imageDown;
    }

    public Image getImageVul() {
        return imageVul;
    }
}
