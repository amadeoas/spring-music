package com.aas.music.model;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.security.InvalidParameterException;

import org.junit.Test;

import com.aas.music.model.EqSetting.BandSet;
import com.aas.music.model.EqSetting.BandType;
import com.aas.music.model.EqSetting.FrequencyUnits;


/**
 * Simple test to make sure that EqSetting is working.
 *
 * @author Amadeo Asco
 */
public class EqSettingTests {

	@Test
	public void test() {
		EqSetting eqSetting = new EqSetting();
		
		eqSetting.setId(1);
		assertEquals((Integer) 1, eqSetting.getId());
		assertEquals(0, eqSetting.getValidBandSets().size());

		eqSetting.setActive(true);
		assertEquals(true, eqSetting.isActive());
		eqSetting.setActive(true);
		assertEquals(true, eqSetting.isActive());
		eqSetting.setBandSet(BandSet.LOW);
		assertEquals(BandSet.LOW, eqSetting.getBandSet());
		eqSetting.setBandType(BandType.LOW_MID);
		assertEquals(BandType.LOW_MID, eqSetting.getBandType());
		eqSetting.setFrequency(2.1);
		assertEquals(2.1, eqSetting.getFrequency(), 0.01);
		eqSetting.setFreqUnits(FrequencyUnits.GHz);
		assertEquals(FrequencyUnits.GHz, eqSetting.getFreqUnits());

		eqSetting.setFreqUnits(FrequencyUnits.MHz);
		assertEquals(FrequencyUnits.MHz, eqSetting.getFreqUnits());
		assertEquals(2.1 * 1000, eqSetting.getFrequency(), 0.01);
		
		assertEquals(0, BandSet.values(null).size());
		assertEquals(2, BandSet.values(BandType.LOW).size());
		
		eqSetting = new EqSetting();
		eqSetting.setBandType(BandType.LOW_MID);
		assertEquals(BandType.LOW_MID, eqSetting.getBandType());
		eqSetting.setBandSet(BandSet.LOW);
		assertEquals(BandSet.LOW, eqSetting.getBandSet());
	}
	
	@Test
	public void testBandTypeException() {
		assertThatThrownBy(() -> new  EqSetting().setBandType(null))
        	.isInstanceOf(InvalidParameterException.class);
	}

	@Test
	public void testInvalidBandTypeException() {
		final EqSetting eqSetting = new EqSetting();

		eqSetting.setBandSet(BandSet.LOW);
		assertThatThrownBy(() -> eqSetting.setBandType(BandType.LOW))
        	.isInstanceOf(InvalidParameterException.class);
	}
	
	@Test
	public void testBandSetException() {
		assertThatThrownBy(() -> new  EqSetting().setBandSet(null))
    		.isInstanceOf(InvalidParameterException.class);
	}

	@Test
	public void testInvalidBandSetException() {
		final EqSetting eqSetting = new EqSetting();

		eqSetting.setBandType(BandType.LOW);
		assertThatThrownBy(() -> eqSetting.setBandSet(BandSet.LOW))
    		.isInstanceOf(InvalidParameterException.class);
	}
	
	@Test
	public void testFreqUnitsException() {
		assertThatThrownBy(() -> new  EqSetting().setFreqUnits(null))
    		.isInstanceOf(InvalidParameterException.class);
	}

}
