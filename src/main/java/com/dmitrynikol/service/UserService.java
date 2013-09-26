package com.dmitrynikol.service;

import java.util.List;

import com.dmitrynikol.model.User;

/**
 * Service for User entity.
 * 
 * @author Dmitry Nikolaenko
 *
 */
public interface UserService {
	public User create(User user);
	public void delete(Integer id);
	public User update(User user);
	public User find(Integer id);
	public List<User> getAll();
	public Long count();
}
