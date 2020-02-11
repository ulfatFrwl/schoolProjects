// Filename: Queen.h
// 
// Headerfile for class Queen that solves NQueens problem
// 
// Ulfat Fruitwala, Feb 2020

#ifndef QUEEN_H
#define QUEEN_H

#include <string>
#include <stack>
#include <array>
#include <list>
#include <iostream>
using namespace std;

class Queen
{
		public:
		int board_size;                              // Size of board
		int queensFound;                             // Number of Queens found
		int **board;                                 // Dynamic 2D array to imitate a chess board
		
		//extra variables used during traverse and popping the stack
		bool found;
		int top_column;
		int top_row;
	
		stack <array<int,2>> myStack;                // empty stack of array with 2 indexes
		array<int,2> top;                            // variable of type array<int,2> to store top of the stack
		
		//CONSTRUCTOR, initializes queensFound = 0
		Queen();
		
		//INITIALIZES
		void queen_init(list <int>);
		
		//EVALUATING FUNCTIONS
		bool solve(list <int>);
		bool val_existing_queens();
		bool has_queen(int);
		
		//HELPER FUNCTIONS
		void print_board();
		string print_solution();
		void traverse();
		bool is_safe(int, int);
		void delete_();
		
		//FUNCTIONS FOR STACK
		void pop_stack();
		
};

#endif