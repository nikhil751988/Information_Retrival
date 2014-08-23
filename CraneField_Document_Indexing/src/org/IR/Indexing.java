package org.IR;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author NIKHIL BHARADWAJ RAMESHA
 * This class is used to generate inverted index for the posting list
 */

public class Indexing {

		
	
	public static HashMap<String, LinkedList<Postings>> term_docFreq_Map=new HashMap<String, LinkedList<Postings>>();
	
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
