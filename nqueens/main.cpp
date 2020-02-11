// Filename: nqueens.cpp
// 
// Contains solution to HW2: Place your queens with care
// 
// Format to run this is:
//    ./Main <INPUT_FILE> <OUTPUT_FILE>
//    
// Input file must contain a board size n, in form of an int
// After board size, the query could have upto n coordinates
// in the form of <INT <INT>, where the first value is the column
// and the second value is the row. These are predisposed Queens.
// 
// Output is a string of coordinates of position for the remaining Queens, 
// in the form of <column> <space> <row> <space>.
// 
// Ulfat Fruitwala, Feb 2020


#include "Queen.h"
#include <iostream>
#include <fstream>
#include <sstream>
#include <stack>
#include <array>
#include <list>
#include <string>
#include <string.h>
#include <stdio.h>
using namespace std;


int main(int argc, char** argv)
{
	  //VALIDATE ARGUMENT
		if(argc<3)
				throw std::invalid_argument("Usage: ./nqueens <INPUT FILE> <OUTPUT FILE>"); // throw error
	
		//FILE INPUT/OUTPUT
		ifstream input;        // query input
		ofstream output;       // query output
	
		input.open(argv[1]);   // open input file
    output.open(argv[2]);  // open output file
	
		//QUERY
		string query_input;
		
	
		while(getline(input, query_input))
		{
				list <int> list_of_inputs;                      // list for input queries
			
				if(query_input.length()==0)   continue;
				
				char char_array[query_input.length()+1];        // array to store input string as char
				strcpy(char_array, query_input.c_str());        // copy string to char array
				
				char *token = strtok(char_array, " ");          // tokenize string query by whitespace, first char which is the size of board
				list_of_inputs.push_back(stoi(token));          // board size is pushed first     
				token = strtok(NULL, " ");
			
				while(token!=NULL)
				{
						list_of_inputs.push_back(stoi(token)-1);    // converting tokens into string and pushing on list
						token = strtok(NULL, " ");
				}

			
				Queen* solve_nqueen = new Queen;                       // Create a Queen object;
			
				if(solve_nqueen->solve(list_of_inputs)==true)          // If there is a solution, print it on the output file
						output << solve_nqueen->print_solution() << endl;
					
				else
					output<< "No solution" << endl;                      // else print "no solution" on the output file
			
				solve_nqueen->delete_();                               // delete dynamically allocated array memory
			
		}
	
	//CLOSE FILES
	input.close();
	output.close();
}

