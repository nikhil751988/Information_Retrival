package org.IR;

import java.io.Serializable;

/**
 * @author NIKHIL BHARADWAJ RAMESHA
 * This class is used to hold the compressed posting list for each index
 */

@SuppressWarnings("serial")
public class CompressedPosting implements Serializable{
	
	public byte [] gap;
	
	public byte [] term_Freq;
	
	/**
	 * Constructor for the class
	 * @param gap
	 * @param tFreq
	 */
	
	public CompressedPosting(byte [] gap, byte [] tFreq)
	{
		this.gap=gap;
		this.term_Freq=tFreq;
	}

}
