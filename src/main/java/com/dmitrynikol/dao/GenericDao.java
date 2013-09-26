package com.dmitrynikol.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Interface for a Data Access Object that can be used for a single specified
 * type domain object. A single instance implementing this interface can be used
 * only for the type of domain object specified in the type parameters.
 * 
 * @author Dmitry Nikolaenko
 *
 * @param <T> The type of the domain object for which this instance is to be used.
 */
public interface GenericDao<T extends Serializable> {
	
	/**
	 * Returns the total number of results
	 */
	public long count();
	
	/**
	 * Create and save new object in DB.
	 */
	public T create(T t);
	
	/**
	 * Remove the entity with the specified type and id from the datastore.
	 */
	public void delete(Object id);
	
	/**
	 * Get the entity with the specified type and id from the datastore.
	 * 
	 * @param id of the entity.
	 * @return If none is found, return null.
	 */
	public T find(Object id);
	
	/**
	 * Get a list of all the objects of the specified type.
	 */
	public List<T> getAll();
	
	/**
	 * Update object in DB.
	 */
	public T update(T t);
}