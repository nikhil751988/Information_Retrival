package org.IR;

/**
 * @author NIKHIL BHARADWAJ RAMESHA
 * This is the class used to generate the Gamma and Delta Codes and write them to the
 * bit stream and send it to the ByteConverter class
 */

public class GammaDeltaCode {
	
	/**
	 * The method is used to generate GammaCode
	 * @param gap
	 * @return
	 */

	public static String eliasGammaCode(int gap)
	{
		String gammaCode;
		if(gap==1)
		{
			gammaCode=String.valueOf(0);
		}
		else
		{
			String dGap=Integer.toBinaryString(gap);
			dGap=dGap.substring(1, dGap.length());
			int gap_length=dGap.length();
			String unaryCode=null;
			for(int itr=0;itr<gap_length;itr++)
			{
				if(itr==0)
				{
					unaryCode="1";
				}
				else
				{
					unaryCode=unaryCode+"1";
				}
			}
			unaryCode=unaryCode+"0";
			gammaCode=unaryCode+dGap;
		}
		return gammaCode;
	}
	
	/**
	 * The method is used to generate delta code
	 * @param termFreq
	 * @return
	 */
	
	public static String eliasDeltaCode(int termFreq)
	{
		String deltaCode;
		if(termFreq==1)
		{
			deltaCode=String.valueOf(0);
		}
		else
		{
			String term_Freq=Integer.toBinaryString(termFreq);
			int term_length=term_Freq.length();
			String gammaCode=eliasGammaCode(term_length);
			term_Freq=term_Freq.substring(1, term_Freq.length());
			deltaCode=gammaCode+term_Freq;
		}
		return deltaCode;
	}
	
	/**
	 * Send the Gamma code the ByteConverter write method as bit stream
	 * @param btconv
	 * @param gammaCode
	 */
	
	public static void writeEliasGammaCode(ByteConverter btconv, String gammaCode)
	{
		char[] code=gammaCode.toCharArray();
		
		try
		{
			for(int pos=0;pos<code.length;pos++)
			{
				int bit=Character.getNumericValue(code[pos]);
				btconv.writeByte(bit);
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Send the delta code to the byte converter write method as bit stream
	 * @param btconv
	 * @param deltaCode
	 */
	
	public static void writeEliasDeltaCode(ByteConverter btconv, String deltaCode)
	{
		char [] code=deltaCode.toCharArray();
		
		try
		{
			for(int pos=0;pos<code.length;pos++)
			{
				int bit=Character.getNumericValue(code[pos]);
				btconv.writeByte(bit);
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}