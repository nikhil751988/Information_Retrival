package org.IR;

/**
 * @author Nikhil Bharadwaj Ramesha
 */

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Titleparser {
	
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
				trimmedToken=tkn.replaceAll("[#%$!,. /:;+()*\'\" ]","")+",";
			}
		}
		
		if(token.matches("[.,#$;: ]*[0-9]+[.][0-9]+[.,#$;: ]*"))
		{
			trimmedToken=token.replaceAll("[,:! /\\-#$()\'\";]","");
			if(trimmedToken.charAt(0)=='.')
			{
				trimmedToken=trimmedToken.substring(1,trimmedToken.length()).trim();
;			}
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
				trimmedToken=tkn.replaceAll("[./, !()\\-#:;\'\"]", "").trim().toLowerCase()+",";
			}
		}
		
		else if(token.matches("[A-Z][.][A-Z][\\.A-Z]*.*"))
		{
			trimmedToken=token.replaceAll("[./, !()#\\-:;\'\"]", "").trim();
		}
		
		else if(token.matches("[A-Z][a-z]+-[a-z]+-*[a-z]*.*") || token.matches("[a-z]+-[a-z]+-*[a-z]*.*"))
		{
			trimmedToken=token.replaceAll("[,. #!/$%&();:\'\"]", "").toLowerCase();
		}
		
		else
		{
			if(token.contains("\'s"))
			{
				token=token.substring(0,token.indexOf('\''));
			}
			trimmedToken=token.replaceAll("[.,!\\-#/$ ()\'\":;]", "").trim().toLowerCase();
		}
		
		return trimmedToken;
	}
	
	/**
	 * Accepts the line in Title section read from the parsed file and tokenizes
	 * @param line
	 * @return
	 */
	
	public static ArrayList<String> returnTitleTokens(String line)
	{
		ArrayList<String> titleTokenizedList=new ArrayList<String>();
		StringTokenizer strLine=new StringTokenizer(line," ");
		while(strLine.hasMoreTokens())
		{
			String foundToken=strLine.nextToken();
			if(foundToken.contains("="))
			{
				String [] Tokens=foundToken.split("=");
				for(String token : Tokens)
				{
					titleTokenizedList.add(trimmer(token));
				}
			}
			
			else if(foundToken.matches("[,.#$;: ]*[0-9]*.[0-9]+[,.:;$# ]*"))
			{
				titleTokenizedList.add(trimmer(foundToken));
			}
			
			else if(foundToken.matches("[a-z]+[.][A-Z][a-z]*.*") || foundToken.matches("[a-z]+[.][a-z]+.*"))
			{
				String [] tokens=trimmer(foundToken).split(",");
				for(String tkn : tokens)
				{
					titleTokenizedList.add(tkn);
				}
			}
			
			else if(foundToken.matches("[A-Z][.][A-Z][\\.A-Z]*.*"))
			{
				titleTokenizedList.add(trimmer(foundToken));
			}
			
			else if(foundToken.matches("[A-Z][a-z]+-[a-z]+-*[a-z]*.*") || foundToken.matches("[a-z]+-[a-z]+-*[a-z]*.*"))
			{
				titleTokenizedList.add(trimmer(foundToken));
			}
			
			else if(foundToken.matches("[0-9]+ *[-] *[0-9]+"))
			{
				String [] tokens=trimmer(foundToken).split(",");
				for(String tkn : tokens)
				{
					titleTokenizedList.add(tkn);
				}
			}
			
			else
			{
				if(!foundToken.matches("[.,?*!%\'\":;$&]"))
				{
					titleTokenizedList.add(trimmer(foundToken));
				}
			}
		}
		return titleTokenizedList;
	}

}
