//
//  music.h
//  Homework 4
//
//  Created by Ulfat on 10/19/17.
//  Copyright © 2017 Ulfat Fruitwala. All rights reserved.
//

#ifndef music_h
#define music_h
#include <iostream>
#include <cstring>
#include "publication.h"

using namespace std;

//Music class that is derived from Publication class. Contains duration and format information

class Music: public Publication

{
    
private:
    
    int duration;
    string format;
    
public:
    
    //Customized constructor
    Music (string T, string A, int D, string F): Publication (T, A)
    {
        duration = D;
        format = F;
    }
    
    void setDuration (int);
    int getDuration ();
    
    void setFormat (string);
    string getFormat ();
    
};

//Sets duration of music
void Music::setDuration (int D)
{
    duration = D;
}

//Returns duration of music
int Music::getDuration ()
{
    return duration;
}

//Sets format of music
void Music::setFormat (string F)
{
    format = F;
}

//Returns format of music
string Music::getFormat ()
{
    return format;
}


#endif /* music_h */
