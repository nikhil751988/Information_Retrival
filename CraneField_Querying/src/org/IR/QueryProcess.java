package org.IR;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class is used to process the individual queries
 * @author NIKHIL BHARADWAJ RAMESHA
 *
 */

public class QueryProcess {
	
	/**
	 * This method is used to process each term in the query and call Tfidf class
	 * for weight calculation
	 * @param token
	 * @param queryCounter
	 */
	
	public static void processTerm(String token, int queryCounter)
	{

		if(token.matches("[A-Z][a-z]+-[a-z]+-*[a-z]*.*") || token.matches("[a-z]+-[a-z]+-*[a-z]*.*"))
		{
			token=token.replaceAll("[,. #/!$ %&();:\'\"]", "").toLowerCase();
		}		
		else
		{
			token=token.replaceAll("[.,/!#$()\'\":;\\-]", "");
		}
		
		if(!StopWords.isStopWord(token))
		{
			String stemmedToken=Indexing.gettingStemmedWords(token).toLowerCase();
			TfIdfFinder.maxTermWeighting(stemmedToken, queryCounter);
			TfIdfFinder.okapiWeighting(stemmedToken, queryCounter);
		}
	}
	
	/**
	 * This method parses the Query File
	 * @param queryFile
	 * @throws IOException
	 */
	
	public static void readQueryFile(File queryFile)throws IOException
	{
		boolean queryFlag=false;
		Scanner qFileScan=new Scanner(queryFile);
		int queryCounter=0;
		while(qFileScan.hasNext())
		{
			String scannedLine=qFileScan.nextLine();
			if(scannedLine.contains("Q"))
			{
				queryCounter+=1;
				queryFlag=true;
			}
			
			if(scannedLine.contentEquals(""))
			{
				queryFlag=false;
			}
			
			if(queryFlag && !scannedLine.contains("Q"))
			{
				String [] queryTerms=scannedLine.split(" +");
				for(String term : queryTerms)
				{
					processTerm(term,queryCounter);
				}
			}
		}
		if(queryFlag)
		{
			TfIdfFinder.addLastMaxTermMap();
			TfIdfFinder.addLastOkapiMap();
			queryFlag=false;
		}
		qFileScan.close();
	}

}
