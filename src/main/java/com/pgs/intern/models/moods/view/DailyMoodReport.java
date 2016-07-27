package com.pgs.intern.models.moods.view;

import java.util.List;

/**
 * Created by kmichalik on 7/27/2016 9:40 AM.
 */
public class DailyMoodReport {
    private boolean isWeekend;
    private String date;
    private List<MoodReport> moodReports;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<MoodReport> getMoodReports() {
        return moodReports;
    }

    public void setMoodReports(List<MoodReport> moodReports) {
        this.moodReports = moodReports;
    }
}
