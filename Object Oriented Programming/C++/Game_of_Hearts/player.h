//
//  player.h
//  Homework 6
//
//  Created by Ulfat on 12/2/17.
//  Copyright © 2017 Ulfat. All rights reserved.
//

#ifndef player_h
#define player_h
#include "deck.h"
#include "card.h"

class Player
{
private:

    bool win = false;
    int points = 0;
    Card* handptr[13];
    
public:
    
    int number = 0;
    
    Player();
    
    Player(int i)
    {
        number = i;
    }
    
    void setHand(Card h[][13])
    {
        for (int i = 0; i < 13; i++)
        {
            handptr[i] = &h[number-1][i];
        }
    }
    
    Card getCard(int num)
    {
        return *(handptr[num-1]);
    }
    
    void setWin()
    {
        win = true;
    }
    
    void resetWin()
    {
        win = false;
    }
    
    bool getWin()
    {
        return win;
    }
    
    void setPoints(Card acc[4])
    {
        for (int i = 0; i <4; i++)
        {
            if (acc[i].getSuit()== Card::Suits::hearts)
            {
                points += 1;
            }
            
            if (acc[i].getSuit()== Card::Suits::spades && acc[i].getNumber()==12)
            {
                points += 13;
            }
        }
    }
    
    int getPoints()
    {
        return points;
    }

    
};

#endif /* player_h */
