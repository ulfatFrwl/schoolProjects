README for HW 2: Place your queens with care, CSE101

U. Fruitwala, Feb 2020
----------------------------------------------------------

Code files: Makefile, main.cpp, Queen.cpp, Queen.h
Test files: simple-input.txt, simple-output.txt, more-input.txt, more-output.txt
Extra files: README

----------------------------------------------------------

This program gets a set of numbers from input file, which are coordinates
for queens, and places these queens on a board. It continues to find
places for queens until max number of queens are found, which is the size
of the board. Instead of using recursion to solve this, a stack is used
to retrive the places where queens are situated. 

----------------------------------------------------------

USAGE:
1) Run "make", to get executable "nqueens".
2) Run "./nqueens <INPUT FILE> <OUTPUT FILE>"

Each line of INPUT FILE should have 1 non-negative integer, board size.
Coordinates for queens is optional.
Each line of OUTPUT file has the numbers in the form <column> <space> <row> <space>
for queens found from the program.

----------------------------------------------------------

REFERENCES
1. In code file Queen.cpp, method <bool is_safe(int row int column)> is derived
	 from various online resources that solve the problem, however my function
	 evaluates the entire board for safety, whereas resources online only evaluate
	 columns, rows, and right side of the diagonal.
	 
	 link: https://www.geeksforgeeks.org/printing-solutions-n-queen-problem/