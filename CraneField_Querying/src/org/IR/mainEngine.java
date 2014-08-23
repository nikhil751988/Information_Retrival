package org.IR;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author NIKHIL BHARADWAJ RAMESHA
 * This is the main class which initiates the program
 */

public class mainEngine {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String inputDirLoc=null;
		String queryFile=null;
		try
		{		
			System.out.println("Enter the Cranfield Document Collection Location:");
			InputStreamReader iStreamRdr=new InputStreamReader(System.in);
			BufferedReader bfReader=new BufferedReader(iStreamRdr);
			inputDirLoc=bfReader.readLine();
			System.out.println("Enter the Query File with location:");
		    queryFile=bfReader.readLine();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		File inputDir=new File(inputDirLoc);
		final File [] listOfFiles=inputDir.listFiles();
		File_Tokenizer.initialize();
		for(final File file : listOfFiles)
		{
			try{

				File_Tokenizer.parseFile(file);
			}
			catch(Exception ie)
			{
				ie.printStackTrace();
			}
		}
		
		Indexing.buildMaxDocStemRelation(listOfFiles.length);
		Indexing.storeDocumentFreq();
		File_Tokenizer.calAvgDocLength();
		
		File queryFileLoc=new File(queryFile);
				
		try{
			QueryProcess.readQueryFile(queryFileLoc);
		}
		catch(IOException ie)
		{
			ie.printStackTrace();
		}
		
		try{
			UtilityCompute.printQueryDocRelevanceDetails();
		}
		catch(IOException ie)
		{
			ie.printStackTrace();
		}
		
	}

}
