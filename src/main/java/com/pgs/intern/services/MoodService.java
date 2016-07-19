package com.pgs.intern.services;

import com.pgs.intern.dao.MoodDao;
import com.pgs.intern.models.Mood;
import com.pgs.intern.models.MoodViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.time.DateUtils;
import javax.inject.Inject;
import java.util.Date;
import java.util.Calendar;

/**
 * Created by Maciej Rosa on 7/15/2016 12:53 PM.
 */
@Component
public class MoodService {
    @Autowired
    private MoodDao moodDao;

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

}
