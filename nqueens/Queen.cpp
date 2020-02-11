// Filename: Queen.cpp
// 
// Class Queen that solves the NQueens problem
// 
// Ulfat Fruitwala, Feb 2020

#include "Queen.h"
#include <string>
#include <stack>
#include <array>
#include <list>
#include <iostream>

using namespace std;

Queen:: Queen()
{
		queensFound = 0;
		top_column = 0;
		top_row = 0;
}

// solve(list l): solves the problem
// Input: list that contains all values from input file
// Output: returns true if found solution, else returns false
bool Queen:: solve(list <int> l)
{
		// initialize the board and get query inputs	
		queen_init(l);    
		
		// if there are 2 or more queens from input query, validate if they are in safe spots
		if(queensFound>1)
				if(!val_existing_queens())  return false;
		
		
		// do first traversal, if all queens are found, return true
		traverse();
		if(queensFound>= board_size)  return true;
	
		
		// otherwise pop the stack
		while(!myStack.empty())
		{
				pop_stack();   // pop the stack
				top_row++;     // increment the row of the queen just removed from the stack
				
				//Try placing the queen in rows below, if there are any
				while(top_row<board_size)
				{
						if(is_safe(top_row, top_column))       // if a safe row is found, place the queen there
						{
								board[top_row][top_column] = 1;            // set the coordinates = 1
								queensFound++;                             // increment number of queens found
								myStack.push({top_row,top_column});        // push on the stack
								traverse();                                // traverse to find remaining queens
								if(queensFound>= board_size)       // check base case after traversal is finished
								{
										//print_board();
										return true;
								}
						}
						top_row++;   // if a safe row isn't found, try the next row
				}
				//loop back to beginning if no rows are safe or available to place a queen
		}
		
		return false;
}

// pop_stack(): pops the stack and sets the coordinates just popped = 0
// Input: none
// Output: none
void Queen:: pop_stack()
{
		top = myStack.top();               // set top to be the top element in stack
		top_row = top[0];
		top_column = top[1];
		board[top_row][top_column] = 0;    // set the coordinates on board back to 0 since we removed a Queen from that spot
		queensFound--;                     // decrement number of queens found
		myStack.pop();                     // pop the stack
}

// queen_init(list l): initializes the board, sets board size, and sets the coordinates from input file = 1
// Input: list that contains values from input file (first element of the list is board size)
// Output: none
void Queen:: queen_init(list <int> l)
{
		//first element in list l is board size, set and then remove
		board_size = l.front();     
		l.pop_front();
		
	  //initalize board
	  board = new int*[board_size];
		for(int i = 0; i < board_size; i++)
				board[i] = new int[board_size];
	
		//set entire board = 0
		for(int i = 0; i < board_size; i++)
				for(int j = 0; j < board_size; j++)
						board[i][j] = 0;
	
		//get remaining elements from list and put them on the board
		int temp1, temp2;
		while(!l.empty())
		{
				temp1 = l.front();        //column 
				l.pop_front();
				temp2 = l.front();        // row
				l.pop_front();
				board[temp2][temp1] = 1;  // set that coordinate = 1;
				queensFound++;            // increment Queens found
		}
}

// traverse(): traverses the board, starting from column 0 to size-1, to find places for queens.
// If a column already has a queen, it skips to the next column. If a queen isn't found in a column,
// the function returns in order to pop the stack.
// Input: none
// Output: none
void Queen:: traverse()
{
		for(int c = 0; c < board_size; c++)
		{
				if(has_queen(c))   continue;    // if this column already has a queen, go to next iteration
				else
				{
						found = false;                         // set flag = false
						for(int r = 0; r < board_size; r++)
								if(is_safe(r,c)==true)             // if a queen is found
								{
										queensFound++;                 // increment number of queens found
										myStack.push({r,c});           // push the coordinates on the stack
										board[r][c] = 1;               // make the board = 1
										found = true;                  // set flag = true
										break;                         // break from this loop
								}
				}
				if(!found) return;        // if no queens found in a column, return from the function
		}
}

// is_safe(int row, int column): evaluates if a coordinate is safe for placing a Queen, returns true if safe, else returns false
// Input: row and column to evaluates
// Output: none
bool Queen:: is_safe(int row, int column)
{
		int c, r;
	
		//CHECK COLUMN
		for(r = 0; r < board_size; r++)
				if(board[r][column]==1)     return false;
	
		//CHECK ROW
		for(c = 0; c < board_size; c++)
				if(board[row][c]==1)        return false;
	
		//CHECK DIAGONAL
		//upper left
		for(r = row, c = column; r >= 0 && c >= 0; r--, c--)
				if(board[r][c]==1)          return false;
	
		//upper right
		for(r = row, c = column; r >= 0 && c < board_size; r--, c++)
				if(board[r][c]==1)          return false;
	
		//lower left
		for(r = row, c = column; r < board_size && c >= 0; r++, c--)
				if(board[r][c]==1)          return false;
	
		//lower right
		for(r = row, c = column; r < board_size && c < board_size; r++, c++)
				if(board[r][c]==1)          return false;
		
		return true;
}

// has_queen(int c): returns true if a column has a queen else returns false
// Input: column to check for a queen
// Output: none
bool Queen:: has_queen(int c)
{
		for(int r = 0; r < board_size; r++)
			if(board[r][c]==1)
					return true;
		return false;
}

// val_existing_queens(): evaluates if there is conflict amongst queens coming from input file
// Input: none
// Output: none
bool Queen:: val_existing_queens()
{
		bool safe = false;
	
		for(int r= 0; r < board_size; r++)
		{
				for(int c = 0; c < board_size; c++)
				{
						if(board[r][c] == 1)
						{
								board[r][c] = 0;   // change current row and column to = 0, else is_safe will mark it unsafe since there is a queen sitting on this spot
								
								if(is_safe(r,c)==true)  
									safe = true;
								else  
									safe = false;
						
								board[r][c] = 1;   // change current row and column back to = 1
						}
				}
		}
		return safe;
}

// print_board(): print the board
// Input: none
// Output: board printed on console
void Queen:: print_board()
{
		for(int r = 0; r < board_size; r++)
		{
				for(int c = 0; c < board_size; c++)
						cout<< board[r][c] << " ";
				cout<<endl;
		}
}

// print_solution(): returns solution as a string
// Input: none
// Output: none
string Queen:: print_solution()
{
		string to_return = "";
		for(int c = 0; c< board_size; c++)
		{
				for(int r = 0; r < board_size; r++)
				{
						if(board[c][r]==1)
							to_return += to_string(c+1) + " " + to_string(r+1) +" ";
				}
		}
		return to_return;
}

// delete_(): deletes dynamically allocated memory for array and sets variables to 0
// Input: none
// Output: none
void Queen:: delete_()
{
		for(int i = 0; i < board_size; ++i) 
			delete [] board[i];
			delete [] board;
		queensFound = 0;
		top_row = 0;
		top_column = 0;
}