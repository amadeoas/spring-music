package com.aas.music.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.aas.music.web.InstrumentsController;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


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

    @Autowired
    private InstrumentsController instrumentsController;

    private MockMvc mockMvc;


    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.instrumentsController).build();
    }

    @Test
    public void testInitList() throws Exception {
        this.mockMvc.perform(get("/instruments"))
            .andExpect(status().isOk())
            .andExpect(view().name("instruments/instrumentsList"))
            .andExpect(forwardedUrl("instruments/instrumentsList"));
    }

//    @Test
//    public void testAddVocalCompressor() throws Exception {
//    	final VocalCompressor instrument = new VocalCompressor();
//    	int result;
//
//    	instrument.setName("Test");
//    	result = (Integer) this.mockMvc.perform(post("/add")
//        		.content(asJsonString(instrument))
//    			.contentType(MediaType.APPLICATION_JSON)
//    			.accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isOk())
//            .andReturn().getAsyncResult();
//    	assertTrue(result > 0);
//    }
//
//    @Test
//    public void testAddEqInstrument() throws Exception {
//    	final EqInstrument instrument = new EqInstrument();
//    	int result;
//
//    	instrument.setName("Test");
//    	result = (Integer) this.mockMvc.perform(post("/add")
//        		.content(asJsonString(instrument))
//    			.contentType(MediaType.APPLICATION_JSON)
//    			.accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isOk())
//            .andReturn().getAsyncResult();
//    	assertTrue(result > 0);
//    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);

            return jsonContent;
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

}
