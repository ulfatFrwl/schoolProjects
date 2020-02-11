//
//  main.cpp
//  Homework 6
//
//  Created by Ulfat on 12/2/17.
//  Copyright © 2017 Ulfat. All rights reserved.
//

#include <iostream>
#include "deck.h"
#include "card.h"
#include "player.h"
#include "exception.h"
#include "game.h"
#include <iomanip>
#include <string>

using namespace std;

void playCard (Card h[][13], Card acc[], int player, int number, int turn);




int main()
{
    Game one;
    Card hand1[4][13], ticks[13][4];
    Player plays[4] = {1, 2,  3,  4};
    Deck deck1;
    int cardNumber, playerNumber, cardsAccum[4];
    
    deck1.shuffle();
    deck1.deal(hand1,plays);
    deck1.sort(hand1);
    deck1.display(hand1,plays);
    
    one.startRound1(hand1, plays);
    deck1.display(hand1, plays);
    one.startNextRound(hand1, plays);
    deck1.display(hand1, plays);
    cout << one.validateCards(hand1) << endl;
    
   
    cout << endl << endl;
    
    
   

    
}



