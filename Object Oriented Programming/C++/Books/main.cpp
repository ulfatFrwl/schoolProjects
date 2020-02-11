//
//  main.cpp
//  Homework 4
//
//  Created by Ulfat on 10/18/17.
//  Copyright © 2017 Ulfat Fruitwala. All rights reserved.
//

#include <iostream>
#include <cstring>
#include "publication.h"
#include "person.h"
#include "book.h"
#include "music.h"
#include "video.h"
#include "date.h"
using namespace std;

const int personSz = 4;
const int bookSz = 2;
const int musicSz = 2;
const int videoSz = 2;
const int overdueDay = 21;

void displayMenu ();
void displayPerson (Person[]);
void displayPub (Book[], Music[], Video[]);
void editPerson (Person []);
void editPub (Book[], Music[], Video[]);
void editBook (Book[]);
void editMusic (Music[]);
void editVideo (Video[]);
void checkOut (Book[], Music[], Video[], Person[], Date);
void checkOutB (Book[], Person[], Date);
void checkOutM (Music[], Person[], Date);
void checkOutV (Video[], Person[], Date);
void checkIn (Book[], Music[], Video[]);
void checkInB (Book[]);
void checkInM (Music[]);
void checkInV (Video[]);
void publicationDUE (Book[], Music[], Video[], Date);



int main()
{
    int day, month, year;
    cout << "Enter today's date (DD/MM/YYYY)" << endl;
    cin>> day >> month >> year;
    
    Date dateToday;
    
    dateToday.setDate(day, month, year);
    
    //Creating 4 person objects
    Person people[personSz] = { Person("Ulfat Fruitwala", 111, "uf2996@gmail.com"),
                        Person ("Nick Tao", 222, "xunhaotao@gmail.com"),
                        Person ("Rita Chuang", 333, "ritachuang@gmail.com"),
                        Person ("Louiesa Hao", 444, "lHao@gmail.com") };
    
    //Creating 2 book objects
    Book book[bookSz] = { Book("The Defining Decade", "Meg Jay", 236, "Digital"), Book("Angels and Demons", "Dan Brown", 500, "softcover")};
    
    //Creating 2 music objects
    Music music[musicSz] = { Music("Born to Die", "Lana Del Rey", 286, "mp3"), Music("Instant Crush", "Daft Punk", 338, "AAV")};
    
    //Creating 2 video objects
    Video video[videoSz] = { Video("Cats", "FurryFriends", "high", "PetCo"), Video("How to make cookies", "Tasty", "low", "TastyCo")};
    
    int choice;

    do
    {
        displayMenu();
        cout << endl;
        cout << "Choose an option 1-8" << endl;
        cin >> choice;
        
        switch (choice)
        {
            case 1:
            {
                displayPerson(people);
                break;
            }
                
            case 2:
            {
                displayPub(book, music, video);
                break;
            }
                
            case 3:
            {
                editPerson(people);
                break;
            }
                
            case 4:
            {
                editPub(book, music, video);
                break;
            }
                
            case 5:
            {
                checkOut(book, music, video, people, dateToday);
                break;
            }
                
            case 6:
            {
                checkIn(book, music, video);
                break;
            }
                
            case 7:
            {
                publicationDUE(book, music, video, dateToday);
                break;
            }
                
            case 8:
            {
                break;
            }
        }
    
    } while(choice != 8);
            

}

//Function that displays menu for user to choose from
void displayMenu ()
{
    cout << "MENU" << endl;
    cout << "1.Display people" << endl;
    cout << "2.Display publications" << endl;
    cout << "3.Edit people" << endl;
    cout << "4.Edit publication" << endl;
    cout << "5.Check out publication" << endl;
    cout << "6.Check in publication" << endl;
    cout << "7.Display publication overdue" << endl;
    cout << "8.Quit" << endl;

}

//Function displays each person's information
void displayPerson (Person people[])
{
    for (int i = 0; i < personSz; i++)
    {
        cout << "Person " << (i+1) << endl;
        cout << "Name: " << people[i].getName() << endl;
        cout << "ID Number: " << people[i].getID() << endl;
        cout << "Email address: " << people[i].getEmail() << endl;
        cout << endl;
    }
}

