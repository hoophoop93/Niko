package com.pgs.intern.models;

/**
 * Created by kmichalik on 7/14/2016.
 */
public enum MoodType {
    Happy("\uD83D\uDE0C"),
    Neutral("\uD83D\uDE10"),
    Bad("\uD83D\uDE41");

    private final String text;

    private MoodType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}