package com.dmitrynikol;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.dmitrynikol.dto.Users;
import com.dmitrynikol.model.User;

/**
 * Integration test.
 * 
 * @author Dmitry Nikolaenko
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"/spring-beans-test.xml", 
		"/servlet-context-test.xml" })
public class UserRestServiceTest {
	
	private static final String REST_APP_URL = "http://localhost:8080/RESTfulWebApp/rest/users";
	private RestTemplate restTemplate = new RestTemplate();
			
	@Test
	public void addUserTest() {
		// create a new resource by POSTing the given object to the URI template, 
		// and returns the representation found in the response. 
		User createdUser = restTemplate.postForObject(REST_APP_URL, createUser(), User.class);
		
		Assert.assertNotNull(createdUser);
		Assert.assertEquals("Tom", createdUser.getName());
		Assert.assertNotNull(createdUser.getId());
	}
	
	@Test
	public void findUserTest() {
		User createdUser = restTemplate.postForObject(REST_APP_URL, createUser(), User.class);
		
		// retrieve a representation by doing a GET on the specified URL, 
		// the response is converted and returned. 
		User userFound = restTemplate.getForObject(createRestAppUrl(createdUser), User.class);
		Assert.assertNotNull(userFound);
		Assert.assertEquals("Tom", userFound.getName());
		Assert.assertNotNull(userFound.getId());
	}
	
	@Test
	public void updateUserTest() {
		User createdUser = restTemplate.postForObject(REST_APP_URL, createUser(), User.class);
		
		createdUser.setName("Bob");
		// update a resource by PUTing the given user to the URI
		restTemplate.put(createRestAppUrl(createdUser), createdUser);
		User userFound = restTemplate.getForObject(createRestAppUrl(createdUser), User.class);
		
		Assert.assertNotNull(userFound);
		Assert.assertEquals("Bob", userFound.getName());
	}
	
	@Test
	public void loadUsersTest() {
		restTemplate.postForObject(REST_APP_URL, createUser(), User.class);
		Users users = (Users) restTemplate.getForObject(REST_APP_URL, Users.class);
		
		Assert.assertNotNull(users);
		Assert.assertNotNull(users.getUsers());
		Assert.assertFalse(users.getUsers().isEmpty());
	}
	
	@Test
	public void deleteUserTest() {
		// just create new user
		User createdUser = restTemplate.postForObject(REST_APP_URL, createUser(), User.class);
		
		// testing...
		Assert.assertNotNull(createdUser);
		Assert.assertEquals("Tom", createdUser.getName());
		Assert.assertNotNull(createdUser.getId());
		
		// try to find it and then testing...
		User userFound = restTemplate.getForObject(createRestAppUrl(createdUser), User.class);
		Assert.assertNotNull(userFound);
		Assert.assertEquals("Tom", userFound.getName());
		Assert.assertNotNull(userFound.getId());
		
		// delele it
		restTemplate.delete(createRestAppUrl(userFound));
		
		// and finally try to find again, but we lost him forever
		User deletedUser = restTemplate.getForObject(createRestAppUrl(createdUser), User.class);
		Assert.assertNull(deletedUser);
	}
	
	/**
	 * Create a test user.
	 */
	private User createUser() {
		User user = new User();
		user.setAge(30);
		user.setName("Tom");
		
		return user;
	}
	
	/**
	 * Construct REST URL for testing.
	 * 
	 * @param user for testing
	 */
	private String createRestAppUrl(User user) {
		return REST_APP_URL.concat("/").concat(user.getId().toString());
	}
}