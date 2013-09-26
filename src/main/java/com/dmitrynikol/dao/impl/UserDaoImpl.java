package com.dmitrynikol.dao.impl;

import org.springframework.stereotype.Repository;

import com.dmitrynikol.dao.UserDao;
import com.dmitrynikol.model.User;

/**
 * Implementation of Generic DAO.
 * 
 * @author Dmitry Nikolaenko
 */

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

}
