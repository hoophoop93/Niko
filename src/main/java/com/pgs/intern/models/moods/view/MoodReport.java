package com.pgs.intern.models.moods.view;

import com.pgs.intern.models.MoodType;

/**
 * Created by kmichalik on 7/27/2016 9:41 AM.
 */
public class MoodReport {
    private MoodType mood;
    private String displayName;


    public MoodType getMood() {
        return mood;
    }

    public void setMood(MoodType mood) {
        this.mood = mood;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
