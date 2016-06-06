package com.aas.music.model;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * Representation of an instrument with its QE settings.
 * 
 * @author aasco
 */
@Entity
@Table(name = "instruments")
public class EqInstrument extends BaseEntity implements Instrument {
	
	public static final String TYPE = "ei";

	@Transient
	private final String type = TYPE;

    @Column(name = "name")
    @NotEmpty
	private String name;

    @JoinColumn(name="instrument_id")
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@ElementCollection(targetClass=EqSetting.class)
    private Collection<EqSetting> eqSettings;

	
	/**
	 * @return read-only collection of all EQ settings.
	 */
	public Collection<EqSetting> getEqSettings() {
		return Collections.unmodifiableCollection(this.eqSettings);
	}

    @Override
    public String getName() {
		return this.name;
	}
    
    public String getType() {
    	return this.type;
    }
	
	/**
	 * @return read-only collection of all EQ settings.
	 */
	public void setEqSettings(final Collection<EqSetting> eqSettings) {
		this.eqSettings = Collections.unmodifiableCollection(eqSettings);
	}

	public void setName(final String name) {
		this.name = name;
	}

} // end class Instrument
