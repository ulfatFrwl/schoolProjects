//
//  person.h
//  Homework 4
//
//  Created by Ulfat on 10/19/17.
//  Copyright © 2017 Ulfat Fruitwala. All rights reserved.
//

#ifndef person_h
#define person_h
#include <cstring>
#include <iostream>


using namespace std;

//Person class that creates a person with their full name, ID number and email address

class Person

{
private:
    
    string fullName;
    int ID;
    string email;
    
public:
    
    Person();
    Person(string, int, string);
    
    void setName (string);
    string getName();
    void displayName();
    
    void setID (int);
    int getID ();
    
    void setEmail (string);
    string getEmail ();
    
};

//Default parameter
Person::Person()
{
    fullName = " ";
    ID = 0;
    email = " ";
}

//Customized parameter
Person::Person(string name, int iD, string Email)
{
    fullName = name;
    ID = iD;
    email = Email;
}

//Function sets name
void Person::setName(string name)
{
    fullName = name;
}

//Function gets name
string Person::getName()
{
    return fullName;
}

void Person::displayName()
{
    cout << fullName << endl;
}

//Function sets ID number
void Person::setID(int iD)
{
    ID = iD;
}

//Function gets ID number
int Person::getID()
{
    return ID;
}

//Function sets email address
void Person::setEmail(string Email)
{
    email = Email;
}

//Function gets email address
string Person::getEmail()
{
    return email;
}

#endif /* person_h */

