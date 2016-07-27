package com.pgs.intern.dao;

import com.pgs.intern.models.Mood;
import com.pgs.intern.models.Project;
import com.pgs.intern.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by lschiffer on 7/26/2016.
 */
public interface MoodDaoDataJpaInterface extends CrudRepository<Mood, Long> {
    @Query("select count(mood.dateAdd) > 0 from Mood mood where mood.user = ?1 and mood.project = ?2 and mood.dateAdd = ?3")
    boolean checkDayMood(User user, Project project, Date dateAdd);

    @Query("select mood from Mood mood where mood.project = ?1 and mood.dateAdd = ?2")
    List<Mood> findAllMoodsInProjectAndDate(Project project,Date date);
}