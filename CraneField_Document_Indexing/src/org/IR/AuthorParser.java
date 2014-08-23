package org.IR;

/**
 * @author Nikhil Bharadwaj Ramesha
 */

import java.util.ArrayList;


public class AuthorParser {
	
	/**
	 * Accepts the token and trims the special characters using certain conditions
	 * @param token
	 * @return
	 */
	
	public static String trimmer(String token)
	{
		return token.replaceAll("[!,.:# /()\'\"-]", "").toLowerCase();
	}
	
	/**
	 * Accepts the line in Title section read from the parsed file and tokenizes
	 * @param readLine
	 * @return
	 */
	
	public static ArrayList<String> returnAuthorTokens(String readLine)
	{
		ArrayList<String> authorTokenizedList=new ArrayList<String>();
		String [] lineTokenizer=null;
		if(readLine.contains("&"))
		{
			lineTokenizer=readLine.split("&");
		}
		
		else if(readLine.contains("and"))
		{
			lineTokenizer=readLine.split("and");
			authorTokenizedList.add("and");
		}
		
		else
		{
			authorTokenizedList.add(trimmer(readLine));
		}
		
		if(lineTokenizer!=null)
		{
			for(String foundToken : lineTokenizer)
			{
				authorTokenizedList.add(trimmer(foundToken));
			}
		}
		return authorTokenizedList;
	}

}
