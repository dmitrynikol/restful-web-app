package com.dmitrynikol.dao;

import com.dmitrynikol.model.User;

/**
 * Interface for specific User entity because we should tell which entity is acceptable. 
 * 
 * @author Dmitry Nikolaenko
 *
 */
public interface UserDao extends GenericDao<User> {
	
}
