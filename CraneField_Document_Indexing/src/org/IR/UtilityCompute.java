package org.IR;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author NIKHIL BHARADWAJ RAMEHSA
 * This class is used for performing utility computations
 */

public class UtilityCompute {
	
	public static HashMap<Integer,String> max_DocStem_freq=new HashMap<Integer, String>();
	public static HashMap<String, Integer> term_docFreq=new HashMap<String, Integer>();
	
	
	/**
	 * This method builds the document containing the max term relationship for every document
	 * @param documentLength
	 */
	
	public static void buildMaxDocStemRelation(int documentLength)
	{
		int max_dtf=0;
		String term_maxDtf=null;
		String term=null;
		
		for(int docId=1;docId<documentLength;docId++)
		{
			for(Map.Entry<String, LinkedList<Postings>> map_Entry : Indexing.term_docFreq_Map.entrySet())
			{
				Iterator<Postings> postItr=map_Entry.getValue().iterator();
				while(postItr.hasNext())
				{
					Postings tempPosting=postItr.next();
					if(tempPosting.returnDocId()==docId)
					{
						if(tempPosting.returnTermFreq()>max_dtf)
						{
							max_dtf=tempPosting.returnTermFreq();
							term=map_Entry.getKey();
							break;
						}
					}
				}
			}
			term_maxDtf=term+":"+String.valueOf(max_dtf);
			max_DocStem_freq.put(docId, term_maxDtf);
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
	
	
	/**
	 * Method is used to write Uncompressed File
	 * @param fileLoc
	 * @return
	 * @throws IOException
	 */
	
	public static File writeUncompressedIndex(String fileLoc)throws IOException
	{
		File tmpFile=new File(fileLoc);
		if(tmpFile.exists())
		{
			tmpFile.delete();
		}
		FileOutputStream fos=new FileOutputStream(fileLoc);
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		oos.writeObject(Indexing.term_docFreq_Map);
		oos.close();
		fos.close();
		File uncompressedFile=new File(fileLoc);
		return uncompressedFile;
	}
	
	/**
	 * Method is used to write Compressed File
	 * @param fileLoc
	 * @return
	 * @throws IOException
	 */
	
	public static File writeCompressedIndex(String fileLoc)throws IOException
	{
		File tmpFile=new File(fileLoc);
		if(tmpFile.exists())
		{
			tmpFile.delete();
		}
		FileOutputStream fos=new FileOutputStream(fileLoc);
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		oos.writeObject(Compresser.compressedIndex);
		oos.close();
		fos.close();
		File compressedFile=new File(fileLoc);
		return compressedFile;
	}
	
	/**
	 * Method used to print uncompressed inverted list size in bytes
	 * @param postingList
	 * @throws IOException
	 */
	
	public static void printingUncompressedSizeBytes(LinkedList<Postings> postingList)throws IOException
	{
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		ObjectOutputStream oos=new ObjectOutputStream(baos);
		oos.writeObject(postingList);
		oos.close();
		System.out.println("Size of Uncompressed Inverted List in bytes: "+baos.toByteArray().length+" Bytes");
		baos.close();
	}
	
	/**
	 * Method used to print compressed inverted list size in bytes
	 * @param cmposting
	 * @throws IOException
	 */
	
	public static void printingCompressedSizeBytes(CompressedPosting cmposting)throws IOException
	{
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		ObjectOutputStream oos=new ObjectOutputStream(baos);
		oos.writeObject(cmposting);
		oos.close();
		System.out.println("Size of Compressed Inverted List in bytes: "+baos.toByteArray().length+" Bytes");
		baos.close();
	}
	
	/**
	 * Method used to print the final statistics
	 * @param elapsedTime
	 * @param uncompressedFile
	 * @param compressedFile
	 * @param statisticsFile
	 * @throws IOException
	 */
	
	public static void printStatistics(long elapsedTime, File uncompressedFile, File compressedFile, String statisticsFile)throws IOException
	{
		PrintStream pstream=new PrintStream(new FileOutputStream(statisticsFile));
		System.setOut(pstream);
		String [] testTokens={"Reynolds", "NASA", "Prandtl", "flow", "pressure", "boundary", "shock"};
		//Printing the Elapsed Time to Tokenize, Index and Compress
		System.out.println("Time taken to Parse, Index and Compress: "+elapsedTime+"ms");
		
		//Printing size before Compression
		System.out.println("The size of the Uncompressed File: "+uncompressedFile.length()/1024+"kB");
		
		//Printing size after Compression
		System.out.println("The sie of the Compressed File: "+compressedFile.length()/1024+"kB");
		
		//The Number of Inverted Lists in the Index
		System.out.println("Number of Inverted Lists: "+Indexing.term_docFreq_Map.size());
		
		/**
		 *  Printing the statistics for the sample tokens taken in the List
		 *  The statistics include term frequency, document frequency and the inverted List Length in Bytes
		 *  Inverted list length displayed for compressed and uncompressed version
		 */
		
		for(String token : testTokens)
		{
			Stemmer stemmer=new Stemmer();
			for(int pos=0;pos<token.length();pos++)
			{
				stemmer.add(token.charAt(pos));
			}
			stemmer.stem();
			String queryWord=stemmer.toString().toLowerCase();
			
			System.out.println("Word: "+token);
			
			//Printing the Document Frequency
			System.out.println("Document Frequency: "+term_docFreq.get(queryWord));
			
			//Printing the Term Frequency with the Document IDs
			System.out.println("Document ID : Term Frequency");
			Iterator<Postings> pItr=Indexing.term_docFreq_Map.get(queryWord).iterator();
			int counterPtr=1;
			int pLength=Indexing.term_docFreq_Map.get(queryWord).size();
			int termFrequency=0;
			while(pItr.hasNext())
			{
				Postings tmpPosting=pItr.next();
				System.out.print(tmpPosting.returnDocId()+":"+tmpPosting.returnTermFreq());
				termFrequency+=tmpPosting.returnTermFreq();
				++counterPtr;
				if(counterPtr<=pLength)
				{
					System.out.print("->");
				}
			}
			System.out.println("\nTotal Term Frequency: "+termFrequency);
			
			//Printing the Length of the Uncompressed Inverted List
			LinkedList<Postings> postingList=Indexing.term_docFreq_Map.get(queryWord);
			try{
				printingUncompressedSizeBytes(postingList);
			}
			catch(IOException e){
				e.printStackTrace();
			}
			
			//Printing the Length of the Compressed Inverted List
			CompressedPosting cmpPosting=Compresser.compressedIndex.get(queryWord);
			try{
				printingCompressedSizeBytes(cmpPosting);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			System.out.println("\n");
		}
		pstream.close();
	}

}
