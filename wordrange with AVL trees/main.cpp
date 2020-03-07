#include "avl.h"
#include <string>
#include <string.h>
#include <stdio.h>
#include <iostream>
#include <fstream>
#include <sstream>

using namespace std;

int main(int argc, char** argv)
{
		// VALIDATE ARGUMENT
		if (argc < 3) 
    {
        throw std::invalid_argument("Usage: ./wordrange <INPUT FILE> <OUTPUT FILE>");
    }
	
		//FILE INPUT/OUTPUT
		ifstream input;        // query input
		ofstream output;       // query output
	
		input.open(argv[1]);   // open input file
    output.open(argv[2]);  // open output file
	
		//QUERY
		string query_input;    // store next command
		char *com, *op;        // use with strtok
		char *val1, *val2;     // store values after command
			
		AVL myAVL;             // initialize AVL tree
		
		while(getline(input, query_input))
		{
				if(query_input.length()==0)   continue;       // empty command, continue
			
				com = strdup(query_input.c_str());            // convert string to c-style string
			  op = strtok(com, " \t");                      // first token is operation
			
				if(strcmp(op, "i") == 0)                      // insert into tree
				{
						val1 = strtok(NULL, " \t");               // value after operation
						if(val1 == NULL || myAVL.find(val1) != NULL)  continue;  // either val1 is NULL or val1 is already present, continue
						myAVL.insert(val1);                       // insert val1 in tree
				}
			
				if(strcmp(op, "r") == 0)                      // range
				{
						val1 = strtok(NULL, " \t");               // 1st value after operation
						val2 = strtok(NULL, " \t");               // 2nd value after operation
						output << myAVL.rangeQuery(val1, val2) << endl;  // write range to file
				}			
		}
	
		myAVL.deleteAVL();
		input.close();
		output.close();
}