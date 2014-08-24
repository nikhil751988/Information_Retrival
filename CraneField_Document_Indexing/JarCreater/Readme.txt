Name: Nikhil Bharadwaj Ramesha
Net ID: nxr122630

Steps to compile and run the program

Below are the commands and instructions to run the program

1. Copy the java files and the shell script provided with this zip to the same location

	Rename jarCreater.sh.txt to jarCreater.sh
	mv jarCreater.sh.txt jarCreater.sh
	chmod 744 jarCreater.sh

2. Create an output directory
	
	mkdir -m 777 Output

3. Run the shell script to launch the program:

	./jarCreater.sh

	-This shell script compiles all the files
	-Creates the executable jar file
	-Run the executable jar file

4. The jar will ask for the following details:

	Source location of the Cranfield:
	Ex./people/cs/s/sanda/cs6322/Cranfield 

	Output File Directory:
	Ex.$PWD/Output

The program will run and the output files which are compressed files and the uncompressed files are written .
Also the program statistics are written into the statistics.txt file which is also in the same output folder. 
	   