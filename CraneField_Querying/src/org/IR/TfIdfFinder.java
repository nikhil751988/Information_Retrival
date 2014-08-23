package org.IR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author NIKHIL BHARADWAJ RAMESHA
 * This Class is used to calculate different weight schemes defined
 *
 */

public class TfIdfFinder {
	
	public static int mqueryId=0;
	public static int oqueryId=0;
	
	public static HashMap<Integer, Double> max_tfidf_map=new HashMap<Integer, Double>();
	public static HashMap<Integer, Double> okapi_tfidf_map=new HashMap<Integer, Double>();
	
	public static ArrayList<HashMap<Integer, Double>> mtfidf_map_list=new ArrayList<HashMap<Integer, Double>>();
	public static ArrayList<HashMap<Integer, Double>> otfidf_map_list=new ArrayList<HashMap<Integer, Double>>();
	
	/**
	 * This method is to calculate max term weighting scheme
	 * @param stem
	 * @param queryident
	 */
	
	public static void maxTermWeighting(String stem, int queryident)
	{
		double mxT_tf_idf=0;
		
		if(mqueryId==0)
		{
			mqueryId=queryident;
		}
		
		if(mqueryId!=queryident)
		{
			mqueryId=queryident;
			HashMap<Integer, Double> tfIdf_map=new HashMap<Integer, Double>();
			tfIdf_map.putAll(max_tfidf_map);
			mtfidf_map_list.add(tfIdf_map);
			max_tfidf_map.clear();
		}
	
		double collectionSize=1400;
		LinkedList<Postings> tempPostList=Indexing.term_docFreq_Map.get(stem);
		if(tempPostList!=null)
		{
			for(Postings posting : tempPostList)
			{
				double termFreq=(double)posting.returnTermFreq();
				double docFreq=(double)Indexing.term_docFreq.get(stem);
				String maxTerm=Indexing.max_DocStem_freq.get(queryident);
				double maxTf=Double.parseDouble(maxTerm.substring(maxTerm.indexOf(':')+1, maxTerm.length()));
				mxT_tf_idf=(0.4+0.6*Math.log(termFreq+0.5)/Math.log(maxTf+1.0))*(Math.log(collectionSize/docFreq)/Math.log(collectionSize));
				if(!max_tfidf_map.containsKey(posting.returnDocId()))
				{
					max_tfidf_map.put(posting.returnDocId(), mxT_tf_idf);
				}

				else
				{
					max_tfidf_map.put(posting.returnDocId(), max_tfidf_map.get(posting.returnDocId())+mxT_tf_idf);
				}
			}
		}
	}
	
	/**
	 * This method is used to calculate Okapi weighting scheme
	 * @param stem
	 * @param queryident
	 */
	
	public static void okapiWeighting(String stem, int queryident)
	{
		double okapi_tf_idf=0;
		
		if(oqueryId==0)
		{
			oqueryId=queryident;
		}
		
		if(oqueryId!=queryident)
		{
			oqueryId=queryident;
			HashMap<Integer, Double> tfIdf_map=new HashMap<Integer, Double>();
			tfIdf_map.putAll(okapi_tfidf_map);
			otfidf_map_list.add(tfIdf_map);
			okapi_tfidf_map.clear();
		}
		
		double collectionSize=1400;
		LinkedList<Postings> tempPostList=Indexing.term_docFreq_Map.get(stem);
		if(tempPostList!=null)
		{
			for(Postings posting : tempPostList)
			{
				double termFreq=(double)posting.returnTermFreq();
				double docFreq=(double)Indexing.term_docFreq.get(stem);
				double docLen=(double)File_Tokenizer.tokensPerDoc.get(posting.returnDocId());
				double avgDocLen=File_Tokenizer.avgDocLen;
				okapi_tf_idf=(0.4+0.6*(termFreq/(termFreq+0.5+1.5*(docLen/avgDocLen)))*(Math.log(collectionSize/docFreq)/Math.log(collectionSize)));
				if(!okapi_tfidf_map.containsKey(posting.returnDocId()))
				{
					okapi_tfidf_map.put(posting.returnDocId(), okapi_tf_idf);
				}

				else
				{
					okapi_tfidf_map.put(posting.returnDocId(), okapi_tfidf_map.get(posting.returnDocId())+okapi_tf_idf);
				}
			}
		}
	}
	
	/**
	 * Add last max term map object to the list
	 */
	
	public static void addLastMaxTermMap()
	{
		mtfidf_map_list.add(max_tfidf_map);		
	}
	
	/**
	 * Add last okapi term object to the list
	 */
	
	public static void addLastOkapiMap()
	{
		otfidf_map_list.add(okapi_tfidf_map);
	}
}
