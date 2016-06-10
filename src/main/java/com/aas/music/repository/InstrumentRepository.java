package com.aas.music.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.aas.music.model.Instrument;


/**
 * Repository class for <code>Instrument</code> domain objects. All method names 
 * are compliant with Spring Data naming conventions so this interface can 
 * easily be extended for Spring Data, see here: 
 * http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 * @author Amadeo Asco
 */
public interface InstrumentRepository {

	/**
	 * Adds the specified ustomer's details.
	 * 
	 * @param instrument the instrument's details.
	 * @return the new instrument's ID.
	 * @throws DataAccessException when the data access failed.
	 */
	int add(Instrument instrument) throws DataAccessException;
	
	/**
	 * Adds the specified ustomer's details.
	 * 
	 * @param presets the presets' details.
	 * @throws DataAccessException when the data access failed.
	 */
	void add(List<Instrument> presets) throws DataAccessException;

	/**
	 * Deletes the specified instrument's details.
	 * 
	 * @param instrumentId the instrument's ID.
	 * @param type the instrument type.
	 * @throws DataAccessException when the data access failed.
	 */
	void delete(int instrumentId, String type) throws DataAccessException;

    /**
     * Retrieves all <code>Instrument</code>s from the data store.
     *
     * @return a <code>Collection</code> of <code>Instrument</code>s.
	 * @throws DataAccessException when the data access failed.
     */
    Collection<Instrument> findAll() throws DataAccessException;

    /**
     * Retrieves an <code>Instrument</code> from the data store by id.
     *
     * @param id the id to search for.
	 * @param type the instrument type.
     * @return the <code>Instrument</code> if found.
	 * @throws DataAccessException when the data access failed.
     */
    Instrument findById(int id, String type) throws DataAccessException;

    /**
     * Retrieves the default <code>Instrument</code> from the data store by name.
     *
     * @param type the data type.
     * @return the <code>Instrument</code> if found.
	 * @throws DataAccessException when the data access failed.
     */
    Instrument findDefault(String type) throws DataAccessException;

    /**
     * Retrieves an <code>Instrument</code> from the data store by name.
     *
     * @param name the name to search for.
     * @param type the data type.
     * @return the <code>Instrument</code> if found.
	 * @throws DataAccessException when the data access failed.
     */
    Instrument findByName(String name, String type) throws DataAccessException;

	/**
	 * Saves the new instrument's details provided.
	 * 
	 * @param instrument the instrument details.
	 * @throws DataAccessException when the data access failed.
	 */
	void update(Instrument instrument) throws DataAccessException;

}
