Ulfat Fruitwala
ufruitwa
Fall 2019
Lab 1: Intro to Logic Simulation


-----------
DESCRIPTION

This lab is an introductory lab for Multimedia Logic (MML), a schematic entry logic simulation program. 
There are two truth tables in the lab instructions and circuits are to be developed based on the truth tables.

Part B truth table:

in_3 in_2 in_1 in_0 | b_2 b_1 b_0
 0    0    0    0   |  0   0   0
 0    0    0    1   |  0   0   1
 0    0    1    0   |  1   0   0
 0    0    1    1   |  0   0   1
 0    1    0    0   |  0   0   0
 0    1    0    1   |  0   0   1
 0    1    1    0   |  1   0   0
 0    1    1    1   |  0   0   1
 1    0    0    0   |  0   0   0
 1    0    0    1   |  0   0   1
 1    0    1    0   |  1   0   0
 1    0    1    1   |  0   0   1
 1    1    0    0   |  0   0   0
 1    1    0    1   |  0   0   1
 1    1    1    0   |  1   0   0
 1    1    1    1   |  0   0   1

Three circuits can be developed from the truth table above. Assume in_3 = a, in_2 = b, in_1 = c, in_0 = d

1. b_2: off	b_1: off     b_0: off
   
   Simplified SOP: c'd'

2. b_2: on	b_1: off     b_0: off

   Simplified SOP: cd'

3. b_2: off	b_1: off     b_0: on

   Simplified SOP: d

 **************************

Part C truth table: Given

in_2 in_1 in_0 | c_0
  0    0    0     1
  0    0    1     1
  0    1    0     0
  0    1    1     1
  1    0    0     0
  1    0    1     1
  1    1    0     0
  1    1    1     0

Assume in_2 = a, in_1 = b, in_0 = c

Simplified SOP: a'b' + a'bc + ab'c

3 versions of the same function is implemented in the Lab1.lgi file; different logic gates restrictions are placed for each 3.
Note: output for first version is c_0, second version is c_1, third version is c_2

-----------
FILES

-
Lab1.lgi
(Multimedia Logic file)

Includes all the circuitry from the truth tables mentioned above. 

-

------------
INSTRUCTIONS
To simulate the circuits, navigate the switches on page one of Lab1.lgi file. Follow the
Os and 1s for each corresponding switches in each of the truth table and match the output.




