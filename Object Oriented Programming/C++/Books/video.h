//
//  video.h
//  Homework 4
//
//  Created by Ulfat on 10/19/17.
//  Copyright © 2017 Ulfat Fruitwala. All rights reserved.
//

#ifndef video_h
#define video_h
#include <iostream>
#include <cstring>
#include "publication.h"

using namespace std;

//Video class that is derived from Publication class. Contains resolution and producer information

class Video: public Publication

{
    
private:
    
    string resolution;
    string producer;
    
public:
    
    //Customized constructor
    Video (string T, string A, string R, string P):Publication (T, A)
    {
        resolution = R;
        producer = P;
    }
    
    void setResolution (string);
    string getResolution();
    
    void setProducer (string);
    string getProducer();
    
};

//Sets resolution of video
void Video::setResolution (string R)
{
    resolution = R;
}

//Returns resolution of video
string Video::getResolution ()
{
    return resolution;
}

//Sets producer of video
void Video::setProducer (string P)
{
    producer = P;
}

//Returns producer of video
string Video::getProducer ()
{
    return producer;
}

#endif /* video_h */
