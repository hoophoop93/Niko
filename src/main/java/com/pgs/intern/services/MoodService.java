package com.pgs.intern.services;

import com.pgs.intern.dao.MoodDaoDataJpaInterface;
import com.pgs.intern.dao.ProjectDaoJpa;
import com.pgs.intern.models.Mood;
import com.pgs.intern.models.MoodViewModel;
import com.pgs.intern.models.Project;
import com.pgs.intern.models.moods.view.DailyMoodReport;
import com.pgs.intern.models.moods.view.MoodReport;
import com.pgs.intern.models.moods.view.MoodsViewModel;
import com.pgs.intern.models.moods.view.ProjectMoodsReport;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Maciej Rosa on 7/15/2016 12:53 PM.
 */
@Component
public class MoodService {
    @Autowired
    private MoodDaoDataJpaInterface moodDao;

    public final int DAYS = 8;


    @Autowired
    private ProjectDaoJpa projectDaoJpa;

    @Inject
    private CurrentUser currentUser;

    public void addMood(MoodViewModel moodViewModel){
        Mood mood = new Mood();
        mood.setDateAdd(moodViewModel.getDateAdd());
        mood.setProject(moodViewModel.getProject());
        mood.setUser(currentUser.getUser());
        mood.setMoodType(moodViewModel.getMoodType());
        moodDao.save(mood);
    }

    public boolean isInLast7Days(Date date){
        Date sevenDaysAgo = DateUtils.addDays(DateUtils.truncate(new Date(), Calendar.DATE),-7);
        return sevenDaysAgo.compareTo(DateUtils.truncate(date,Calendar.DATE)) <= 0;
    }

    private int getDaysAgoCount(Date date){
        DateTime dateTime = new DateTime(date);
        return Math.abs(Days.daysBetween(dateTime,new DateTime()).getDays());
    }


    public MoodsViewModel getMoodsViewModel(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date today = DateUtils.truncate(new Date(),Calendar.DATE);
        Date daysAgo = DateUtils.addDays(today,-7);


        List<ProjectMoodsReport> projectMoodsReports = new ArrayList<>();
        for(Project p : projectDaoJpa.getUserProjects(currentUser.getUser())){
            ProjectMoodsReport report = new ProjectMoodsReport();
            report.setTitle(p.getTitle());
            report.setOwner(p.getOwner().getDisplayName());
            DailyMoodReport[] dailyReports = new DailyMoodReport[DAYS];

            for(int i = 0; i < DAYS; i++){
                dailyReports[i] = new DailyMoodReport();
                dailyReports[i].setMoodReports(new ArrayList<>());
                dailyReports[i].setDate(simpleDateFormat.format(DateUtils.addDays(today,i-DAYS+1)));
                dailyReports[i].setWeekend(false);
            }
            for(Mood mood : moodDao.findAllMoodsInProjectAndDate(p,daysAgo,today)){
                MoodReport moodReport = new MoodReport();
                moodReport.setMood(mood.getMoodType());
                moodReport.setDisplayName(mood.getUser().getDisplayName());
                int day = DAYS - getDaysAgoCount(mood.getDateAdd()) - 1;
                if(day >= 0)
                    dailyReports[day].getMoodReports().add(moodReport);
            }
            report.setDailyMoodReports(dailyReports);
            projectMoodsReports.add(report);
        }
        MoodsViewModel moodsViewModel = new MoodsViewModel();
        moodsViewModel.setProjectMoodsReportList(projectMoodsReports);
        return moodsViewModel;
    }
}