//Function displays each publication's information
void displayPub (Book book[], Music music[], Video video[])
{
    
    for (int i = 0; i < bookSz; i++)
    {
        cout << "Book " << (i+1) << endl;
        cout << "Title: " << book[i].getTitle() << endl;
        cout << "Author: " << book[i].getAuthor() << endl;
        cout << book[i].getPages() << " pages" << endl;
        cout << "Format: " << book[i].getFormat() << endl;
        book[i].displayBorrower();
        cout << endl;
    }
    
    cout << endl;
    
    for (int i = 0; i < musicSz; i++)
    {
        cout << "Music " << (i+1) << endl;
        cout << "Title: " << music[i].getTitle() << endl;
        cout << "Author: " << music[i].getAuthor() << endl;
        cout << "Duration: " << music[i].getDuration() << " seconds" << endl;
        cout << "Format: " << music[i].getFormat() << endl;
        music[i].displayBorrower();
        cout << endl;
    }
    
    cout << endl;
    
    for (int i = 0; i < videoSz; i++)
    {
        cout << "Video " << (i+1) << endl;
        cout << "Title: " << video[i].getTitle() << endl;
        cout << "Author: " << video[i].getAuthor() << endl;
        cout << video[i].getResolution() << " resolution" << endl;
        cout << "Producer: " << video[i].getProducer() << endl;
        video[i].displayBorrower();
        cout << endl;
    }
    
}

//Function initiates a menu to edit each person's attributes
void editPerson (Person people[])
{
    int personNumber;
    int choice;
    string change;
    string newName;
    int newID;
    string newEmail;
    
    for (int i = 0; i < personSz; i++)
    {
        cout << i+1 << ". " << people[i].getName() << endl;
    }
    cout << "Which person's information would you like to change? Enter 1 - " << personSz << endl;
    cin >> personNumber;
    
    do
    { cout << endl;
        
        cout << "What would you like to change about " << people[personNumber-1].getName() << "? Enter 1 - 4" << endl;
        cout << "1. Full name" << endl;
        cout << "2. ID number" << endl;
        cout << "3. Email address" << endl;
        cout << "4. Don't change anything" << endl;
        cin >> choice;
        
        //Switch statement for all options available to users to perform
        switch (choice)
        {
            case 1:
                cout << "Enter new full name: ";
                cin.ignore();
                getline(cin, newName);
                people[personNumber-1].setName(newName);
                break;
                
            case 2:
                cout << "Enter new ID number ";
                cin >> newID;
                people[personNumber-1].setID(newID);
                break;
                
            case 3:
                cout << "Enter new email address ";
                cin >> newEmail;
                people[personNumber-1].setEmail(newEmail);
                break;
                
            case 4:
                break;
        }
        
        cout << endl;
        cout << "Would you like to change something else?" << endl;
        cout << "Enter 'y' or 'n' ";
        cin >> change;
        cout << endl;
        
    } while (change == "y" || change == "Y");
    
}

//Function edits publications
void editPub (Book book[], Music music[], Video video[])
{
    char choice;
    
    cout << "What publication would you like to edit? " << endl;
    cout << "Enter 'B' for book, 'M' for music, 'V' for video" << endl;
    cin >> choice;
    
    if (choice == 'B' || choice == 'b')
    {
        editBook(book);
    }
    
    if (choice == 'M' || choice == 'm')
    {
        editMusic(music);
    }
    
    if (choice == 'V' || choice == 'v')
    {
        editVideo(video);
    }
    
}

