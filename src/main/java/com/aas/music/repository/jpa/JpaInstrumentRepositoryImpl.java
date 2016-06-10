package com.aas.music.repository.jpa;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

//import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;

import com.aas.music.model.BaseEntity;
import com.aas.music.model.EqSetting;
import com.aas.music.model.Instrument;
import com.aas.music.model.VocalCompressor;
import com.aas.music.model.EqInstrument;
import com.aas.music.repository.InstrumentRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * JPA implementation of the {@link InstrumentRepository} interface.
 *
 * @author Amadeo Asco
 */
@Repository
public class JpaInstrumentRepositoryImpl implements InstrumentRepository {

    @PersistenceContext
    private EntityManager em;


	@Override
	@Transactional
	public int add(final Instrument instrument) throws DataAccessException {
		this.em.persist(instrument);
		
		return ((BaseEntity) findByName(instrument.getName(), instrument.getType())).getId();
	}

	@Override
	@Transactional
	public void add(final List<Instrument> instruments) throws DataAccessException {
		for (final Instrument instrument : instruments) {
			this.em.persist(instrument);
		}
	}

	@Override
	@Transactional
	public void delete(final int instrumentId, final String type) 
			throws DataAccessException {
    	// Delete instrument
		final Query query;

		if (type.equals(EqInstrument.TYPE)) {
			query = this.em.createQuery(
					"DELETE FROM EqInstrument AS i "
				  + "WHERE i.id = :p");
		} else {
			query = this.em.createQuery(
	    			"DELETE FROM VocalCompressor AS i "
	    		  + "WHERE i.id = :p");
		}

    	query.setParameter("p", instrumentId).executeUpdate();
	}

	@Override
    @SuppressWarnings("unchecked")
    public Collection<Instrument> findAll() {
    	Query query = this.em.createQuery(
    			"SELECT i " 
    		  + "FROM EqInstrument i WHERE i.name <> 'Default' " 
    		  + "ORDER BY i.name");
    	final Collection<Instrument> list = query.getResultList();
    	Collection<Instrument> l;
    	
    	query = this.em.createQuery(
    			"SELECT i " 
    		  + "FROM VocalCompressor i WHERE i.name <> 'Default'");
    	l = query.getResultList();
    	list.addAll(l);
    	
    	return list;
    }

	@Override
	public Instrument findById(int instrumentId, final String type) throws DataAccessException {
		return EqInstrument.TYPE.equals(type) ? this.em.find(EqInstrument.class, instrumentId) 
				: this.em.find(VocalCompressor.class, instrumentId);
	}

	@Override
	public EqInstrument findDefault() throws DataAccessException {
		return (EqInstrument) findByName("Default", EqInstrument.TYPE);
	}

    /**
     * @param name the name.
     * @return the instrument with the specified name if exists or null otherwise.
     * @throws DataAccessException when failed to retrieve the instrument.
     */
	public Instrument findByName(final String name, final String type) 
			throws DataAccessException {
		try {
	    	final Query query;
	    	
	    	if (EqInstrument.TYPE.equals(type)) {
		    	query = this.em.createQuery(
		    			"SELECT i " 
		    		  + "FROM EqInstrument i "
		    		  + "WHERE i.name = '" + name + "'");
	    	} else {
		    	query = this.em.createQuery(
		    			"SELECT i " 
		    		  + "FROM VocalCompressor i "
		    		  + "WHERE i.name = '" + name + "'");
	    	}
	
	    	return (Instrument) query.getSingleResult();
		} catch (final NoResultException nre) {
    		// This is not an error as it may not exist yet
		}
		
		return null;
	}

	@Override
	@Transactional
	public void update(final Instrument instrument) throws DataAccessException {
    	if (EqInstrument.TYPE.equals(instrument.getType())) {
    		updateIt((EqInstrument) instrument);
    	} else {
    		updateIt((VocalCompressor) instrument);
    	}
	}

	private void updateIt(final EqInstrument instrument) throws DataAccessException {
    	Query query = this.em.createQuery(
    			"UPDATE EqInstrument "
    		  + "SET name = :n "
    		  + "WHERE id = :p" );

    	query.setParameter("n", instrument.getName())
    			.setParameter("p", instrument.getId())
    			.executeUpdate();
    	
    	for (final EqSetting eqSetting : instrument.getEqSettings()) {
    		query = this.em.createQuery(
        			"UPDATE EqSetting "
        		  + "SET instrument_id = :ii, active = :a, band_type = :bt, band_set = :bs, gain = :g, freq = :f, freq_units = :fu "
        		  + "WHERE id = :p" );
        	query.setParameter("fu", eqSetting.getFreqUnits().name())
        		.setParameter("f", eqSetting.getFrequency())
        		.setParameter("g", eqSetting.getGain())
        		.setParameter("bs", eqSetting.getBandSet().name())
        		.setParameter("bt", eqSetting.getBandType().name())
        		.setParameter("a", eqSetting.isActive())
        		.setParameter("ii", instrument.getId())
				.setParameter("p", eqSetting.getId())
				.executeUpdate();
    	}
	}

	public void updateIt(final VocalCompressor instrument) throws DataAccessException {
	    final Query query = this.em.createQuery(
    			"UPDATE VocalCompressor "
    		  + "SET name = :n, attack = :a, "
    		  + "release = :re, threshold = :t, ratio = :r, ratio_of = :ro, "
    		  + "presence = :pr, make_up = :mu "
    		  + "WHERE id = :p" );

    	query.setParameter("mu", instrument.getMakeUp())
    			.setParameter("pr", instrument.getPresence())
    			.setParameter("ro", instrument.getRatioOf())
    			.setParameter("r", instrument.getRatio())
    			.setParameter("t", instrument.getThreshold())
    			.setParameter("re", instrument.getRelease())
    			.setParameter("a", instrument.getAttack())
    			.setParameter("n", instrument.getName())
    			.setParameter("p", instrument.getId())
    			.executeUpdate();
	}

}
