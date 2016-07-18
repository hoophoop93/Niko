package com.pgs.intern.services;

import com.pgs.intern.dao.MoodDao;
import com.pgs.intern.models.Mood;
import com.pgs.intern.models.MoodViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

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
}
