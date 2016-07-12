package com.pgs.intern.dao;

import com.pgs.intern.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by kmichalik on 7/12/2016.
 */
@Repository("UserDaoImpl")
@Transactional(propagation = Propagation.REQUIRED)
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager() {
        this.entityManager = entityManager;
    }

    public void save(User user) {
        entityManager.persist(user);
    }


}
