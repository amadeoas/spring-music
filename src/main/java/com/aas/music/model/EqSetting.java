package com.aas.music.model;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * Representation of an EQ setting.
 * 
 * @author aasco
 */
@Entity
@Table(name = "eq_settings")
public class EqSetting extends BaseEntity {

	/**
	 * Band type.
	 */
	public enum BandType {
		LOW,
		LOW_MID,
		HI_MID,
		HI;
	} // end enumeration BandType


	/**
	 * The available set per specific type.
	 * 
	 * @author aasco
	 */
	public enum BandSet {
		PEAK(BandType.LOW, BandType.HI),
		SHELF(BandType.LOW, BandType.HI),
		HI(BandType.LOW_MID),
		LOW(BandType.LOW_MID),
		NONE(BandType.HI_MID);

		/**
		 * List of band types associated to this band set.
		 */
		private BandType[] bandTypes;

		
		/**
		 * Checks if the specified band type is associated to this band set.
		 * 
		 * @param bandType the band type.
		 * @return true if the specified band type is associated to this band 
		 * 			set, or false otherwise.
		 */
		public boolean isAssociatedTo(final BandType bandType) {
			for (final BandType type : this.bandTypes) {
				if (type == bandType) {
					return true;
				}
			}
			
			return false;
		}

		BandSet(final BandType... bandTypes) {
			this.bandTypes = bandTypes;
		}
		
		/**
		 * @param type the band type.
		 * @return all the valid band sets for the specified band type.
		 */
		public static Collection<BandSet> values(final BandType type) {
			final Collection<BandSet> sets = new ArrayList<>();

			for (final BandSet set : values()) {
				if (set.isAssociatedTo(type)) {
					sets.add(set);
				}
			}
			
			return sets;
		}
		
	} // end enumeration BandSet


	/**
	 * The valid units for the frequency.
	 * 
	 * @author aasco
	 */
	public enum FrequencyUnits {
		Hz(1l), // base units
		KHz(1000l),
		MHz(1000000l),
		GHz(1000000000l);
		
		
		/**
		 * The factor in relation to the base units, the Hz.
		 */
		private final long factor;


		FrequencyUnits(final long factor) {
			this.factor = factor;
		}

		/**
		 * @return the factor in relation to the base units, the Hz.
		 */
		public long factor() {
			return this.factor;
		}

	} // end enumeration FrequencyUnits


    @Column(name = "active")
	private boolean active;

	@Enumerated(EnumType.STRING)
    @Column(name = "band_type")
    @NotEmpty
	private BandType bandType;

	@Enumerated(EnumType.STRING)
    @Column(name = "band_set")
    @NotEmpty
	private BandSet bandSet;
    
	@Column(name = "gain")
	private int gain;

	@Column(name = "freq")
	private double frequency;

	@Enumerated(EnumType.STRING)
    @Column(name = "freq_units")
	@NotEmpty
	private FrequencyUnits freqUnits;


	/**
	 * @return true if the setting is on or false otherwise.
	 */
	public boolean isActive() {
		return this.active;
	}

	/**
	 * @return the set associated with this EQ setting.
	 */
	public BandType getBandType() {
		return this.bandType;
	}

	/**
	 * @return the set associated with this EQ setting.
	 */
	public BandSet getBandSet() {
		return this.bandSet;
	}

	/**
	 * @return the frequency associated with this EQ setting.
	 */
	public double getFrequency() {
		return this.frequency;
	}

	/**
	 * @return the units associated with the frequency.
	 */
	public FrequencyUnits getFreqUnits() {
		return this.freqUnits;
	}

	/**
	 * @return the gain associated with this EQ setting.
	 */
	public int getGain() {
		return this.gain;
	}
	
	/**
	 * @return list of valid band sets for the current band type.
	 */
	public Collection<BandSet> getValidBandSets() {
		return BandSet.values(this.bandType);
	}

	/**
	 * Sets this setting on or off.
	 * 
	 * @param active true if the setting is in use or false otherwise.
	 */
	public void setActive(final boolean active) {
		this.active = active;
	}

	/**
	 * Sets the band type.
	 * 
	 * @param bandType the band type.
	 * @throws InvalidParameterException when the passed band type is not valid for 
	 * 			the current band set.
	 */
	public void setBandType(final BandType bandType) 
			throws InvalidParameterException {
		if (bandType == null ) {
			throw new InvalidParameterException("Invalid band type; it is NULL");
		}
		if (this.bandSet != null && !this.bandSet.isAssociatedTo(bandType)) {
			throw new InvalidParameterException(
					  "Invalid band set '" + this.bandSet.name() 
					+ "' for the type '" + bandType.name() 
					+ "'");
		}
		this.bandType = bandType;
	}

	/**
	 * Sets the band set.
	 * 
	 * @param bandSet the band set for the specific band type.
	 * @throws InvalidParameterException when the passed band set is not valid for 
	 * 			the current band type.
	 */
	public void setBandSet(final BandSet bandSet) throws InvalidParameterException {
		if (bandSet == null) {
			throw new InvalidParameterException("Invalid band set; it is NULL");
		}
		if (this.bandType != null && !bandSet.isAssociatedTo(this.bandType)) {
			throw new InvalidParameterException(
					  "Invalid band set '" + bandSet.name() 
					+ "' for the type '" + this.bandType.name()
					+ "'");
		}
		this.bandSet = bandSet;
	}

	/**
	 * Sets the frequency for the given units.
	 * 
	 * @param frequency the frequency quantity for the existing units.
	 */
	public void setFrequency(final double frequency) {
		this.frequency = frequency;
	}

	/**
	 * Sets the units of the frequency.
	 * 
	 * @param freqUnits the units of the frequency.
	 */
	public void setFreqUnits(final FrequencyUnits freqUnits) 
			throws InvalidParameterException {
		if (freqUnits == null) {
			throw new InvalidParameterException("Missing frequency units");
		}
		if (this.freqUnits != null) {
			this.frequency = (this.freqUnits.factor() * this.frequency) / freqUnits.factor();
		}
		this.freqUnits = freqUnits;
	}

	/**
	 * Sets the gain associated with this EQ setting.
	 */
	public void setGain(final int gain) {
		this.gain = gain;
	}

} // end interface EqSettings
