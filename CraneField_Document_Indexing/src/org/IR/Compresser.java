package org.IR;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author NIKHIL BHARADWAJ RAMESHA
 * This class is used to initiate the compression for the inverted index list
 */

public class Compresser {
	
	public static HashMap<String, CompressedPosting> compressedIndex=new HashMap<String, CompressedPosting>();
	
	/**
	 * This method calls upon steps for compression taken
	 * @throws IOException
	 */
	
	public static void indexCompression()throws IOException
	{
		for(Map.Entry<String, LinkedList<Postings>> map_entry : Indexing.term_docFreq_Map.entrySet())
		{
			ByteArrayOutputStream gap_aos=new ByteArrayOutputStream();
			ByteArrayOutputStream tf_aos=new ByteArrayOutputStream();
			ByteConverter btConv_gap=new ByteConverter(gap_aos);
			ByteConverter btConv_tf=new ByteConverter(tf_aos);
			Iterator<Postings> lListItr=map_entry.getValue().iterator();
			while(lListItr.hasNext())
			{
				Postings posting=lListItr.next();
				String deltaCode=GammaDeltaCode.eliasDeltaCode(posting.returnGap());
				String gammaCode=GammaDeltaCode.eliasGammaCode(posting.returnTermFreq());
				try
				{
					GammaDeltaCode.writeEliasDeltaCode(btConv_gap, deltaCode);
					GammaDeltaCode.writeEliasGammaCode(btConv_tf, gammaCode);
				}
				catch(Exception e)
				{
					System.out.println("IOException");
					gap_aos.close();
					tf_aos.close();
				}
			}
			btConv_gap.close();
			btConv_tf.close();
			CompressedPosting cmpPost=new CompressedPosting(gap_aos.toByteArray(),tf_aos.toByteArray());
			compressedIndex.put(map_entry.getKey(), cmpPost);
			gap_aos.close();
			tf_aos.close();
		}
	}

}
