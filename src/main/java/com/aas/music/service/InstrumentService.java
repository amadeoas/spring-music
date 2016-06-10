package com.aas.music.service;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.aas.music.model.Instrument;


/**
 * Mostly used as a facade so all controllers have a single point of entry.
 *
 * @author Amadeo Asco
 */
public interface InstrumentService {

	/**
	 * Adds the instrument's details specified.
	 * 
	 * @param instrument the instrument's details.
	 * @return new instrument's ID.
	 * @throws DataAccessException when it has been an access problem.
	 */
	int add(Instrument instrument) throws DataAccessException;

	/**
	 * Adds the presets' details specified.
	 * 
	 * @param instruments the presets' details.
	 * @throws DataAccessException when it has been an access problem.
	 */
	void add(List<Instrument> instruments) throws DataAccessException;

	/**
	 * Deletes the instrument's details specified.
	 * 
	 * @param instrumentId the instrument's Id.
	 * @param type the instrument type.
	 * @throws DataAccessException when it has been an access problem.
	 */
	void delete(int instrumentId, String type) throws DataAccessException;

	/**
	 * Looks for the instrument with the specified ID.
	 * 
	 * @param instrumentId the instrument's ID.
	 * @param type the instrument type.
	 * @return the the instrument with the specified ID.
	 * @throws DataAccessException when it has been an access problem.
	 */
	Instrument findInstrument(int instrumentId, String type) throws DataAccessException;

	/**
	 * Looks for the instrument with the specified ID.
	 * 
	 * @param name the instrument's name.
	 * @param type the instrument type.
	 * @return the the instrument with the specified ID.
	 * @throws DataAccessException when it has been an access problem.
	 */
	Instrument findInstrument(String name, String type) throws DataAccessException;

	/**
	 * @return all the instrument details.
	 * @throws DataAccessException when it has been an access problem.
	 */
	Collection<Instrument> getAllInstruments() throws DataAccessException;

	/**
	 * Updates the instrument's details with those provided.
	 * 
	 * @param instrument the instrument's details.
	 * @throws DataAccessException when it has been an access problem.
	 */
	void update(Instrument instrument) throws DataAccessException;

}
