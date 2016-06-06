package com.aas.music.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * Representation of an instrument of type vocal compressor.
 * 
 * @author aasco
 */
@Entity
@Table(name = "vocal_compressors")
public class VocalCompressor extends BaseEntity implements Instrument {
	
	public static final String TYPE = "vc";

	@Transient
	private final String type = TYPE;

    @Column(name = "name")
    @NotEmpty
	private String name;

	@Column(name = "mode")
    private String mode;

	@Column(name = "attack")
	private long attack;  // ms

	@Column(name = "release")
	private long release; // ms

	@Column(name = "threshold")
	private double threshold; // dB

	@Column(name = "ratio")
	private int ratio;

	@Column(name = "ratio_of")
	private int ratioOf;

	@Column(name = "presence")
	private double presence; // dB

	@Column(name = "make_up")
	private double makeUp; // dB


    @Override
    public String getName() {
		return this.name;
	}

	public long getAttack() {
		return this.attack;
	}

	public String getMode() {
		return this.mode;
	}

	public long getRelease() {
		return this.release;
	}

	public double getThreshold() {
		return this.threshold;
	}

	public long getRatio() {
		return this.ratio;
	}

	public long getRatioOf() {
		return this.ratioOf;
	}

	public double getPresence() {
		return this.presence;
	}

	public double getMakeUp() {
		return this.makeUp;
	}
    
    public String getType() {
    	return type;
    }

	public void setName(final String name) {
		this.name = name;
	}
	
	public void setAttack(final long attack) {
		this.attack = attack;
	}

	public void setMode(final String mode) {
		this.mode = mode;
	}

	public void setRelease(final long release) {
		this.release = release;
	}

	public void setThreshold(final double threshold) {
		this.threshold = threshold;
	}

	public void setRatio(final int ratio) {
		this.ratio = ratio;
	}

	public void setRatioOf(final int ratioOf) {
		this.ratioOf = ratioOf;
	}

	public void setPresence(final double presence) {
		this.presence = presence;
	}

	public void getMakeUp(final double makeUp) {
		this.makeUp = makeUp;
	}

} // end class VocalCompressor
