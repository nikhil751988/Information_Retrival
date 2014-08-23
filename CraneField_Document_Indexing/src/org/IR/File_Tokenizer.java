package org.IR;

/***
 * @author Nikhil Bharadwaj Ramesha
 */

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class File_Tokenizer 
{
	public static HashMap<Integer, Integer> tokensPerDoc;
	
	public static void initialize()
	{
		tokensPerDoc=new HashMap<Integer, Integer>();
	}
	
	/**
	 * Parses and class the utility classes to get the tokens
	 * @param cranField
	 * @throws IOException
	 */
	
	public static void parseFile(File cranField) throws IOException
	{
		String readLine;
		Scanner fileScanner=new Scanner(cranField);
		while(fileScanner.hasNextLine())
		{
			readLine=fileScanner.nextLine();
			if(readLine.equalsIgnoreCase("<DOCNO>"))
			{
				readLine=fileScanner.nextLine();
				DocumentNumber.setDocNumber(readLine.trim());
			}
			
			if(readLine.equalsIgnoreCase("<TITLE>"))
			{
				
				readLine=fileScanner.nextLine();
				while(!readLine.equalsIgnoreCase("</TITLE>"))
				{
					
					Iterator<String> titleTokenItr=Titleparser.returnTitleTokens(readLine).iterator();
					while(titleTokenItr.hasNext())
					{
						String titleToken=titleTokenItr.next();
						
						Indexing.callStemmer(titleToken);
						
						if(!tokensPerDoc.containsKey(DocumentNumber.getDocNumber()))
						{
							tokensPerDoc.put(DocumentNumber.getDocNumber(), 1);
						}

						else
						{
							tokensPerDoc.put(DocumentNumber.getDocNumber(), tokensPerDoc.get(DocumentNumber.getDocNumber())+1);
						}
					}
					readLine=fileScanner.next();
				}
				
			}
			
			if(readLine.equalsIgnoreCase("<AUTHOR>"))
			{
				
				readLine=fileScanner.nextLine();
				
				while(!readLine.equalsIgnoreCase("</AUTHOR>"))
				{
					Iterator<String> authorTokenItr=AuthorParser.returnAuthorTokens(readLine).iterator();
					while(authorTokenItr.hasNext())
					{
						String authorToken=authorTokenItr.next();
						
						Indexing.callStemmer(authorToken);
						
						if(!tokensPerDoc.containsKey(DocumentNumber.getDocNumber()))
						{
							tokensPerDoc.put(DocumentNumber.getDocNumber(), 1);
						}

						else
						{
							tokensPerDoc.put(DocumentNumber.getDocNumber(), tokensPerDoc.get(DocumentNumber.getDocNumber())+1);
						}
					}
					readLine=fileScanner.nextLine();
				}
			}
			
			if(readLine.equalsIgnoreCase("<BIBLIO>"))
			{
				
				readLine=fileScanner.nextLine();
				
				while(!readLine.equalsIgnoreCase("</BIBLIO>"))
				{
					Iterator<String> biblioTokenItr=BiblioParser.returnBiblioTokens(readLine).iterator();
					while(biblioTokenItr.hasNext())
					{
						String biblioToken=biblioTokenItr.next();
						Indexing.callStemmer(biblioToken);
						
						if(!tokensPerDoc.containsKey(DocumentNumber.getDocNumber()))
						{
							tokensPerDoc.put(DocumentNumber.getDocNumber(), 1);
						}

						else
						{
							tokensPerDoc.put(DocumentNumber.getDocNumber(), tokensPerDoc.get(DocumentNumber.getDocNumber())+1);
						}
					}
					readLine=fileScanner.nextLine();
				}

			}
			
			if(readLine.equalsIgnoreCase("<TEXT>"))
			{
				
				readLine=fileScanner.nextLine();
				
				while(!readLine.equalsIgnoreCase("</TEXT>"))
				{
					Iterator<String> textTokenItr=Textparser.returnTextTokens(readLine).iterator();
					while(textTokenItr.hasNext())
					{
						String textToken=textTokenItr.next();
						Indexing.callStemmer(textToken);
						
						if(!tokensPerDoc.containsKey(DocumentNumber.getDocNumber()))
						{
							tokensPerDoc.put(DocumentNumber.getDocNumber(), 1);
						}

						else
						{
							tokensPerDoc.put(DocumentNumber.getDocNumber(), tokensPerDoc.get(DocumentNumber.getDocNumber())+1);
						}
					}
					readLine=fileScanner.nextLine();	
				}
			}
		}
		fileScanner.close();
	}
}
