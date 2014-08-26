PROJECT DESCRIPTION
=====================

This Repository, contains three sub projects which cumulatively builds a Text Based Search Engine

The engine is built to parse CraneField Documents which is a collection of documents containing
SGML tags. The SGML tags in this data follow the conventional style:

		<[/]?tag> | >[/]?tag (attr[=value])+>
		
Each Sub Project's purpose is as follows:

	a. CraneField_FileTokenization: 
	This module parses the documents and builds a plain index structure which is of the 
	dictionary form containing a mapping of a word to its frequency. Rules for parsing 
	is defined in each tag class and top frequency words along with other pertinent 
	details are displayed.
	 								
	b. CraneField_Document_Indexing: 
	This module builds the indexes and inverted indexes by applying Stemming process,
	compresses the indexing structure and stores it into a file. A set of statistics 
	are displayed along with compressed and uncompressed indexed structure size to 
	highlight the minimal disk space usage of compressed structure. The Gamma-Delta 
	encoding scheme is used in Compression.
									 
	c. CraneField_Querying: 
	In this module, a set of pre-defined queries are provided which are parsed and by 
	applying the TF-IDF weighted scheme against each query and documents, the relevant
	documents are displayed for the given user queries.
							
The CraneField Document collection and the Query File is provided

The instructions for running each module is given in the ReadMe.txt contained within them  
									
