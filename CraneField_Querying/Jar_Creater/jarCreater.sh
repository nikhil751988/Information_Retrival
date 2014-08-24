#!/bin/sh
#Check if the directory exists and delete it
if [ -d org ]
then
rm -rf org/
fi
mkdir -p org/IR
#Compiling the independent Java files
javac AuthorParser.java
javac BiblioParser.java
javac DocumentNumber.java
javac Textparser.java
javac Titleparser.java
javac Postings.java
javac Stemmer.java
javac StopWords.java
#copying the class files to classpath directory
mv *.class org/IR
#compiling the dependent java file and move it to classpath directory
javac -classpath $PWD Indexing.java
mv *.class org/IR
javac -classpath $PWD File_Tokenizer.java
mv *.class org/IR
javac -classpath $PWD TfIdfFinder.java
mv *.class org/IR 
javac -classpath $PWD QueryProcess.java
mv *.class org/IR
javac -classpath $PWD UtilityCompute.java
mv *.class org/IR
javac -classpath $PWD mainEngine.java
mv *.class org/IR
#Create a Manifest file
if [ -f manifest.txt ]
then
echo "Manifest file already present"
else
echo "Main-Class: org.IR.mainEngine" >> manifest.txt
fi
#Create the executable jar file
if [ -s WeightScheme.jar ]
then
rm -rf WeightScheme.jar
fi
jar -cvfm WeightScheme.jar manifest.txt org/
#give permission to execute
chmod 744 WeightScheme.jar
#Executing the jar file
java -jar WeightScheme.jar
