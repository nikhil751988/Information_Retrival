package org.IR;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author NIKHIL BHARADWAJ RAMEHSA
 * This class is used for performing utility computations
 */

public class UtilityCompute {
	
		
	/**
	 * This method sorts the HashMap entry
	 * Returns the sorted list object
	 * @param tempMap
	 * @return
	 */
	
	public static List<Map.Entry<Integer, Double>> sortWeightMap(HashMap<Integer, Double> tempMap)
	{
		List<Map.Entry<Integer, Double>> sortedList=new LinkedList<Map.Entry<Integer, Double>>(tempMap.entrySet());
		
		Collections.sort(sortedList, new Comparator<Map.Entry<Integer, Double>>(){

			public int compare(Entry<Integer, Double> value1,
					Entry<Integer, Double> value2) {
				// TODO Auto-generated method stub
				return value2.getValue().compareTo(value1.getValue());
			}			
		
		});
		
		return sortedList;
	}
	
	/**
	 * This method is used to print the statistics
	 * Statistics are printed to Statistics.txt
	 * @throws IOException
	 */
	
	public static void printQueryDocRelevanceDetails() throws IOException
	{
		PrintStream pStream=new PrintStream(new FileOutputStream("Statistics.txt"));
		System.setOut(pStream);
		System.out.println("Following is the Query Document Relevance:\n");
		int counter=0;
		int topTen;
		Iterator<HashMap<Integer, Double>> maxTItr=TfIdfFinder.mtfidf_map_list.iterator();
		Iterator<HashMap<Integer, Double>> okapiItr=TfIdfFinder.otfidf_map_list.iterator();
		while(maxTItr.hasNext())
		{
			topTen=10;
			counter+=1;
			int mtrank=0;
			int okrank=0;
			System.out.println("For Query "+counter+": \n");
			System.out.println("Max Term Weighting Scheme: ");
			HashMap<Integer, Double> tmpMaxTMap=maxTItr.next();
			List<Map.Entry<Integer, Double>> maxTSortList=sortWeightMap(tmpMaxTMap);
			for(Map.Entry<Integer, Double> mTEntry : maxTSortList)
			{
				mtrank+=1;
				System.out.println("Document "+mTEntry.getKey()+": "+mTEntry.getValue()+" Rank: "+mtrank);
				System.out.println("Header: "+File_Tokenizer.headerMap.get(mTEntry.getKey()));
				System.out.println();
				topTen--;
				if(topTen==0)
				{
					break;
				}
			}
			topTen=10;
			System.out.println("\n Okapi Weighting Scheme: ");
			HashMap<Integer, Double> tmpOkapiMap=okapiItr.next();
			List<Map.Entry<Integer, Double>> okapiSortList=sortWeightMap(tmpOkapiMap);
			for(Map.Entry<Integer, Double> okEntry : okapiSortList)
			{
				okrank+=1;
				System.out.println("Document "+okEntry.getKey()+": "+okEntry.getValue()+" Rank: "+okrank);
				System.out.println("Header: "+File_Tokenizer.headerMap.get(okEntry.getKey()));
				System.out.println();
				topTen--;
				if(topTen==0)
				{
					break;
				}
			}
			System.out.println("\n");
		}
		pStream.close();
	}

}