//subfunction edits book object
void editBook(Book book[])
{
    int bookChoice, choice, newPages;
    string newTitle, newAuthor, newFormat;
    char redoCh;
    
    for (int i = 0; i < bookSz; i++)
    {
        cout << (i+1) << ". " << book[i].getTitle() << endl;
    }
    cout << "Choose book. Enter 1 - " << bookSz << endl;
    cin >> bookChoice;
    
    do
    {
            cout << "1. Title" << endl << "2. Author" << endl << "3. Pages" << endl << "4. Format" << endl << "5. Quit" << endl;
            cout << "Enter 1 - 5:";
            cin >> choice;
    
            switch (choice)
            {
                case 1:
                {
                    cout << "Enter new title" << endl;
                    cin.ignore();
                    getline(cin, newTitle);
                    book[bookChoice-1].setTitle(newTitle);
                    break;
                }
                
                case 2:
                {
                    cout << "Enter new author" << endl;
                    cin.ignore();
                    getline(cin, newAuthor);
                    book[bookChoice-1].setAuthor(newAuthor);
                    break;
                }
                   
                case 3:
                {
                    cout << "Enter new number of pages" << endl;
                    cin >> newPages;
                    book[bookChoice-1].setPages(newPages);
                    break;
                }
                    
                case 4:
                {
                    cout << "Enter new format, choose from 'hardcover', 'softcover', 'digital'." << endl;
                    cin >> newFormat;
                    book[bookChoice-1].setFormat(newFormat);
                    break;
                }
                    
                case 5:
                    break;
            }
           
            cout << "Would you like to change some else about this book? Enter 'Y' or 'N'" << endl;
            cin >>redoCh;
                    
    }while(redoCh == 'y'|| redoCh == 'Y');

}

//subfunction edits music object
void editMusic(Music music[])
{
    int musicChoice, choice, newDur;
    string newTitle, newAuthor, newFormat;
    char redoCh;
    
    for (int i = 0; i < musicSz; i++)
    {
        cout << (i+1) << ". " << music[i].getTitle() << endl;
    }
    cout << "Choose music. Enter 1 - " << musicSz << endl;
    cin >> musicChoice;
    
    do
    {
        cout << "1. Title" << endl << "2. Author" << endl << "3. Duration" << endl << "4. Format" << endl << "5. Quit" << endl;
        cout << "Enter 1 - 5:";
        cin >> choice;
        
        switch (choice)
        {
            case 1:
            {
                cout << "Enter new title" << endl;
                cin.ignore();
                getline (cin, newTitle);
                music[musicChoice-1].setTitle(newTitle);
                break;
            }
                
            case 2:
            {
                cout << "Enter new author" << endl;
                cin.ignore();
                getline (cin, newAuthor);
                music[musicChoice-1].setAuthor(newAuthor);
                break;
            }
                
            case 3:
            {
                cout << "Enter new duration" << endl;
                cin >> newDur;
                music[musicChoice-1].setDuration(newDur);
                break;
            }
                
            case 4:
            {
                cout << "Enter new format, choose from 'mp3', 'AAV', 'WAV'." << endl;
                cin >> newFormat;
                music[musicChoice-1].setFormat(newFormat);
                break;
            }
                
            case 5:
                break;
        }
        
        cout << "Would you like to change some else about this book? Enter 'Y' or 'N'" << endl;
        cin >>redoCh;
        
    } while (redoCh == 'y' || redoCh == 'Y');

}

//subfunction edits video object
void editVideo(Video video[])
{
    int videoChoice, choice;
    string newTitle, newAuthor, newReso, newProducer;
    char redoCh;
    
    for (int i = 0; i < videoSz; i++)
    {
        cout << (i+1) << ". " << video[i].getTitle() << endl;
    }
    cout << "Choose video. Enter 1 - " << videoSz << endl;
    cin >> videoChoice;
    
    do
    {
        cout << "1. Title" << endl << "2. Author" << endl << "3. Resolution" << endl << "4. Producer" << endl << "5. Quit" << endl;
        cout << "Enter 1 - 5:";
        cin >> choice;
        
        switch (choice)
        {
            case 1:
            {
                cout << "Enter new title" << endl;
                cin.ignore();
                getline (cin, newTitle);
                video[videoChoice-1].setTitle(newTitle);
                break;
            }
                
            case 2:
            {
                cout << "Enter new author" << endl;
                cin.ignore();
                getline (cin, newAuthor);
                video[videoChoice-1].setAuthor(newAuthor);
                break;
            }
                
            case 3:
            {
                cout << "Enter new resolution, choose from 'low', 'high', '4K'." << endl;
                cin >> newReso;
                video[videoChoice-1].setResolution(newReso);
                break;
            }
                
            case 4:
            {
                cout << "Enter new producer" << endl;
                cin.ignore();
                getline (cin, newProducer);
                video[videoChoice-1].setProducer(newProducer);
                break;
            }
                
            case 5:
                break;
        }
        
        cout << "Would you like to change some else about this book? Enter 'Y' or 'N'" << endl;
        cin >>redoCh;
        
    } while (redoCh == 'y' || redoCh == 'Y');
    
}

