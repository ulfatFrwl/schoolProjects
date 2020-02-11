//
//  deck.h
//  Homework 6
//
//  Created by Ulfat on 12/2/17.
//  Copyright © 2017 Ulfat. All rights reserved.
//

#ifndef deck_h
#define deck_h
#include <iostream>
#include "card.h"
#include "player.h"
#include "game.h"
#include <ctime>
#include <cstdlib>
#include <iomanip>
using namespace std;

class Deck
{
private:
    
    Card cardDeck[52];
    
public:
    
    //Constructor that initializes values for array of cards
    Deck()
    {
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 13; j++)
            {
                if (i==0)
                {
                    cardDeck[j].setSuit(Card::Suits::clubs);
                    cardDeck[j].setNumber(j+1);
                    cardDeck[j].setDescription();
                }
                
                if (i==1)
                {
                    cardDeck[j+13].setSuit(Card::Suits::diamonds);
                    cardDeck[j+13].setNumber(j+1);
                    cardDeck[j+13].setDescription();
                }
                
                if (i==2)
                {
                    cardDeck[j+26].setSuit(Card::Suits::hearts);
                    cardDeck[j+26].setNumber(j+1);
                    cardDeck[j+26].setDescription();
                }
                
                if (i==3)
                {
                    cardDeck[j+39].setSuit(Card::Suits::spades);
                    cardDeck[j+39].setNumber(j+1);
                    cardDeck[j+39].setDescription();
                }
            }
        }
    }
    
    void shuffle();
    void deal(Card[][13], Player p[]);
    void sort(Card[][13]);
    void display(Card[][13],Player pl[]);
    void displayOri();
    
    
};

void Deck::shuffle()
{
    srand(time(0));
    int rNum;
    
    for (int i = 0; i <52; i++)
    {
        Card temp;
        rNum = rand()%52 + 1;
        temp = cardDeck[i];
        cardDeck[i] = cardDeck[rNum-1];
        cardDeck[rNum-1] = temp;
    }
}

void Deck::deal(Card H[][13], Player p[])
{
    int count = 0;
    for (int i = 0; i < 4; i++)
    {
        for (int j=0; j < 13; j++)
        {
            H[i][j].setSuit(cardDeck[count].getSuit());
            H[i][j].setNumber(cardDeck[count].getNumber());
            H[i][j].setDescription();
            count++;
        }
    }
}

void Deck::sort(Card H[][13])
{
    bool swap;
    Card temp;
    
    do
    {
        swap = false;
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 12; j++)
            {
                if (H[i][j] > H[i][j+1]==true)
                {
                    temp = H[i][j+1];
                    H[i][j+1] = H[i][j];
                    H[i][j] = temp;
                    swap = true;
                }
            }
        }
    }while (swap == true);
}

//Displays each hand of cards
void Deck::display(Card H[][13], Player pl[])
{
    cout << endl;
    for (int i = 0; i <4; i++)
    {
        cout << "Hand " << setw(16) << left << i+1;
    }
    cout << endl;
    
    for (int i = 0; i <4; i++)
    {
        cout << setw(21) << left << "------";
    }
    
    cout << endl;
    for (int i = 0; i < 4; i++)
    {
        cout  << "Points: " << setw(13) << left << pl[i].getPoints();
    }
    cout << endl << endl;
    
    for (int a = 0; a <13; a++)
    {
        for (int j = 0; j < 4; j++)
        {
            cout << setw(21)<< left<< H[j][a].getDescription();
        }
        cout << endl;
    }
}

void Deck::displayOri()
{
    for (int i = 0; i < 52; i++)
    {
        cout << cardDeck[i].getDescription() << endl;
    }
    cout << endl;
}

#endif /* deck_h */

