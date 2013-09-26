package com.dmitrynikol.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmitrynikol.dao.UserDao;
import com.dmitrynikol.model.User;
import com.dmitrynikol.service.UserService;

/**
 * Implementation of the UsersService.
 * 
 * @author Dmitry Nikolaenko
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	@Transactional(readOnly = false)
	public User create(User user) {
		return userDao.create(user);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Integer id) {
		userDao.delete(id);
	}

	@Override
	@Transactional(readOnly = false)
	public User update(User user) {
		return userDao.update(user);
	}

	@Override
	@Transactional(readOnly = false)
	public User find(Integer id) {
		return userDao.find(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> getAll() {
		return userDao.getAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return userDao.count();
	}
}