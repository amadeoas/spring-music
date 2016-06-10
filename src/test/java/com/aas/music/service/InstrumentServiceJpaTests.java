package com.aas.music.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aas.music.model.BaseEntity;
import com.aas.music.model.EqInstrument;
import com.aas.music.model.EqSetting;
import com.aas.music.model.Instrument;
import com.aas.music.model.VocalCompressor;

/**
 * <p>Base class for {@link InstrumentService} integration tests. </p> <p> Subclasses should specify Spring context
 * configuration using {@link ContextConfiguration @ContextConfiguration} annotation </p> <p>
 * AbstractclinicServiceTests and its subclasses benefit from the following services provided by the Spring
 * TestContext Framework: </p> <ul> <li><strong>Spring IoC container caching</strong> which spares us unnecessary set up
 * time between test execution.</li> <li><strong>Dependency Injection</strong> of test fixture instances, meaning that
 * we don't need to perform application context lookups. See the use of {@link Autowired @Autowired} on the <code>{@link
 * AbstractInstrumentServiceTests#clinicService clinicService}</code> instance variable, which uses autowiring <em>by
 * type</em>. <li><strong>Transaction management</strong>, meaning each test method is executed in its own transaction,
 * which is automatically rolled back by default. Thus, even if tests insert or otherwise change database state, there
 * is no need for a teardown or cleanup script. <li>An {@link org.springframework.context.ApplicationContext
 * ApplicationContext} is also inherited and can be used for explicit bean lookup if necessary.</li> </ul>
 *
 * @author Amadeo Asco
 */
@ContextConfiguration(locations = {"classpath:spring/business-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("jpa")
public class InstrumentServiceJpaTests {

    @Autowired
    protected InstrumentService instrumentService;


    @Test
    public void testFindByName() {
    	Instrument instrument;

    	instrument = this.instrumentService.findInstrument("Kick Drum", EqInstrument.TYPE);
    	assertEquals("Kick Drum", instrument.getName());
    	instrument = this.instrumentService.findInstrument("Vocal Compressor", VocalCompressor.TYPE);
    	assertEquals("Vocal Compressor", instrument.getName());
    	instrument = this.instrumentService.findInstrument("Invalid", VocalCompressor.TYPE);
    	assertNull(instrument);
    }

    @Test
	public void testEqInstrumentAdd() {
		final EqInstrument instrument = new EqInstrument();

		instrument.setName("Test");
//		instrument.setEqSettings(eqSettings);
		this.instrumentService.add(instrument);
	}

    @Test(expected = DataIntegrityViolationException.class)
	public void testRepeatedEqInstrumentAdd() {
    	final EqInstrument newInstrument = new EqInstrument();

    	newInstrument.setName("Rock Male Vocal");
		this.instrumentService.add(newInstrument);
	}

	@Test
	public void testEqInstrumentAddList() {
		final List<Instrument> list = new ArrayList<>();
		
		for (int i = 0; i < 3; ++i) {
			final EqInstrument instrument = new EqInstrument();

			instrument.setName("Test" + i);
			list.add(instrument);
		}

		this.instrumentService.add(list);
	}

	@Test
	public void testVocalCompressorAdd() {
		// 
		final VocalCompressor instrument = new VocalCompressor();

		instrument.setName("Test");
		this.instrumentService.add(instrument);
	}

    @Test(expected = DataIntegrityViolationException.class)
	public void testRepeatedVocalCompressorAdd() {
    	final VocalCompressor newInstrument = new VocalCompressor();

    	newInstrument.setName("Vocal Compressor");
		this.instrumentService.add(newInstrument);
	}

	@Test
    public void shouldFindAllInstruments() {
        final Collection<Instrument> instruments 
        		= this.instrumentService.getAllInstruments();

        assertThat(instruments.size()).isEqualTo(7);
        for (final Instrument instrument : instruments) {
            assertThat(instrument).isNotNull();
        }
    }

    @Test
    public void shouldInstrument() {
    	int instrumentId = 2;
        Instrument instrument = this.instrumentService.findInstrument(instrumentId, 
        		EqInstrument.TYPE);

        assertThat(instrument).isNotNull();
        assertThat(((BaseEntity) instrument).getId()).isEqualTo(instrumentId);
        assertThat(instrument.getType()).isEqualTo(EqInstrument.TYPE);
        
    	instrumentId = 1;
        instrument = this.instrumentService.findInstrument(instrumentId, 
        		VocalCompressor.TYPE);

        assertThat(instrument).isNotNull();
        assertThat(((BaseEntity) instrument).getId()).isEqualTo(instrumentId);
        assertThat(instrument.getType()).isEqualTo(VocalCompressor.TYPE);
    }
    
    @Test
    public void testVocalCompressorUpdate() {
    	final String NAME = "Vocal Compressor";
    	final String NEW_NAME = "Vocal Compressor - new";
    	VocalCompressor instrument = (VocalCompressor) this.instrumentService.findInstrument(
    			NAME, VocalCompressor.TYPE);

    	instrument.setName(NEW_NAME);
    	this.instrumentService.update(instrument);
    	
    	instrument = (VocalCompressor) this.instrumentService.findInstrument(
    			instrument.getId(), VocalCompressor.TYPE);
    	assertEquals(NEW_NAME, instrument.getName());
    	
    	// Restore data
    	instrument.setName(NAME);
    	this.instrumentService.update(instrument);
    }
    
    @Test
    public void testEqInstrumentUpdate() {
    	final String NAME = "Rock Male Vocal";
    	final String NEW_NAME = "Rock Male Vocal - new";
    	final int INCREASE = 1;
    	EqInstrument instrument = (EqInstrument) this.instrumentService.findInstrument(
    			NAME, EqInstrument.TYPE);
    	Iterator<EqSetting> iter;
    	EqSetting eqSetting;
    	int gain;

    	instrument.setName(NEW_NAME);
    	iter = instrument.getEqSettings().iterator();
    	eqSetting = iter.next();
    	gain = eqSetting.getGain() + INCREASE;
    	eqSetting.setGain(gain);
    	this.instrumentService.update(instrument);
    	
    	instrument = (EqInstrument) this.instrumentService.findInstrument(
    			instrument.getId(), EqInstrument.TYPE);
    	assertEquals(NEW_NAME, instrument.getName());
    	iter = instrument.getEqSettings().iterator();
    	eqSetting = iter.next();
    	assertEquals(gain, eqSetting.getGain());

    	// Restore name
    	instrument.setName(NAME);
    	eqSetting.setGain(gain - INCREASE);
    	this.instrumentService.update(instrument);
    }
    
    @Test
    public void testVocalCompressorDelete() {
    	this.instrumentService.delete(1, VocalCompressor.TYPE);
    }
    
    @Test
    public void testEqInstrumentDelete() {
    	this.instrumentService.delete(1, EqInstrument.TYPE);
    }

}
