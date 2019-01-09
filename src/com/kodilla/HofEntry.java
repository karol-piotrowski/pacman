package com.kodilla;

import java.io.Serializable;

public class HofEntry implements Serializable {
    private String initials;
    private int maxGhosts;
    private int bonusDuration;
    private int score;

    public HofEntry(String initials, int maxGhosts, int bonusDuration, int score) {
        this.initials = initials;
        this.maxGhosts = maxGhosts;
        this.bonusDuration = bonusDuration;
        this.score = score;
    }

    public String getInitials() {
        return initials;
    }

    public int getMaxGhosts() {
        return maxGhosts;
    }

    public int getBonusDuration() {
        return bonusDuration;
    }

    public int getScore() {
        return score;
    }

}
