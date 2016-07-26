package com.pgs.intern.services;

import com.pgs.intern.dao.MoodDao;
import com.pgs.intern.dao.MoodDaoDataJpaInterface;
import com.pgs.intern.dao.UserDao;
import com.pgs.intern.models.Mood;
import com.pgs.intern.models.MoodViewModel;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;

/**
 * Created by Maciej Rosa on 7/26/2016 8:24 AM.
 */
public class MoodServiceTest {
    @Mock
    CurrentUser currentUser;

 //   @Mock
  //  MoodDao moodDao;
    @Mock
    private MoodDaoDataJpaInterface moodDao;


    @InjectMocks
    MoodService moodService;

    @Before
    public void initTestClasses(){
        moodService = new MoodService();
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void addMood(){
        MoodViewModel mvm = new MoodViewModel();
        moodService.addMood(mvm);
        verify(moodDao).save((Mood) anyObject());
    }

    @Test
    public void dateValidationTest(){
        Date date = DateUtils.addDays(new Date(), -8);
        assertFalse(moodService.isInLast7Days(date));
        date = DateUtils.addDays(new Date(),-7);
        assertTrue(moodService.isInLast7Days(date));
    }
}
