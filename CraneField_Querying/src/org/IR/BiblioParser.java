package org.IR;

/**
 *@author Nikhil Bharadwaj Ramesha 
 */

import java.util.ArrayList;
import java.util.StringTokenizer;

public class BiblioParser {
	
	/**
	 * Accepts the token and trims the special characters using certain conditions
	 * @param token
	 * @return
	 */
	
	public static String trimmer(String token)
	{
		String trimmedToken=null;
		
		if(token.matches("[0-9]+ *- *[0-9]+"))
		{
			String [] tokens=token.split("-");
			for(String tkn : tokens)
			{
				trimmedToken=tkn.replaceAll("[#%$!,.:;+ /()*\'\" ]","")+",";
			}
		}
		else
		{
			if(!(token.matches("[# .&\'\"]*[0-9]+")))
			{
				trimmedToken=token.replaceAll("[#$%!:,.; ()+/*&\'\" ]", "");
			}
			else
			{
				trimmedToken=token.replaceAll("[# ./&\'\"]", "").toLowerCase();
			}
		}
		return trimmedToken;
	}
	
	/**
	 * Accepts the line in Biblio section read from the parsed file and tokenizes
	 * @param readLine
	 * @return
	 */
	
	public static ArrayList<String> returnBiblioTokens(String readLine)
	{
		ArrayList<String> biblioTokenList=new ArrayList<String>();
		StringTokenizer lineTokenizer=new StringTokenizer(readLine,",");
		while(lineTokenizer.hasMoreTokens())
		{
			String foundToken=lineTokenizer.nextToken();
			if(foundToken.matches("[0-9]+ *- *[0-9]+"))
			{
				String [] tokens=trimmer(foundToken).split(",");
				for(String tkn : tokens)
				{
					biblioTokenList.add(tkn);
				}
			}
			else
			{
				if(!foundToken.matches("[.,$%#@!&;:\'\"+*]"))
				{
					biblioTokenList.add(trimmer(foundToken));
				}
			}
		}
		
		return biblioTokenList;
	}
}
