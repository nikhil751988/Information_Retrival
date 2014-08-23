package org.IR;

import java.io.Serializable;

/**
 * @author NIKHIL BHARADWAJ RAMESHA
 * This class is used to store the Posting for each document
 */

@SuppressWarnings("serial")
public class Postings implements Serializable{
	
	public int gap;
	
	public int term_Freq;
	
	public int docId;
	
	/**
	 * Constructor used when it is the first posting in the document
	 * @param docId
	 * @param tFreq
	 */
	
	public Postings(int docId, int tFreq)
	{
		this.docId=docId;
		this.term_Freq=tFreq;
		this.gap=docId;
	}
	
	/**
	 * Constructor called upon when otherwise
	 * Gap between the documents are computed and stored here
	 * @param gap
	 * @param tFreq
	 * @param docId
	 */
	
	public Postings(int gap, int tFreq, int docId)
	{
		this.gap=gap;
		this.term_Freq=tFreq;
		this.docId=docId;
	}
	
	/**
	 * Returns the Gap Value
	 * @return
	 */
	
	public int returnGap()
	{
		return this.gap;
	}
	
	/**
	 * Return the Document ID
	 * @return
	 */
	
	public int returnDocId()
	{
		return this.docId;
	}
	
	/**
	 * Increments the term frequency
	 */
	
	public void setTermFreq()
	{
		this.term_Freq=this.term_Freq+1;
	}
	
	/**
	 * Returns the term  frequency
	 * @return
	 */
	
	public int returnTermFreq()
	{
		return this.term_Freq;
	}

}
