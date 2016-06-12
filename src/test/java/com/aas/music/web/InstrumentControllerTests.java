package com.aas.music.web;

import com.aas.music.model.EqInstrument;
import com.aas.music.model.Instrument;
import com.aas.music.model.VocalCompressor;
import com.aas.music.service.InstrumentService;
import com.aas.music.web.InstrumentsController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.*;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for {@link InstrumentsController}.
 *
 * @author Amadeo Asco
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/business-config.xml", "classpath:spring/tools-config.xml", "classpath:spring/mvc-core-config.xml"})
@WebAppConfiguration
@ActiveProfiles("jpa")
public class InstrumentControllerTests {

    @Mock
    private InstrumentService instrumentService;

	@InjectMocks
    private InstrumentsController instrumentsController;

    private MockMvc mockMvc;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
        		.standaloneSetup(this.instrumentsController)
        		.build();
    }

    @Test
    public void testInitList() throws Exception {
    	final Collection<Instrument> instruments = new ArrayList<>();

    	when(this.instrumentService.getAllInstruments()).thenReturn(instruments);
        this.mockMvc.perform(get("/instruments"))
            .andExpect(status().isOk())
            .andExpect(view().name("instruments/instrumentsList"))
            .andExpect(forwardedUrl("instruments/instrumentsList"));

        this.mockMvc.perform(get("/instruments")
        		.param("lang", InstrumentsController.DEFAULT_LANGUAGE))
        	.andExpect(status().isOk())
        	.andExpect(view().name("instruments/instrumentsList"))
        	.andExpect(forwardedUrl("instruments/instrumentsList"));
    }

    @Test
    public void testViewEqInstrument() throws Exception {
    	final int instrumentId = 2;
    	final EqInstrument instrument = new EqInstrument();

    	instrument.setId(instrumentId);
    	instrument.setName("Test");
    	when(this.instrumentService.findInstrument(instrumentId, instrument.getType())).thenReturn(instrument);
    	this.mockMvc.perform(get("/instruments/view/{instrumentId}_{type}", instrumentId, instrument.getType()))
            .andExpect(status().isOk())
            .andExpect(view().name("instruments/instrumentView"))
            .andExpect(forwardedUrl("instruments/instrumentView"));
    }

    @Test
    public void testViewVocalCompressor() throws Exception {
    	final int instrumentId = 2;
    	final VocalCompressor instrument = new VocalCompressor();

    	instrument.setId(instrumentId);
    	instrument.setName("Test");
    	when(this.instrumentService.findInstrument(instrumentId, instrument.getType())).thenReturn(instrument);
    	this.mockMvc.perform(get("/instruments/view/{instrumentId}_{type}", instrumentId, instrument.getType()))
            .andExpect(status().isOk())
            .andExpect(view().name("instruments/vcView"))
            .andExpect(forwardedUrl("instruments/vcView"));
    }

    @Test
    public void testAddViewEqInstrument() throws Exception {
    	this.mockMvc.perform(get("/instruments/addView/{instrumentId}_{type}", 2, EqInstrument.TYPE))
            .andExpect(status().isOk())
            .andExpect(view().name("instruments/instrumentAdd"))
            .andExpect(forwardedUrl("instruments/instrumentAdd"));
    }

    @Test
    public void testAddViewVocalCompressor() throws Exception {
    	this.mockMvc.perform(get("/instruments/addView/{instrumentId}_{type}", 2, VocalCompressor.TYPE))
            .andExpect(status().isOk())
            .andExpect(view().name("instruments/vcAdd"))
            .andExpect(forwardedUrl("instruments/vcAdd"));
    }

    @Test
    public void testEditEqInstrument() throws Exception {
    	this.mockMvc.perform(get("/instruments/edit/{instrumentId}_{type}", 2, EqInstrument.TYPE))
            .andExpect(status().isOk())
            .andExpect(view().name("instruments/instrumentEdit"))
            .andExpect(forwardedUrl("instruments/instrumentEdit"));
    }

    @Test
    public void testEditVocalCompressor() throws Exception {
    	this.mockMvc.perform(get("/instruments/edit/{instrumentId}_{type}", 2, VocalCompressor.TYPE))
            .andExpect(status().isOk())
            .andExpect(view().name("instruments/vcEdit"))
            .andExpect(forwardedUrl("instruments/vcEdit"));
    }

    @Test
    public void testDataEqInstrument() throws Exception {
    	final int instrumentId = 2;
    	final EqInstrument instrument = new EqInstrument();

    	instrument.setId(instrumentId);
    	instrument.setName("Test");
    	instrument.setEqSettings(new ArrayList<>());
    	testData(instrumentId, instrument);
    }

    @Test
    public void testDataVocalCompressor() throws Exception {
    	final int instrumentId = 1;
    	final VocalCompressor instrument = new VocalCompressor();

    	instrument.setId(instrumentId);
    	instrument.setName("Test");
    	testData(instrumentId, instrument);
    }

    @Test
    public void testAddVocalCompressor() throws Exception {
    	final VocalCompressor instrument = new VocalCompressor();

    	instrument.setName("Test");
    	testAdd(instrument);
    }

    @Test
    public void testAddEqInstrument() throws Exception {
    	final EqInstrument instrument = new EqInstrument();

    	instrument.setName("Test");
    	testAdd(instrument);
    }
    
    @Test
    public void testDelete() throws Exception {
    	testDelete_(2, EqInstrument.TYPE);

    	testDelete_(1, VocalCompressor.TYPE);
    }

    @Test
    public void testUpdateEqInstrument() throws Exception {
    	final EqInstrument instrument = new EqInstrument();

    	instrument.setId(2);
    	instrument.setName("Test");
    	doNothing().when(this.instrumentService).update(instrument);
    	this.mockMvc.perform(post("/instruments/update")
    			.content(asJsonString(instrument))
    			.contentType(MediaType.APPLICATION_JSON))
    		.andExpect(status().isOk())
    		.andExpect(view().name("/success"))
    		.andExpect(forwardedUrl("/success"));
    }

    @Test
    public void testUpdateVocalCompressor() throws Exception {
    	final VocalCompressor instrument = new VocalCompressor();

    	instrument.setId(2);
    	instrument.setName("Test");
    	doNothing().when(this.instrumentService).update(instrument);
    	this.mockMvc.perform(
    		post("/instruments/updateVC")
    			.content(asJsonString(instrument))
    			.contentType(MediaType.APPLICATION_JSON))
    		.andExpect(status().isOk())
    		.andExpect(view().name("/success"))
    		.andExpect(forwardedUrl("/success"));
    }

    public static String asJsonString(final Object obj) {
    	final Gson gson = new GsonBuilder().create();
    	
    	return gson.toJson(obj);
    }
    
    private void testData(final int instrumentId, final Instrument instrument) 
    		throws Exception {
    	when(this.instrumentService.findInstrument(instrumentId, instrument.getType()))
    			.thenReturn(instrument);
    	this.mockMvc.perform(
    			get("/instruments/data/{instrumentId}_{type}", instrumentId, instrument.getType()))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.id", is(instrumentId)))
    			.andExpect(jsonPath("$.type", is(instrument.getType())));
    }
    
    private void testDelete_(final int instrumentId, final String type) 
    		throws Exception {
    	final Collection<Instrument> instruments = new ArrayList<>();

    	doNothing().when(this.instrumentService).delete(instrumentId, type);
    	when(this.instrumentService.getAllInstruments()).thenReturn(instruments);
    	this.mockMvc.perform(get("/instruments/delete/{instrumentId}_{type}", instrumentId, type)
    			.param("lang", InstrumentsController.DEFAULT_LANGUAGE))
            .andExpect(status().isOk())
            .andExpect(view().name("instruments/instrumentsList"))
            .andExpect(forwardedUrl("instruments/instrumentsList"));
    }

    private void testAdd(final Instrument instrument) throws Exception {
    	final Integer value = 1;

    	when(this.instrumentService.add(Matchers.any(Instrument.class)))
    		.thenReturn(value);
    	this.mockMvc.perform(post(instrument instanceof EqInstrument 
    				? "/instruments/add" : "/instruments/addVC")
        		.content(asJsonString(instrument))
    			.contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(value.toString()))
            .andReturn();
    }

}
