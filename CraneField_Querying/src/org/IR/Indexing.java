package org.IR;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author NIKHIL BHARADWAJ RAMESHA
 * This class is used to generate inverted index for the posting list
 */

public class Indexing {

		
	
	public static HashMap<String, LinkedList<Postings>> term_docFreq_Map=new HashMap<String, LinkedList<Postings>>();
	public static HashMap<Integer,String> max_DocStem_freq=new HashMap<Integer, String>();
	public static HashMap<String, Integer> term_docFreq=new HashMap<String, Integer>();
	
	public static LinkedList<Postings> doc_termFreq_List;
	
	/**
	 * This method calls upon the stemmer
	 * @param token
	 * @return
	 */
	
	public static String gettingStemmedWords(String token)
	{
		Stemmer stmr=new Stemmer();
		for(int index=0;index<token.length();index++)
		{
			stmr.add(token.charAt(index));
		}
		stmr.stem();
		return stmr.toString();
	}
	
	/**
	 * This method calls the stemmer and builds the inverted list
	 * for each token
	 * @param token
	 */
	
	public static void callStemmer(String token)
	{
		String stem=null;
		
		if(!StopWords.isStopWord(token))
		{
			stem=gettingStemmedWords(token).toLowerCase();
		}
		
		if(stem!=null && !stem.contentEquals(""))
		{
			if(!term_docFreq_Map.containsKey(stem))
			{
				Postings posting=new Postings(DocumentNumber.docNumber,1);
				doc_termFreq_List=new LinkedList<Postings>();
				doc_termFreq_List.add(posting);
				term_docFreq_Map.put(stem, doc_termFreq_List);
			}

			else
			{
				if(term_docFreq_Map.get(stem).getLast().returnDocId()==DocumentNumber.getDocNumber())
				{
					term_docFreq_Map.get(stem).getLast().setTermFreq();
				}
				else
				{
					int gap=DocumentNumber.getDocNumber()-term_docFreq_Map.get(stem).getLast().returnDocId();
					Postings posting=new Postings(gap,1,DocumentNumber.getDocNumber());
					term_docFreq_Map.get(stem).add(posting);
				}

			}
		}
	}
	
	/**
	 * This method builds the document containing the max term relationship for every document
	 * @param documentLength
	 */
	
	public static void buildMaxDocStemRelation(int documentLength)
	{
		int max_dtf;
		String term_maxDtf;
		String Key=null;
		for(int docId=1;docId<=documentLength;docId++)
		{
			max_dtf=0;
			String term=null;
			for(Map.Entry<String, LinkedList<Postings>> map_Entry : Indexing.term_docFreq_Map.entrySet())
			{
				Key=map_Entry.getKey();
				Iterator<Postings> postItr=map_Entry.getValue().iterator();
				while(postItr.hasNext())
				{
					Postings tempPosting=postItr.next();
					if(tempPosting.returnDocId()==docId)
					{
						if(tempPosting.returnTermFreq()>max_dtf)
						{
							max_dtf=tempPosting.returnTermFreq();
							term=Key;
						}
						break;
					}
				}
			}
			if(Key!=null && max_dtf!=0)
			{
				term_maxDtf=term+":"+String.valueOf(max_dtf);
				max_DocStem_freq.put(docId, term_maxDtf);
			}
		}
	}
	
	/**
	 * This method stores the document frequency for each term
	 */
	
	public static void storeDocumentFreq()
	{
		for(Map.Entry<String, LinkedList<Postings>> map_entry : Indexing.term_docFreq_Map.entrySet())
		{
			term_docFreq.put(map_entry.getKey(), map_entry.getValue().size());
		}
	}

}
