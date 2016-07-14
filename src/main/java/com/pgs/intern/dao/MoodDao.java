package com.pgs.intern.dao;

import com.pgs.intern.models.Mood;
import com.pgs.intern.models.Project;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by mzalucka on 7/14/2016.
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class MoodDao {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager() {
        this.entityManager = entityManager;
    }

    public void save(Mood mood) {
        entityManager.persist(mood);
    }
}
