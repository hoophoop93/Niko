package com.pgs.intern.models;

import java.util.List;

/**
 * Created by kmichalik on 7/27/2016 8:43 AM.
 */
public class MoodsViewModel {
    private List<ProjectMoodsReport> projectMoodsReportList;

    public List<ProjectMoodsReport> getProjectMoodsReportList() {
        return projectMoodsReportList;
    }

    public void setProjectMoodsReportList(List<ProjectMoodsReport> projectMoodsReportList) {
        this.projectMoodsReportList = projectMoodsReportList;
    }


    static public class ProjectMoodsReport {

        private String owner;
        private String projectTitle;
        private DailyMoodReport[] dailyMoodReports = new DailyMoodReport[7];

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

        public String getProjectTitle() {
            return projectTitle;
        }

        public void setProjectTitle(String projectTitle) {
            this.projectTitle = projectTitle;
        }


        static public class DailyMoodReport {
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


            static public class MoodReport {
                private String mood;
                private String displayName;


                public String getMood() {
                    return mood;
                }

                public void setMood(String mood) {
                    this.mood = mood;
                }

                public String getDisplayName() {
                    return displayName;
                }

                public void setDisplayName(String displayName) {
                    this.displayName = displayName;
                }
            }

        }
    }
}
