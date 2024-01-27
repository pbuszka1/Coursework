// Parker Buszka
// CS 231 Project 2

//includes standard libraries and the custom
// myADT.h
#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include "stackADT.h"

// is used to check if the line is a palindrome
// by peeking the last value of line and comparing 
// it to the first value of line, and if they are 
// equal the function continues and returns 1 if it is
// a palindrome or 0 if it isnt
int isPalindrome (dataType *line, stackType *stack) {
    for (int num = 0; num < strlen(line); num++) {
        if (!isalpha(peek(stack)))
            pop(stack);
        if (isspace(line[num]) || !isalpha(line[num]))
            continue; // to go to next iteration of the loop
        if (tolower(line[num]) == tolower(peek(stack))) { // to makesure that case is not an issue
            pop(stack);
        } else {
            return 0;
        }
    }
    return 1;
}

// the main function that is used to execute the code
// it reads in the file names, opens them, creates a string from
// the inputed line, creates and populates a stack, then checks
// if they are a palindrome
int main(int argc, char *argv[]) {
    // gets the first and second text file names
    char *input_str = argv[1];
    char *output_str = argv[2];

    // sets the size that will be used for the string to read the input file
    // adds two because it needs the terminating '\0' character at the end and the \n character
    int size = 82;

    // creates the pointers to the textfiles
    FILE *input_pointer;
    FILE *output_pointer;
    
    // access the text files and sets the pointer to them
    input_pointer = fopen(input_str, "r");
    output_pointer = fopen(output_str, "w");
    
    // intializes the stack and a stack to return 
    // which will house the inputed string in reverse
    // order
    stackType *stack;
    stackType *returnedStack;

    // initializes the memory space for the string
    dataType line[size];

    // creates the stack
    stack = create();

    // reads in each character of the input file into the string line
    while (fgets(line, size, input_pointer) != NULL) {
        // adds each character of the line to the stack
        for (int num = 0; num < strlen(line); num++) {
            returnedStack = push(line[num], stack);
        }
        // gets ride of newline character
        line[strcspn(line, "\n")] = 0;

        // checks if the line is a palindrome
        if (isPalindrome(line, stack) == 1) {
            strcat(line, " is a palindrome\n");
            // fputs(line, output_pointer);
            fprintf(output_pointer, "%s", line);
        } else {
            strcat(line, " is not a palindrome\n");
            // fputs(line, output_pointer);
            fprintf(output_pointer, "%s", line);
        }
    }  
    // frees all the memory 
    destroy(stack);
}