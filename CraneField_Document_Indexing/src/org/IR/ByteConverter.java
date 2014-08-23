package org.IR;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 
 * @author NIKHIL BHARADWAJ RAMESHA
 * This class file writes the bit stream to byte array output stream
 *
 */

public class ByteConverter {
	
	public int counterforByte;
	
	public OutputStream byteOutput;
	
	public int currentByte;
	
	public int counter;
	
	public ByteConverter(OutputStream out)
	{
		if(out==null)
		{
			throw new NullPointerException("Argument is null");
		}
		byteOutput=out;
		currentByte=0;
		counterforByte=0;
		counter=0;
	}
	
	/**
	 * This method accepts the input bit stream and writes to the byte array output stream
	 * @param bit
	 * @throws IOException
	 */
	
	public void writeByte(int bit)throws IOException
	{
		currentByte=currentByte<<1|bit;
		counterforByte=counterforByte+1;
		if(counterforByte==8)
		{
			byteOutput.write(currentByte);
			counterforByte=0;
		}
	}
	
	/**
	 * This method is used to close the byte array output stream
	 * @throws IOException
	 */
	
	public void close()throws IOException
	{
		while(counterforByte>0)
		{
			writeByte(0);
		}
		byteOutput.close();
	}
	
	

}
