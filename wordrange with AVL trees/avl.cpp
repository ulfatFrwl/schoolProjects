// Filename: avl.cpp
// 
// Contains the class AVL that represents a well-balanced binary search tree/AVL tree
// 
// Ulfat Fruitwala Feb 2020


#include "avl.h"
#include <cstdlib>
#include <iostream>
#include <algorithm>
#include <stack>

using namespace std;

// Default constructor sets head and tail to null
AVL :: AVL()
{
		root = NULL;
}

// insert(string val): Inserts the string val into tree
// Input: string to insert into the AVL tree
// Output: Void, just inserts new Node
void AVL:: insert(string val)
{
		Node* to_insert = new Node(val);         // create new node with val
		if(root == NULL)  root = to_insert;      // empty tree
		else
			insert(root, to_insert);               // recursive insert
}

// insert(Node* start, Node* to_insert): Inserts the Node to_insert into tree rooted at start. After inserting the tree will update all ancestor's height, subtree count and balance itself.
// Input: string to insert into the subtree
// Output: Void, just inserts new Node
void AVL:: insert(Node* start, Node* to_insert)
{
		if(start == NULL)  return;               // incase start is NULL
	
		// inserted node is smaller, go left
		if(to_insert->data <= start->data)
		{
				if(start->left == NULL)
				{
						start->left = to_insert;          // add new node
						to_insert->parent = start;        // set parent pointer
						node_update(start);               // update the parent node's height and subtree count
						balance(start, to_insert->data);  // balance parent node, if needed
						return;
				}
				else
				{
						insert(start->left, to_insert);   // recursive call
						node_update(start);               // update parent pointer's height and subtree count
						balance(start, to_insert->data);  // balance parent node, if needed
						return;
				}
		}
		
		// inserted node is larger, go right
		else
		{
				if(start->right == NULL)
				{
						start->right = to_insert;         // add new node
						to_insert->parent = start;        // set parent pointer
						node_update(start);               // update the parent node's height and subtree count
						balance(start, to_insert->data);  // balance parent node, if needed
						return;
				}
				else
				{
						insert(start->right, to_insert);  // recursive call
						node_update(start);               // update parent pointer's height and subtree count
						balance(start, to_insert->data);  // balance parent node, if needed
						return;
				}
		}	
}

// rangeQuery(string val1, string val2): counts number of Nodes that are lexographically between val1 and val 2, assuming val1 < val2
// Input: 2 strings
// Output: number of Nodes between val1 and val2
int AVL:: rangeQuery(string val1, string val2)
{
		return rangeQuery(root, val1, val2);
}

// rangeQuery(Node* start, string val1, string val2): recursive rangeQuery
// Input: 2 strings
// Output: number of Nodes between val1 and val2
int AVL:: rangeQuery(Node* start, string val1, string val2)
{
		if(start == NULL)  return 0;
		if(start->data < val1)  return rangeQuery(start->right, val1, val2);
		if(start->data > val2)  return rangeQuery(start->left, val1, val2);
	
		// Node within rangeQuery
		return subtree(start)- less(start->left, val1)- greater(start->right, val2);
}

// less(Node* start, string val1): counts number of Nodes that are lexographically less than val1
// Input: Pointer to subtree node, string val
// Output: number of Nodes less than val1
int AVL:: less(Node* start, string val1)
{
		if(start == NULL) return 0;
		if(start->data == val1) return subtree(start->left);
		if(start->data < val1) return less(start->right, val1) + subtree(start->left) +1;
		return less(start->left, val1);
}

// greater(Node* start, string val2): counts number of Nodes that are lexographically greater than val1
// Input: Pointer to subtree node, string val
// Output: number of Nodes greater than val2
int AVL:: greater(Node* start, string val2)
{
		if(start == NULL) return 0;
		if(start->data == val2) return subtree(start->right);
		if(start->data > val2) return greater(start->left, val2) + subtree(start->right) + 1;
		return greater(start->right, val2);
}

// find(string val): Finds a Node with key "val"
// Input: string to be found
// Output: a pointer to a Node containing val, if it exists. Else returns NULL
Node* AVL:: find(string val)
{
		return find(root, val);
}

// find(Node* start, string val): Recursively tries to find a Node with key "val", in subtree rooted at start
// Input: string to be found
// Output: a pointer to a Node containing val, if it exists. Else returns NULL
Node* AVL:: find(Node* start, string val)
{
		if(start == NULL || start->data == val)     return start;   // tree is empty or found val
		if(val < start->data)      return find(start->left, val);   // val is less than start, go left
		else                       return find(start->right, val);  // val is larger than start, go right
}

