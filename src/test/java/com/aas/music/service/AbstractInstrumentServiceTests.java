package com.aas.music.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.aas.music.model.BaseEntity;
import com.aas.music.model.EqInstrument;
import com.aas.music.model.Instrument;
import com.aas.music.model.VocalCompressor;
import com.aas.music.service.InstrumentService;


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
public abstract class AbstractInstrumentServiceTests {

    @Autowired
    protected InstrumentService instrumentService;


    @Test
    public void shouldFindAllInstruments() {
        final Collection<Instrument> instruments 
        		= this.instrumentService.getAllInstruments();

        assertThat(instruments.size()).isEqualTo(3);
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

}
