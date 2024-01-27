Parker Buszka
CS 231 Project 2

Hello Professor!

This file provide the instructions for how to correctly use program1.c and program2.c.

stackADT.h creates the nodes for the stack to work and the stack header.
stack.c creates the methods for the stack to work. 
program1.c solves checks if the input is a palindrome and 
program2.c solves postfix expressions.
MyDoubleType.h defines the dataType needed for program2.c and MyCharType.h 
defines the dataType needed for program1.c.

Steps for program1.c
1) In stackADT.h comment out the #include "MyDoubleType.h" and have #include "MyCharType.h" included
2) in terminal type gcc stack.c program1.c
3) then in terminal type ./a.out input.txt output.txt
4) then open the output file to see the results

Steps for program2.c
1) In stackADT.h comment out the #include "MyCharType.h" and have #include "MyDoubleType.h" included
2) in terminal type gcc stack.c program2.c
3) then in terminal type ./a.out input_postfix.txt output_postfix.txt
4) then open the output file to see the results