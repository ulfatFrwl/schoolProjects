//
//  exception.h
//  Homework 6
//
//  Created by Ulfat on 12/3/17.
//  Copyright © 2017 Ulfat. All rights reserved.
//

#ifndef exception_h
#define exception_h
#include <string>
#include <iostream>
using namespace std;

class Exception
{
public:
    enum error {suit, heart} errortype;
    
    Exception (error e)
    {
        errortype = e;
    }
    
    void printError()
    {
        switch (errortype)
        {
            case suit:
                throw string ("Please play a card with the same suit");
                break;
                
            case heart:
                throw string ("Can't play a heart, play another card");
                break;
        }
    }
    
    
};

#endif /* exception_h */
