package com.dmitrynikol.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.dmitrynikol.dao.GenericDao;

/**
 * Implementation of Generic DAO. 
 * 
 * @author Dmitry Nikolaenko
 *
 * @param <T> The type of the domain object for which this instance is to be used.
 */
public abstract class GenericDaoImpl<T extends Serializable> implements GenericDao<T> {
	
	private static final Logger logger = Logger.getLogger(GenericDaoImpl.class);
	
	// parametrized type of a concrete class
	private Class<T> type;
	
	/**
	 * Represents a JPA connection to the object database, we can uses 
	 * it for CRUD operations (for interacting with the persistence context)
	 */
	@PersistenceContext
	protected EntityManager manager;
	
	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class<T>) pt.getActualTypeArguments()[0];
	}
	
	@Override
	public long count() {
		String entity = type.getSimpleName();
		final StringBuilder queryString = 
				new StringBuilder("select count(ent) from ".concat(entity).concat(" ent"));
		// create an instance of Query for executing a Java Persistence query language statement
		final Query query = manager.createQuery(queryString.toString());
		// execute a SELECT query that returns a single untyped result
		return (Long) query.getSingleResult();
	}
	
	@Override
	public T find(final Object id) {
		return manager.find(type, id);
	}
	
	@Override
	public T create(T t) {
		manager.persist(t);
		return t;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		Query query = manager.createQuery("from ".concat(type.getName()));
		return query.getResultList();
	}
	
	@Override
	public T update(T t) {
		return manager.merge(t);
	}
	
	@Override
	public void delete(Object id) {
		manager.remove(manager.getReference(type, id));
	}
}