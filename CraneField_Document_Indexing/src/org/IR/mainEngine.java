package org.IR;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * @author NIKHIL BHARADWAJ RAMESHA
 * This is the main class which initiates the program
 */

public class mainEngine {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String inputDirLoc=null;
		String outputDirLoc=null;
		File compressedF=null;
		File uncompressedF=null;
		try
		{		
			System.out.println("Enter the Cranfield Document Collection Location:");
			InputStreamReader iStreamRdr=new InputStreamReader(System.in);
			BufferedReader bfReader=new BufferedReader(iStreamRdr);
			inputDirLoc=bfReader.readLine();
			System.out.println("Enter the Output Location:");
			outputDirLoc=bfReader.readLine();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		File inputDir=new File(inputDirLoc);
		Date dtStart=new Date();		
		long startTime=dtStart.getTime();
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
		UtilityCompute.buildMaxDocStemRelation(listOfFiles.length);
		UtilityCompute.storeDocumentFreq();
		try
		{
			Compresser.indexCompression();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		String uncompressedFileLoc=outputDirLoc+"\\UncompressedIndex.ser";
		String compressedFileLoc=outputDirLoc+"\\CompressedIndex.ser";
		String statisticsFile=outputDirLoc+"\\statistics.txt";
		
		try{
			uncompressedF=UtilityCompute.writeUncompressedIndex(uncompressedFileLoc);
			compressedF=UtilityCompute.writeCompressedIndex(compressedFileLoc);
		}
		catch(IOException ie)
		{
			ie.printStackTrace();
		}
		
		Date dtEnd=new Date();
		long endTime=dtEnd.getTime();
		long elapsedTime=endTime-startTime;
		
		try{
			UtilityCompute.printStatistics(elapsedTime, uncompressedF, compressedF,statisticsFile);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}
