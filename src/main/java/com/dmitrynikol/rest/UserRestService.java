package com.dmitrynikol.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dmitrynikol.dto.Users;
import com.dmitrynikol.model.User;
import com.dmitrynikol.service.UserService;

/**
 * Represent a class that will be manipulated by different HTTP methods.
 * 
 * @author Dmitry Nikolaenko
 *
 */
@Controller
@RequestMapping("/users")
public class UserRestService {
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Users loadUsers() {
		Users users = new Users();
		users.setUsers(userService.getAll());
		return users;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public User updateUser(@PathVariable(value = "id") Integer id, @RequestBody User user) {
		return userService.update(user);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public User addUser(@RequestBody User user) {
		return userService.create(user);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteUser(@PathVariable(value = "id") Integer id) {
		userService.delete(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public User findUser(@PathVariable(value = "id") Integer id) {
		return userService.find(id);
	}
}