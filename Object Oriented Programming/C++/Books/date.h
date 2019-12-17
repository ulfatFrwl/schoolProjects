//
//  date.h
//  Homework 4
//
//  Created by Ulfat on 10/23/17.
//  Copyright © 2017 Ulfat Fruitwala. All rights reserved.
//

#ifndef date_h
#define date_h
#include <iostream>

using namespace std;

//Date class that calculates how many days a publication is checked out for and if it's overdue or not

class Date

{
private:
    
    int day, month, year;
    
public:
    
    Date()
    {
        day = 0;
        month = 0;
        year = 0;
    }
    
    void setDate (int D, int M, int Y)
    {
        day = D;
        month = M;
        year = Y;
    }
    
    void getDate()
    {
        cout << day << "-" << month << "-" << year << endl;
    }
    
    int operator-(const Date& date)
    {
        int startYear = 1800;
        int year1, month1, total1;
        int year2, month2, total2;
        int grandTotal;
        
        year1 = (year-startYear)*365;
        month1 = month*30;
        total1 = year1 + month1 + day;
        
        year2 = (date.year - startYear)*365;
        month2 = date.month*30;
        total2 = year2 + month2 + (date.day);
        
        grandTotal = total1 - total2;
        return grandTotal;
    }
    
    
};


#endif /* date_h */
