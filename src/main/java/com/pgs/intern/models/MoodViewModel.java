package com.pgs.intern.models;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by lschiffer on 7/14/2016.
 */
public class MoodViewModel {
    @NotNull(message = "Choose mood.")
    private MoodType moodType;

    @NotNull(message = "Choose date.")
    private Date dateAdd;

    @NotNull(message = "Choose project.")
    private Project projectId;

    public MoodType getMoodType() {
        return moodType;
    }

    public void setMoodType(MoodType moodType) {
        this.moodType = moodType;
    }

    public Date getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }

    public Project getProjectId() {
        return projectId;
    }

    public void setProjectId(Project projectId) {
        this.projectId = projectId;
    }
}