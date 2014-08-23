package org.IR;

public class DocumentNumber {
	
	public static Integer docNumber;
	
	/**
	 * Set the value parsed from the document section
	 * @param value
	 */
	
	public static void setDocNumber(String value)
	{
		docNumber=Integer.parseInt(value);
	}
	
	/**
	 * Return the document number
	 * @return
	 */
	
	public static Integer getDocNumber()
	{
		return docNumber;
	}

}
