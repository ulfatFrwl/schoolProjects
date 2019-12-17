//
//  book.h
//  Homework 4
//
//  Created by Ulfat on 10/19/17.
//  Copyright © 2017 Ulfat Fruitwala. All rights reserved.
//

#ifndef book_h
#define book_h
#include <iostream>
#include <cstring>
#include "publication.h"

using namespace std;

//Book class that is derived from Publication class. Contains page and format information

class Book: public Publication

{
    
private:
    
    int pages;
    string format;
    
public:
    
    //Customized constructor
    Book(string T, string A, int P, string F) : Publication(T, A)
    {
        pages = P;
        format = F;
    }
    
    void setPages (int);
    int getPages ();
    
    void setFormat (string);
    string getFormat ();
    
};

//Sets pages of book
void Book::setPages (int P)
{
    pages = P;
}

//Returns pages of book
int Book::getPages ()
{
    return pages;
}

//Sets format of book
void Book::setFormat (string F)
{
    format = F;
}

//Returns format of book
string Book::getFormat ()
{
    return format;
}


#endif /* book_h */