//Function checkouts a publication
void checkOut (Book book[], Music music[], Video video[], Person person[], Date dateToday)
{
    char choice;
    
    cout << "What publication would you like to check out? " << endl;
    cout << "Enter 'B' for book, 'M' for music, 'V' for video" << endl;
    cin >> choice;
    
    if (choice == 'B' || choice == 'b')
    {
        checkOutB(book, person, dateToday);
    }
    
    if (choice == 'M' || choice == 'm')
    {
        checkOutM(music, person, dateToday);
    }
    
    if (choice == 'V' || choice == 'v')
    {
        checkOutV(video, person, dateToday);
    }
        
}

//subfunction checkouts book object
void checkOutB (Book book[], Person person[], Date dateToday)
{
    int bookChoice, idNUM;
    
    for (int i = 0; i < bookSz; i++)
    {
        cout << (i+1) << ". " << book[i].getTitle() << endl;
    }
    cout << "Choose book. Enter 1 - " << bookSz << endl;
    cin >> bookChoice;
    
        if (book[bookChoice-1].checkedOut)
        {
            cout << "This book is not available, please try again" << endl;
            cin.ignore();
            cout << endl;
        }
    
        else
        {
            cout << "What is your ID number?" << endl;
            cin >> idNUM;
            
            for (int i = 0; i < personSz; i++)
            {
                if (idNUM == (person[i].getID()))
                {
                    book[bookChoice-1].checkOut(person[i]);
                    book[bookChoice-1].setCustomCheckOut(18, 7, 2017);
                    //book[bookChoice-1].setCheckOut(dateToday);
                }
            }
        }
}

//subfunction checkouts music object
void checkOutM (Music music[], Person person[], Date dateToday)
{
    int musicChoice, idNUM;
    
    for (int i = 0; i < musicSz; i++)
    {
        cout << (i+1) << ". " << music[i].getTitle() << endl;
    }
    cout << "Choose music. Enter 1 - " << musicSz << endl;
    cin >> musicChoice;
    
        if (music[musicChoice-1].checkedOut)
        {
            cout << "This music is not available, please try again" << endl;
            cin.ignore();
            cout << endl;
        }
    
        else
        {
            cout << "What is your ID number?" << endl;
            cin >> idNUM;
            
            for (int i = 0; i < personSz; i++)
            {
                if (idNUM == (person[i].getID()))
                {
                    music[musicChoice-1].checkOut(person[i]);
                    music[musicChoice-1].setCheckOut(dateToday);
                }
            }
        }
}

//subfunction checkouts video object
void checkOutV (Video video[], Person person[], Date dateToday)
{
    int videoChoice, idNUM;
    
    for (int i = 0; i < videoSz; i++)
    {
        cout << (i+1) << ". " << video[i].getTitle() << endl;
    }
    cout << "Choose video. Enter 1 - " << videoSz << endl;
    cin >> videoChoice;
    
        if (video[videoChoice-1].checkedOut)
        {
            cout << "This video is not available, please try again" << endl;
            cin.ignore();
            cout << endl;
        }
    
        else
        {
            cout << "What is your ID number?" << endl;
            cin >> idNUM;
            
            for (int i = 0; i < personSz; i++)
            {
                if (idNUM == (person[i].getID()))
                {
                    video[videoChoice-1].checkOut(person[i]);
                    video[videoChoice-1].setCheckOut(dateToday);
                }
            }
        }
}

