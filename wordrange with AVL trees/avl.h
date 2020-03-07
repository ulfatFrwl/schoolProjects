// Filename: avl.h 
// 
// Headerfile for the class AVL that represents a self-balancing binary search tree
// 
// Ulfat Fruitwala Feb 2020

#ifndef AVL_H
#define AVL_H

#include <string>
using namespace std;


// class Node to hold the data structure
class Node
{
		public:
			string data;
			Node  *left, *right, *parent;
			int height, subtree_size;
			
			// default constructor, everything is set to null
			Node()
			{
					left = right = parent = NULL;
					height = 0;
					subtree_size = 0;
			}
			
			// constructor sets data to val, height = 1, subtree_size = 1
			Node(string val)
			{
					data = val;
					height = 1;
					subtree_size = 1;
					left = right = parent = NULL;
			}
};

// class AVL
class AVL
{
		private:
			Node* root;                   // root of AVL tree
			
		public:
			AVL();                        // default constructor, sets root to NULL
			
			// insert
			void insert(string);          // insert
			void insert(Node*, Node*);    // recursive insert
			
			// range query
			int rangeQuery(string, string);        // returns number of values between two strings
			int rangeQuery(Node*, string, string); // recursive rangeQuery
			int less(Node*, string);               // returns number of Nodes less than string input
			int greater(Node*, string);            // returns number of Nodes greater than string input
			
			// find
			Node* find(string);           // find
			Node* find(Node*, string);    // recursive find
			
			// rotate
			void rotate_left(Node*);     // performs a left rotation on node
			void rotate_right(Node*);    // performs a right rotation on node
			
			// helper functions
			void node_update(Node*);      // updates height and subtree sizes for a single node
			int  height(Node*);           // returns height of a node
			int  subtree(Node*);          // returns subtree size of a node
			int  balance_factor(Node*);   // returns balance factor of a node
			void balance(Node*, string);  // balances the tree after an insert
			
			// print
			void printInOrder();          // prints tree in order
			string printInOrder(Node*);   // recursive InOrder
			
			// delete
			void deleteAVL();             // deletes every node of the list
			void deleteAVL(Node*);        // recursive delete
};

#endif