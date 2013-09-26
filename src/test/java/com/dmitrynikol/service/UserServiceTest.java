package com.dmitrynikol.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dmitrynikol.model.User;
import com.dmitrynikol.service.UserService;

/**
 * Unit test for the client service layer.
 * 
 * @author Dmitry Nikolaenko
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"/spring-beans-test.xml", 
		"/servlet-context-test.xml" })
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void createTest() {
		User user = userService.create(createTestUser());
		
		Assert.assertNotNull(user);
		Assert.assertNotNull(user.getId());
		Assert.assertEquals("Tom", user.getName());
	}
	
	@Test
	public void deleteTest() {
		User user = userService.create(createTestUser());
		userService.delete(user.getId());
		User deletedUser = userService.find(user.getId());
		
		Assert.assertNull(deletedUser);
	}
	
	@Test
	public void updateTest() {
		User user = userService.create(createTestUser());
		user.setName("Bob");
		userService.update(user);
		User foundUser = userService.find(user.getId());
		
		Assert.assertNotNull(foundUser);
		Assert.assertEquals("Bob", foundUser.getName());
	}
	
	@Test
	public void findTest() {
		User user = userService.create(createTestUser());
		User foundUser = userService.find(user.getId());
		
		Assert.assertNotNull(foundUser);
		Assert.assertNotNull(foundUser.getId());
		Assert.assertEquals("Tom", foundUser.getName());
	}
	
	@Test
	public void getAllTest() {
		userService.create(createTestUser());
		List<User> users = userService.getAll();
		
		Assert.assertNotNull(users);
		Assert.assertFalse(users.isEmpty());
	}
	
	@Test
	public void countTest() {
		userService.create(createTestUser());
		long before = userService.count();
		userService.create(createTestUser());
		long after = userService.count();
		
		Assert.assertEquals(before, after - 1);
	}
	
	/**
	 * Create a test user.
	 */
	private User createTestUser() {
		User user = new User();
		user.setAge(30);
		user.setName("Tom");
		
		return user;
	}
}
