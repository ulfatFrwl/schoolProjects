//
//  card.h
//  Homework 6
//
//  Created by Ulfat on 12/2/17.
//  Copyright © 2017 Ulfat. All rights reserved.
//

#ifndef card_h
#define card_h
#include <iostream>
#include <string>
using namespace std;

class Card
{
private:
    
    int number;
    string description;
    
public:
        
    enum Suits {clubs, diamonds, hearts, spades, null} suitName;
    
    void setSuit (Suits);
    Suits getSuit();
    
    void setNumber(int num)
    {
        number = num;
    }
    
    int getNumber()
    {
        return number;
    }
    
    void setDescription();
    string getDescription();
    
    bool operator > (Card& card)
    {
        if (getSuit()>card.getSuit())
            return true;
        
        else if (getSuit()==card.getSuit() && getNumber()!= 1 && card.getNumber()!= 1 && getNumber() > card.getNumber())
            return true;
        
        else if (getSuit()==card.getSuit() && getNumber()==1)
            return true;
        
        else
            return false;
    }
    
};

//Set suit name
void Card::setSuit (Suits s)
{
    suitName = s;
}

//Return suit in integer form
Card::Suits Card::getSuit()
{
    return suitName;
}


//Setting description of card and returning a description
void Card::setDescription()
{
    string NumDesc, SuitDesc, OF = " of ";
    
    switch (number)
    {
        case 0:
            NumDesc = " ";
            break;
        case 1:
            NumDesc = "Ace of ";
            break;
            
        case 11:
            NumDesc = "Jack of ";
            break;
            
        case 12:
            NumDesc = "Queen of ";
            break;
            
        case 13:
            NumDesc = "King of ";
            break;
            
        default:
            NumDesc = to_string(number);
            NumDesc += OF;
            setNumber(number);
            break;
    }
    
    switch (suitName)
    {
        case 0:
            SuitDesc = "Clubs";
            break;
            
        case 1:
            SuitDesc = "Diamonds";
            break;
            
        case 2:
            SuitDesc = "Hearts";
            break;
            
        case 3:
            SuitDesc = "Spades";
            break;
            
        case 4:
            SuitDesc = " ";
            break;
            
    }
    
    description = NumDesc+SuitDesc;
    
}

//Returns description
string Card::getDescription()
{
    return description;
}

#endif /* card_h */

