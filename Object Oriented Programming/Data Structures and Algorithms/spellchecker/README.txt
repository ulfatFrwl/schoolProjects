Ufat Fruitwala
Data Structures and Algorithms
Spring 2018
Lab 3: Spelling Checker

------------------------------------------------------------------------
DESCRIPTION
Spelling checker is implemented by using a hash table. User is prompted
to enter a word. If word is spelled correctly, program outputs "no mistakes found". 
If word is spelled incorrectly, the program searches a programmer implemented
dictionary for similar words that can be obtained by 

- adding one character to the beginning		
- adding one character to the end
- remove one character from the beginning
- remove one character from the end
- exchanging adjacent characters 

of the misspelled word. These similar words are then printed on the console.

------------------------------------------------------------------------
CLASSES

- 
SpellChecker.java

This is the main class.

-
SeparateChaining.java
Hash table implementation of a dictionary. Dictionary is initialized by 
importing a file containing correctly spelled words.

-
checkSpelling.java
Spelling checker class that initializes an instance of the hash table.
User input words are evaluated in here. Suggestions for correctly
spelled words are also given here.

-
Queue.java
Characters of words are manipulated using a queue implementation.

-
SequentialSearch.java
Sequential search class implemented for searching the hash table.