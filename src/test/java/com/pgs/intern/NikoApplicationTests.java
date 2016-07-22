package com.pgs.intern;

import com.pgs.intern.dao.UserDao;
import com.pgs.intern.models.Mood;
import com.pgs.intern.models.User;
import org.hibernate.Hibernate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NikoApplication.class)
@WebAppConfiguration
public class NikoApplicationTests {

	@Autowired
	private UserDao userDao;

	@Test
	@Transactional
	public void contextLoads() {
		User user = userDao.findById(4L);
	//	Hibernate.initialize(user.getMoodList());
		for(Mood mood : user.getMoodList())
			System.out.println(mood.getDateAdd() + " " + mood.getProject().getTitle() + " " + mood.getMoodType());
	}

}
