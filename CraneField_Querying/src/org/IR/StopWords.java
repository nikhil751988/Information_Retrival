package org.IR;

/**
 * @author NIKHIL BHARADWAJ RAMESHA
 * This Class stores the list of stop words
 */

public class StopWords {

	public static String [] stopWords={"a", "all", "an", "and", "any", "are", "as", "at", "be", "been",
		"by", "but", "few", "for", "from", "have", "has", "he", "her", "here", "him", "his", "how", "i", "in", "is", "it", "its", "of",
		"many", "me", "my", "none", "of", "on", "or", "our", "she", "some", "that", "the", "to", "this", "there", "them", "they", "us", "was", "what", "were", 
		"with", "when", "where", "which", "who", "why", "will", "you", "your", "yours"};
	
	/**
	 * Returns true/false based on the whether the token 
	 * is stopword or not
	 * @param token
	 * @return
	 */
	
	public static boolean isStopWord(String token)
	{
		boolean stopWordFlag=false;
		for(String word : stopWords)
		{
			if(token.equalsIgnoreCase(word))
			{
				stopWordFlag=true;
				break;
			}
		}
		return stopWordFlag;
	}

}
