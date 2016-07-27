package com.pgs.intern.models.moods.view;

import java.util.List;

/**
 * Created by kmichalik on 7/27/2016 9:39 AM.
 */
public class MoodsViewModel {
    private List<ProjectMoodsReport> projectMoodsReportList;

    public List<ProjectMoodsReport> getProjectMoodsReportList() {
        return projectMoodsReportList;
    }

    public void setProjectMoodsReportList(List<ProjectMoodsReport> projectMoodsReportList) {
        this.projectMoodsReportList = projectMoodsReportList;
    }
}