// rotate_left(Node* curr): Left rotates subtree located at curr Node, also fixes parent pointer
// Input: Node to be rotated
// Output: Void, just rotates the tree
void AVL:: rotate_left(Node* curr)
{   
		// set temporary nodes
		Node* x = curr->right;   // x is the right child of current node
		Node* y = x-> left;      // y is the left child of x
	  Node* parent = curr->parent;  // parent pointer of current node
	
		// rotate
		x->left = curr;          // make left child of x = current node
		curr->right = y;         // make right child of current node = left child of x
	
		// parent pointers
		if(y != NULL)     y->parent = curr;   // if left child of x exists, update its parent pointer
		curr->parent = x;                     // update current node's parent pointer
		
		if(parent == NULL)                    // if parent of current is null, means current is the root
		{
				root = x;                         // make x the new root
				x->parent = NULL;                 // make x's parent = NULL
		}
		else                                  // if parent of current exists, update x's parent pointer
		{
				if(parent->data > x->data)        // if parent is larger than x, set x to be left child of parent
						parent->left = x;
				else
						parent->right = x;            // else set x to be right child
				x->parent = parent;
		}
	
		// update the heights and subtree sizes
		node_update(curr);
		node_update(x);
}

// rotate_right(Node* curr): Right rotates subtree located at curr Node, also fixes parent pointer
// Input: Node to be rotated
// Output: Void, just rotates the tree
void AVL:: rotate_right(Node* curr)
{   
		// set temporary nodes
		Node* x = curr->left;    // x is the left child of current node
		Node* y = x->right;      // y is the right child of x
		Node* parent = curr->parent;  // parent pointer of current node
	
		// rotate
		x->right = curr;         // make right child of x = current node
		curr->left = y;          // make left child of current node = right child of x
	
		// parent pointers
		if(y != NULL)     y->parent = curr;   // if right child of x exists, update its parent pointer
		curr->parent = x;                     // update current node's parent pointer
		
		if(parent == NULL)                    // if parent of current is null, means current is the root
		{
				root = x;                         // make x the new root
				x->parent = NULL;                 // make x's parent = NULL
		}
		else                                  // if parent of current exists, update x's parent pointer
		{
				if(parent->data > x->data)        // if parent is larger than x, set x to be left child of parent
						parent->left = x;
				else
						parent->right = x;            // else set x to be right child
				x->parent = parent; 
		}
		
	  // update the heights and subtree sizes
		node_update(curr);
		node_update(x);
}

// balance(Node* curr, string val): checks balance factor of subtree rooted at Node curr and makes any rotations if necessary
// Input: Node to check balance factor of and val that was inserted into the tree (note that this function is called from the insert function)
// Output: Void, just checks for balance factor
void AVL:: balance(Node* curr, string val)
{   
		// left left
		if(balance_factor(curr) > 1 && val < curr->left->data)
				rotate_right(curr);
		
		// right right
		if(balance_factor(curr) < -1 && val > curr->right->data)
				rotate_left(curr);
	
		// left right
		if(balance_factor(curr) > 1 && val > curr->left->data)
		{   
				rotate_left(curr->left);
				rotate_right(curr);
		}
	
		// right left
		if(balance_factor(curr) < -1 && val < curr->right->data)
		{   
				rotate_right(curr->right);
				rotate_left(curr);
		}
}

// node_update(Node* curr): updates height and subtree count of a Node, note that this function is called from insert function
// Insert: Node to be updated
// Output: Void, just updates height and subtree count
void AVL:: node_update(Node* curr)
{   
		if(curr == NULL) return;
		
		curr->height = max(height(curr->left), height(curr->right))+ 1;  // height of a node is max(height of left, height of right) + 1
		curr->subtree_size = subtree(curr->left) + subtree(curr->right) + 1; // subtree size of a node is subtree(left child) + subtree(right child) + 1
}
	
// height(Node* curr): returns height of a Node
// Input: Node to get height of
// Output: height of Node
int AVL:: height(Node* curr)
{
		if(curr==NULL)  return 0;
		else
			return curr->height;
}

// subtree(Node* curr): returns subtree size of a Node
// Input: Node to get subtree size of
// Output: subtree size of Node
int AVL:: subtree(Node* curr)
{
		if(curr==NULL)  return 0;
		else
			return curr->subtree_size;
}

// balance_factor(Node* curr): returns balance factor of a Node
// Input: Node to get balance factor of
// Output: balance factor of a Node
int AVL:: balance_factor(Node* curr)
{
		return height(curr->left)-height(curr->right);
}

// Prints tree in order. Calls the recursive function from the root
// Input: None
// Output: string that has all elements of the tree in order
void AVL:: printInOrder()
{
		cout<< printInOrder(root)<<endl;
}

// Prints rooted subtree tree in order, by making recursive calls
// Input: None
// Output: string that has all elements of the rooted tree in order
string AVL:: printInOrder(Node* start)    
{
    if(start == NULL) // base case
        return ""; // return empty string
    string leftpart = printInOrder(start->left);
    string rightpart = printInOrder(start->right);
    string output = start->data;
    if(leftpart.length() != 0) // left part is empty
        output = leftpart + " " + output; // append left part
    if(rightpart.length() != 0) // right part in empty
        output = output + " " + rightpart; // append right part
    return output;
}

// Deletes every Node to prevent memory leaks
// Input: None
// Output: Void, just deletes every Node of the tree
void AVL:: deleteAVL()
{
		deleteAVL(root);
}

// Deletes every Node in subtree rooted at start to prevent memory leaks
// Input: Node* start
// Output: Void, just deletes every Node of the tree
void AVL:: deleteAVL(Node* start)
{
		if(start == NULL)  return;     // tree is empty
		deleteAVL(start->left);
		deleteAVL(start->right);
		delete(start);
}
