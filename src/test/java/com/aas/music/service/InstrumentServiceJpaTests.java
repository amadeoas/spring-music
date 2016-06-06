package com.aas.music.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Integration test using the jpa profile.
 *
 * @author Amadeo Asco
 * @see AbstractInstrumentServiceTests for more details.
 */
@ContextConfiguration(locations = {"classpath:spring/business-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("jpa")
public class InstrumentServiceJpaTests extends AbstractInstrumentServiceTests {

}
