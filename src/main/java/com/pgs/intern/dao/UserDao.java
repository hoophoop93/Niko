package com.pgs.intern.dao;

import com.pgs.intern.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by kmichalik on 7/12/2016.
 */

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(User user) {
        entityManager.persist(user);
    }

    public boolean checkByEmail(String email) {
        String queryString = "SELECT count(o.email) FROM User o where LOWER(o.email) = :email";
        TypedQuery<Long> query = entityManager.createQuery(queryString, Long.class);
        query.setParameter("email", email.toLowerCase());

        return (query.getSingleResult() > 0);
    }

    public User findUser(String email) {
        String queryString = "Select o from User o where LOWER(o.email) = :email ";
        TypedQuery<User> query = entityManager.createQuery(queryString, User.class);
        query.setParameter("email", email.toLowerCase());
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

}
