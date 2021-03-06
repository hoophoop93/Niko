package com.pgs.intern.models.moods.view;

/**
 * Created by kmichalik on 7/27/2016 9:37 AM.
 */
public class ProjectMoodsReport {
    private boolean noMoodsReported;
    private String owner;
    private String title;
    private DailyMoodReport[] dailyMoodReports;

    public boolean isNoMoodsReported() {
        return noMoodsReported;
    }

    public void setNoMoodsReported(boolean noMoodsReported) {
        this.noMoodsReported = noMoodsReported;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public DailyMoodReport[] getDailyMoodReports() {
        return dailyMoodReports;
    }

    public void setDailyMoodReports(DailyMoodReport[] dailyMoodReports) {
        this.dailyMoodReports = dailyMoodReports;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
