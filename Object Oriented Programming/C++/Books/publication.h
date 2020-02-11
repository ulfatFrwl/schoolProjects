//
//  publication.h
//  Homework 4
//
//  Created by Ulfat on 10/19/17.
//  Copyright © 2017 Ulfat Fruitwala. All rights reserved.
//

#ifndef publication_h
#define publication_h
#include <cstring>
#include <iostream>
#include "person.h"
#include "date.h"

class Person;

using namespace std;

//Publication class that contains title, author and borrower of the publication

class Publication

{
    
private:
    
    string title;
    string author;
    Person *borrower;
    Date checkoutDT;
    
    
public:
    
    //Default constructor
    Publication ()
    {
        title = " ";
        author = " ";
    }
    
    //Customized constructor
    Publication (string T, string A)
    {
        title = T;
        author = A;
    }
    
    bool checkedOut = false;
    
    void setTitle (string);
    string getTitle ();
    
    void setAuthor (string);
    string getAuthor ();
    
    void checkOut(Person&);
    void checkIn();
    
    void setBorrower (Person);
    Person getBorrower();
    void displayBorrower();
    
    void setCheckOut(Date);
    void setCustomCheckOut (int, int, int);
    Date getCheckOut();
    
};

//Function sets title of a publication
void Publication::setTitle (string T)
{
    title = T;
}

//Function returns title of a publication
string Publication::getTitle ()
{
    return title;
}

//Function sets author of a publication
void Publication::setAuthor (string A)
{
    author = A;
}

//Function returns author of a publication
string Publication::getAuthor()
{
    return author;
}

//Function makes checkOut to true
void Publication::checkOut(Person &person)
{
    borrower = &person;
    checkedOut = true;
}

//Function makes checkIn to true
void Publication::checkIn()
{
    checkedOut = false;
    borrower->setName(" ");
}

//Function sets borrower
void Publication::setBorrower (Person person)
{
    borrower = &person;
}

//Function returns borrower object
Person Publication::getBorrower()
{
    return *borrower;
}

//Function that displays borrower
void Publication::displayBorrower()
{
    if (checkedOut)
    {
        cout << "Borrower: ";
        borrower->displayName();
    }
    
    
    if (!checkedOut)
    {
        cout << "No borrower" << endl;
    }
}

//Function sets a check out date for a publication
void Publication::setCheckOut(Date dateToday)
{
    checkoutDT = dateToday;
}

//Customizing date of check out of a publication
void Publication::setCustomCheckOut (int d, int m, int y)
{
    checkoutDT.setDate(d,m,y);
}

// Function returns Date object of checkout
Date Publication::getCheckOut()
{
    return checkoutDT;
}

#endif /* publication_h */