//Function checkins publications
void checkIn(Book book[], Music music[], Video video[])
{
    char choice;
    
    cout << "What publication would you like to check in? " << endl;
    cout << "Enter 'B' for book, 'M' for music, 'V' for video" << endl;
    cin >> choice;
    
    if (choice == 'B' || choice == 'b')
    {
        checkInB(book);
    }
    
    if (choice == 'M' || choice == 'm')
    {
        checkInM(music);
    }
    
    if (choice == 'V' || choice == 'v')
    {
        checkInV(video);
    }
}

//subfunction checkins book objects
void checkInB (Book book[])
{
    int bookChoice;
    
    for (int i = 0; i < bookSz; i++)
    {
        cout << (i+1) << ". " << book[i].getTitle() << endl;
    }
    cout << "Choose book. Enter 1 - " << bookSz << endl;
    cin >> bookChoice;
    
    book[bookChoice-1].checkIn();
    
}

//subfunction checkins music objects
void checkInM (Music music[])
{
    int musicChoice;
    
    for (int i = 0; i < musicSz; i++)
    {
        cout << (i+1) << ". " << music[i].getTitle() << endl;
    }
    cout << "Choose music. Enter 1 - " << musicSz << endl;
    cin >> musicChoice;
    
    music[musicChoice-1].checkIn();
    
}

//subfunction checkins video objects
void checkInV (Video video[])
{
    int videoChoice;
    
    for (int i = 0; i < videoSz; i++)
    {
        cout << (i+1) << ". " << video[i].getTitle() << endl;
    }
    cout << "Choose video. Enter 1 - " << bookSz << endl;
    cin >> videoChoice;
    
    video[videoChoice-1].checkIn();
    
}

//Function displays if each publication is overdue or not
void publicationDUE (Book book[], Music music[], Video video[], Date dateToday)
{
    //publication overdue check for book objects
    
    cout << "BOOKS" << endl;
    for (int i = 0; i < bookSz; i++)
    {
        if (book[i].checkedOut)
        {
            int dateDiff = dateToday-(book[i].getCheckOut());
            int dateDiff2 = dateDiff-21;
            
            cout << book[i].getTitle() << ": ";
            
            if (dateDiff <= overdueDay)
            {
                cout << "Publication not overdue" << endl;
            }
            
            else
            {
                cout << "Publication is overdue by " << dateDiff2 << " days" << endl;
            }
            cout << endl;
        }
        
        else
        {
            cout << book[i].getTitle() << ": not borrowed yet" << endl;
            cout << endl;
        }
        cout << endl;
    }
    
    //publication overdue check for music objects
    
    cout << "MUSIC" << endl;
    for (int i = 0; i < musicSz; i++)
    {
        if (music[i].checkedOut)
        {
            int dateDiff = dateToday-(book[i].getCheckOut());
            int dateDiff2 = dateDiff-21;
            
            cout << music[i].getTitle() << ": ";
            
            if (dateDiff <= overdueDay)
            {
                cout << "Publication not overdue" << endl;
            }
            
            else
            {
                cout << "Publication is overdue by " << dateDiff2 << " days" << endl;
            }
            cout << endl;
        }
        
        else
        {
            cout << music[i].getTitle() << ": not borrowed yet" << endl;
            cout << endl;
        }
        cout << endl;
    }
    
    //publication overdue check for video objects
    
    cout << "VIDEOS" << endl;
    for (int i = 0; i < videoSz; i++)
    {
        if (video[i].checkedOut)
        {
            int dateDiff = dateToday-(book[i].getCheckOut());
            int dateDiff2 = dateDiff-21;
            
            cout << video[i].getTitle() << ": ";
            
            if (dateDiff <= overdueDay)
            {
                cout << "Publication not overdue" << endl;
            }
            
            else
            {
                cout << "Publication is overdue by " << dateDiff2 << " days" << endl;
            }
            cout << endl;
        }
        
        else
        {
            cout << video[i].getTitle() << ": not borrowed yet" << endl;
            cout << endl;
        }
        cout << endl;
    }
}
