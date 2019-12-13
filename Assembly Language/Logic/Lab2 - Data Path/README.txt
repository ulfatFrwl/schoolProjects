Ulfat Fruitwala
ufruitwa
Fall 2019
Lab 2: Simple Data Path

--------------------
DESCRIPTION

In this lab, the user enters a two's complement number from a provided keypad input or a sum derived from the 
ALU (arithmetic logic unit) into 4 different registers. The user then can derive a sum of values held in
any 2 registers which can be a source of an input. 

--------------------
FILES

-
Lab2.lgi

This is a multimedia logic file where the solution circuit is built and can be run.


--------------------
INSTRUCTIONS

It is recommended that the first source of input is the keyboard. Simply toggle the "Write Register Address" 
switches to choose which register the value will be stored in. 
00 --> Register 0
01 --> Register 1
10 --> Register 2
11 --> Register 3
Choose a value from the keyboard and press update button.
Repeat the process again to store another value in another register.

To find a sum of those value, simple toggle the "Read Register 1" and "Read Register 2" address switches.
A sum of values stored in chosed 2 registers will be displayed in the "ALU Output" 7 segment LED.
This sum can be stored in a register to perform the next summation by turning on the "Store Select" switch to 1
and toggling the "Write Register Address".
If the summation causes an overflow such that 2 positive values sum up a negative value or vice verca, the overflow
LED will light up in the bottom right corner of page 1. 