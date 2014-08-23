package org.IR;

/**
 * @author Nikhil Bharadwaj Ramesha
 */

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Textparser {
	
/**
 * Accepts the token and trims the special characters using certain conditions 
 * @param token
 * @return
 */
	
	public static String trimmer(String token)
	{
		String trimmedToken=null;
		
		if(token.matches("[0-9]+ *[-] *[0-9]+"))
		{
			String [] tokens=token.split("-");
			for(String tkn : tokens)
			{
				trimmedToken=tkn.replaceAll("[#%$!, .:;+/()*\'\" ]","")+",";
			}
		}
		
		if(token.matches("[,.$#;: ]*[0-9]+[.][0-9]+[,.#$:; ]*"))
		{
			trimmedToken=token.replaceAll("[,: /!\\-#$()\'\";]","");
			if(trimmedToken.charAt(0)=='.')
			{
				trimmedToken=trimmedToken.substring(1,trimmedToken.length()).trim();
			}
			
			if(trimmedToken.charAt(trimmedToken.length()-1)=='.')
			{
				trimmedToken=trimmedToken.substring(0, trimmedToken.length()-1).trim();
			}
		}
		
		else if(token.matches("[a-z]+[.][A-Z][a-z]*.*") || token.matches("[a-z]+[.][a-z]+.*"))
		{
			String [] tokens=token.split("\\.");
			for(String tkn : tokens)
			{
				if(tkn.contains("\'s"))
				{
					tkn=tkn.substring(0,tkn.indexOf('\''));
				}
				trimmedToken=tkn.replaceAll("[./,!()\\-#: ;\'\"]", "").trim().toLowerCase()+",";
			}
		}
		
		else if(token.matches("[A-Z][a-z]+-[a-z]+-*[a-z]*.*") || token.matches("[a-z]+-[a-z]+-*[a-z]*.*"))
		{
			trimmedToken=token.replaceAll("[,. #/!$ %&();:\'\"]", "").toLowerCase();
		}
		
		else if(token.matches("[A-Z][.][A-Z][\\.A-Z]*.*"))
		{
			trimmedToken=token.replaceAll("[./,! ()\\-#:;\'\"]", "").trim();
		}
		
		else
		{
			if(token.contains("\'s"))
			{
				token=token.substring(0,token.indexOf('\''));
			}
			trimmedToken=token.replaceAll("[.,/!#$()\'\":;\\-]", "").trim().toLowerCase();
		}
		return trimmedToken;
	}
	
	/**
	 * Accepts the line in Text section read from the parsed file and tokenizes
	 * @param readLine
	 * @return
	 */

	public static ArrayList<String> returnTextTokens(String readLine)
	{
		ArrayList<String> textTokenizedList=new ArrayList<String>();
		StringTokenizer textTokenizer=new StringTokenizer(readLine," ");
		while(textTokenizer.hasMoreTokens())
		{
			String foundToken=textTokenizer.nextToken();
			if(foundToken.contains("="))
			{
				String [] Tokens=foundToken.split("=");
				for(String token : Tokens)
				{
					textTokenizedList.add(trimmer(token));
				}
			}
			
			else if(foundToken.matches("[.#$;:, ]*[0-9]*[.][0-9]+[.,#$:; ]*"))
			{
				textTokenizedList.add(trimmer(foundToken));
			}
			
			else if(foundToken.matches("[a-z]+[.][A-Z][a-z]*.*") || foundToken.matches("[a-z]+[.][a-z]+.*"))
			{
				String [] tokens=trimmer(foundToken).split(",");
				for(String tkn : tokens)
				{
					textTokenizedList.add(tkn);
				}
			}
			
			else if(foundToken.matches("[A-Z][.][A-Z][\\.A-Z]*.*"))
			{
				textTokenizedList.add(trimmer(foundToken));
			}
			
			else if(foundToken.matches("[0-9]+ *[-] *[0-9]+"))
			{
				String [] tokens=trimmer(foundToken).split(",");
				for(String tkn : tokens)
				{
					textTokenizedList.add(tkn);
				}
			}
			
			else if(foundToken.matches("[A-Z][a-z]+-[a-z]+-*[a-z]*.*") || foundToken.matches("[a-z]+-[a-z]+-*[a-z]*.*"))
			{
				textTokenizedList.add(trimmer(foundToken));
			}
			
			else
			{
				if(!foundToken.matches("[.,?*!%\'\":;$&]"))
				{
					textTokenizedList.add(trimmer(foundToken));
				}
			}
		}
		return textTokenizedList;
	}
}
