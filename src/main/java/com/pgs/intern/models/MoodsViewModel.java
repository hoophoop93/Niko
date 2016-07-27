package com.pgs.intern.models;

import java.util.Date;
import java.util.List;

/**
 * Created by kmichalik on 7/27/2016 8:43 AM.
 */
public class MoodsViewModel {
    private List<ProjectMoodsRaport> projectMoodsRaportList;

    public List<ProjectMoodsRaport> getProjectMoodsRaportList() {
        return projectMoodsRaportList;
    }

    public void setProjectMoodsRaportList(List<ProjectMoodsRaport> projectMoodsRaportList) {
        this.projectMoodsRaportList = projectMoodsRaportList;
    }


    static class ProjectMoodsRaport {

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


        static class DailyMoodReport {
            private String date;
            private List<MoodReport> moodRaports;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public List<MoodReport> getMoodRaports() {
                return moodRaports;
            }

            public void setMoodRaports(List<MoodReport> moodRaports) {
                this.moodRaports = moodRaports;
            }


            static class MoodReport {
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
