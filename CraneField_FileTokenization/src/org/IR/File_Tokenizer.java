package org.IR;

/***
 * @author Nikhil Bharadwaj Ramesha
 */

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class File_Tokenizer 
{
	private static HashMap<String,Integer> tokenCollection;
	private static HashMap<String,HashSet<Integer>> postingList;
	private static HashMap<Integer, Integer> tokensPerDoc;
	
	public static void initialize()
	{
		tokenCollection=new HashMap<String, Integer>();
		postingList=new HashMap<String,HashSet<Integer>>();
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
						String titleToken=titleTokenItr.next().toString();
						if(!tokenCollection.containsKey(titleToken))
						{
							tokenCollection.put(titleToken, 1);
						}
						else
						{
							tokenCollection.put(titleToken, tokenCollection.get(titleToken)+1);
						}

						if(!postingList.containsKey(titleToken))
						{
							HashSet<Integer> titleDocNoSet=new HashSet<Integer>();
							titleDocNoSet.add(DocumentNumber.getDocNumber());
							postingList.put(titleToken, titleDocNoSet);
						}

						else
						{
							HashSet<Integer> titleDocNoSet=postingList.get(titleToken);
							if(titleDocNoSet.add(DocumentNumber.getDocNumber()))
							{
								postingList.put(titleToken, titleDocNoSet);
							}						
						}

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
						String authorToken=authorTokenItr.next().toString();
						//System.out.println(authorToken);
						if(!tokenCollection.containsKey(authorToken))
						{
							tokenCollection.put(authorToken, 1);
						}
						else
						{
							tokenCollection.put(authorToken, tokenCollection.get(authorToken)+1);
						}

						if(!postingList.containsKey(authorToken))
						{
							HashSet<Integer> authorDocNoSet=new HashSet<Integer>();
							authorDocNoSet.add(DocumentNumber.getDocNumber());
							postingList.put(authorToken, authorDocNoSet);
						}

						else
						{
							HashSet<Integer> authorDocNoSet=postingList.get(authorToken);
							if(authorDocNoSet.add(DocumentNumber.getDocNumber()))
							{
								postingList.put(authorToken, authorDocNoSet);
							}						
						}

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
						String biblioToken=biblioTokenItr.next().toString();
						if(!tokenCollection.containsKey(biblioToken))
						{
							tokenCollection.put(biblioToken, 1);
						}
						else
						{
							tokenCollection.put(biblioToken, tokenCollection.get(biblioToken)+1);
						}

						if(!postingList.containsKey(biblioToken))
						{
							HashSet<Integer> biblioDocNoSet=new HashSet<Integer>();
							biblioDocNoSet.add(DocumentNumber.getDocNumber());
							postingList.put(biblioToken, biblioDocNoSet);
						}

						else
						{
							HashSet<Integer> biblioDocNoSet=postingList.get(biblioToken);
							if(biblioDocNoSet.add(DocumentNumber.getDocNumber()))
							{
								postingList.put(biblioToken, biblioDocNoSet);
							}						
						}

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
						String textToken=textTokenItr.next().toString();
						if(!tokenCollection.containsKey(textToken))
						{
							tokenCollection.put(textToken, 1);
						}
						else
						{
							tokenCollection.put(textToken, tokenCollection.get(textToken)+1);
						}

						if(!postingList.containsKey(textToken))
						{
							HashSet<Integer> textDocNoSet=new HashSet<Integer>();
							textDocNoSet.add(DocumentNumber.getDocNumber());
							postingList.put(textToken, textDocNoSet);
						}

						else
						{
							HashSet<Integer> textDocNoSet=postingList.get(textToken);
							if(textDocNoSet.add(DocumentNumber.getDocNumber()))
							{
								postingList.put(textToken, textDocNoSet);
							}						
						}

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
	}
	
	/**
	 * Print Statistics
	 */
	
	public static void printDetails()
	{
		int summationTokens=0;
		int avgTokensPerDoc=0;
		int wordsOccOnce=0;
		int counter=30;
		/**
		 * Printing Number of Tokens in the CranField Collection
		 */
		
		System.out.print("\nTotal Number of Tokens in Cranfield Collection: ");
		
		for(Map.Entry<Integer, Integer>entry:tokensPerDoc.entrySet())
		{
			summationTokens+=entry.getValue();
		}
		
		System.out.println(summationTokens+"\n");
		
		/**
		 * Printing Number of Unique words in Cranfield Collection
		 */
		
		System.out.print("Number of Cranfield Unique Words: ");
		System.out.println(tokenCollection.size()+"\n");
				
		/**
		 * Printing Words occurring only once in the Cranfield Collection
		 */
		
		System.out.print("Number of Words occurring only once in the Cranfield Collection: ");
		
		for(Map.Entry<String, Integer>entry:tokenCollection.entrySet())
		{
			if(entry.getValue()==1)
			{
				wordsOccOnce+=1;
			}
		}
		System.out.println(wordsOccOnce+"\n");
		
		/**
		 * Printing 30 Most Frequent Words
		 */
		
		List<Map.Entry<String, Integer>> sortedList=new LinkedList<Map.Entry<String, Integer>>(tokenCollection.entrySet());
		
		Collections.sort(sortedList, new Comparator<Map.Entry<String, Integer>>(){

			public int compare(Entry<String, Integer> value1,
					Entry<String, Integer> value2) {
				// TODO Auto-generated method stub
				return value2.getValue().compareTo(value1.getValue());
			}			
		
		});
		
		System.out.println("Printing Top 30 frequent Words in Cranfield Collection..\n");
		
		for(Map.Entry<String, Integer> entry : sortedList)
		{
			if(counter!=0)
			{
				System.out.println(entry.getKey()+"="+entry.getValue());
			}
			else
			{
				break;
			}
			counter--;
		}
	
		/**
		 * Printing the Average tokens per document
		 */
				
		avgTokensPerDoc=summationTokens/tokensPerDoc.size();
		
		System.out.println("\nAverage Tokens Per Document:"+avgTokensPerDoc);
	}
	
	public static void main(String [] args)
	{
		File inputDir=new File(args[0]);
		Date dtStart=new Date();		
		long startTime=dtStart.getTime();
		final File [] listOfFiles=inputDir.listFiles();
		initialize();
		for(final File file : listOfFiles)
		{
			try{

				parseFile(file);
			}
			catch(Exception ie)
			{
				ie.printStackTrace();
			}
		}
		Date dtEnd=new Date();
		long endTime=dtEnd.getTime();
		printDetails();
		System.out.println("\n Time Taken by the Tokenizer Program: "+(endTime-startTime)+"ms");
	}
}
