//
//  game.h
//  Homework 6
//
//  Created by Ulfat on 12/3/17.
//  Copyright © 2017 Ulfat. All rights reserved.
//

#ifndef game_h
#define game_h
#include <iostream>
#include "player.h"
#include "deck.h"
#include "card.h"
#include "exception.h"
using namespace std;

class Game
{
private:
    
    Player *leading, *firstPlayer, *nextPlayer;
    int round = 0, playerNum, cardNum;
    Card tick[4], roundWin;
    
public:
    
    //Sets each player's hand
    void setPlayerandHand(Player p[], Card h[][13])
    {
        for (int i = 0; i<4; i++)
        {
            p[i].setHand(h);
        }
    }
    
    //Sets the leading player
    void setLeading(Card h[][13], Player p[])
    {
        for (int j = 0; j <4; j++)
        {
            for (int i = 0; i <13; i++)
            {
                if (h[j][i].getSuit()==Card::Suits::clubs && h[j][i].getNumber()==2)
                {
                    leading = &p[j];
                    round = 1;
                }
            }
        }
    }
    
    //returns leading player
    Player getLeading()
    {
        return *leading;
    }
    
    //sets first player
    void setFirstPlayer(Player p[])
    {
        for (int i = 0; i <4; i++)
        {
            if (p[i].getWin() == true)
            {
                firstPlayer = &p[i];
                round = 3;
            }
        }
    }
    
    //returns first player
    Player getFirstPlayer()
    {
        return *firstPlayer;
    }
    
    //returns next player
    Player getNext()
    {
        if (round == 1)
        {
            if (leading->number == 4)
                nextPlayer = leading-4;
            else if (leading->number >= 1 || leading-> number <4)
                nextPlayer = leading++;
            round = 2;
        }
        
        if (round==2)
        {
            if (nextPlayer->number == 4)
                nextPlayer = nextPlayer-3;
            else
                nextPlayer++;
        }
        
        if (round==3)
        {
            if (firstPlayer->number==4)
                nextPlayer = firstPlayer-3;
            else if (firstPlayer->number >=1 && firstPlayer->number <4)
                nextPlayer = firstPlayer+1;
            round = 2;
        }

        return *nextPlayer;
    }
    
    void setTick(Card h[][13])
    {
        static int counter = 0;
        tick[counter] = h[playerNum-1][cardNum-1];
        counter++;
    }
    
    void displayTick()
    {
        for (int i = 0; i<4; i++)
        {
            cout << tick[i].getDescription() << endl;
        }
    }
    
    bool validateTick(Card h[][13])
    {
        if (tick[0].getSuit() == h[playerNum-1][cardNum-1].getSuit())
            return true;
        else
            return false;
    }
    
    //Validates if cards other than hearts are available or not
    bool validateCards (Card h[][13])
    {
        for (int i = 0; i < 13; i++)
        {
            if(h[playerNum-1][i].getSuit()!=Card::Suits::clubs && h[playerNum-1][i].getSuit()!=Card::Suits::diamonds && h[playerNum-1][i].getSuit()!=Card::Suits::spades)
            {
                return false;
            }
        }
        return true;
    }
    
    bool validateHeart(Card h[][13])
    {
        if(h[playerNum-1][cardNum-1].getSuit() != Card::Suits::hearts)
            return true;
        else
            return false;
    }
    
    void startNextRound(Card h[][13], Player p[])
    {
        cout << endl;
        setFirstPlayer(p);
        playerNum = getFirstPlayer().number;
        cout << "Player " << playerNum << " play card: ";
        cin >> cardNum;
        setTick(h);
        
        try
        {
            for (int i = 0; i<3; i++)
            {
                playerNum = getNext().number;
                cout << "Player " << playerNum << " play card: ";
                cin >> cardNum;
                if (validateHeart(h) == false)
                {
                    Exception error2(Exception::error::heart);
                    error2.printError();
                    break;
                }
                if (validateTick(h)==false)
                {
                    Exception error1(Exception::error::suit);
                    error1.printError();
                    break;
                }
                setTick(h);
            }
        }
        catch (string obj1)
        {
            cout << obj1 << endl;
        }
        
        catch (string obj2)
        {
            cout << obj2 << endl;
        }
        cout << endl;
        displayTick();
        setRoundWinner();
        getWinner(p, h);
        updateHand(h);
    }
    
    void startRound1(Card h[][13], Player p[])
    {
        cout << endl;
        setLeading(h, p);
        playerNum = getLeading().number;
        cout << "Player " << playerNum << " play card: ";
        cin >> cardNum;
        setTick(h);
        
        try
        {
            for (int i = 0; i<3; i++)
            {
                playerNum = getNext().number;
                cout << "Player " << playerNum << " play card: ";
                cin >> cardNum;
                if (validateHeart(h) == false)
                {
                    Exception error2(Exception::error::heart);
                    error2.printError();
                    break;
                }
                if (validateTick(h)==false)
                {
                    Exception error1(Exception::error::suit);
                    error1.printError();
                    break;
                }
                
                setTick(h);
            }
        }
        catch (string obj1)
        {
            cout << obj1 << endl;
        }
        
        catch (string obj2)
        {
            cout << obj2 << endl;
        }
        cout << endl;
        displayTick();
        setRoundWinner();
        getWinner(p, h);
        updateHand(h);
    }
    
    void setRoundWinner()
    {
        Card biggest=tick[0];
        for (int i = 0; i < 3; i++)
        {
            if (tick[i+1]>biggest)
                biggest = tick[i+1];
        }
        roundWin = biggest;
    }
    
    Card getRoundWin()
    {
        return roundWin;
    }
    
    void getWinner(Player p[], Card h[][13])
    {
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j <13; j++)
            {
                if(h[i][j].getSuit()==roundWin.getSuit() && h[i][j].getNumber()==roundWin.getNumber())
                {
                    p[i].setWin();
                    p[i].setPoints(tick);
                    cout << endl;
                    cout << "Player " << i+1 << " won round" << endl;
                }
            }
        }
    }
    
    void updateHand(Card h[][13])
    {
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j<13; j++)
            {
                if(h[i][j].getSuit()==tick[i].getSuit() && h[i][j].getNumber()==tick[i].getNumber())
                {
                    h[i][j].setSuit(Card::Suits::null);
                    h[i][j].setNumber(0);
                    h[i][j].setDescription();
                }
            }
        }
    }
    
};

#endif /* game_h */
