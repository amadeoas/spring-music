package com.aas.music.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.aas.music.model.EqInstrument;
import com.aas.music.model.Instrument;
import com.aas.music.repository.InstrumentRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Mostly used as a facade for all Instrument controllers
 * Also a placeholder for @Transactional and @Cacheable annotations.   
 *
 * @author Amadeo Asco
 */
@Service
public class InstrumentServiceImpl implements InstrumentService {

    private InstrumentRepository instrumentRepository;

    
    @Autowired
    public InstrumentServiceImpl(final InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

	@Override
	public int add(final Instrument instrument) throws DataAccessException {
		return this.instrumentRepository.add(instrument);
	}

	@Override
	public void add(final List<EqInstrument> instruments) throws DataAccessException {
		this.instrumentRepository.add(instruments);
	}

	@Override
	public void delete(final int instrumentId, final String type) throws DataAccessException {
		this.instrumentRepository.delete(instrumentId, type);
	}

	@Override
	public Instrument findInstrument(final int instrumentId, final String type) 
			throws DataAccessException {
		return this.instrumentRepository.findById(instrumentId, type);
	}

    @Override
    @Transactional(readOnly = true)
    public Collection<Instrument> getAllInstruments() throws DataAccessException {
    	return this.instrumentRepository.findAll();
    }

	@Override
	public void update(final Instrument instrument) throws DataAccessException {
		this.instrumentRepository.update(instrument);
	}

}
