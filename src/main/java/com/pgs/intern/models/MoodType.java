package com.pgs.intern.models;

/**
 * Created by kmichalik on 7/14/2016.
 */
public enum MoodType {
    Happy("\uD83D\uDE04"),
    Neutral("\uD83D\uDE10"),
    Bad("\uD83D\uDE2B");


    private final String text;

    private MoodType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public String getLowerCase() {
        if (this == MoodType.Happy) {
            return "happy";
        }

        if (this == MoodType.Neutral) {
            return "neutral";
        }

        if (this == MoodType.Bad) {
            return "bad";
        }

        return "";
    }
}