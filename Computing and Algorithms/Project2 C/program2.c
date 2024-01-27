// Parker Buszka
// CS 231 Project 2

//includes standard libraries and the custom
// myADT.h
#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include "stackADT.h"

// the main function that is used to execute the code
// it reads in the file names, opens them, creates a string from
// the inputed line, tokenizes it, and then puts that on a stack
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
    stackType *stack;
    stackType *returnedStack;

    // initializes the memory space for the string
    char line[size];

    // creates the stack
    stack = create();
    
    // creates a pointer for the tokenized line
    char *strPtr;

    // reads in each character of the input file into line
    while (fgets(line, size, input_pointer) != NULL) {
        // convert line to double, use tokenizer
        strPtr = strtok(line, " \n\t");
        while (strPtr != NULL) {
            // declares 3 dataType, so we can pop 2 numbers and do
            // the operator on them to then push the solution
            dataType first_num, second_num, solution;
            // this checks what operator strPtr is pointing too
            // or to push a double onto the stack
            // if there is an operator than it pops the top two values, 
            // does the operation, and pushes the solution back onto
            // the stack
            if (*strPtr == '+') {
                first_num = pop(stack);
                second_num = pop(stack);
                solution = second_num + first_num;
                returnedStack = push(solution, stack);
            } else if (*strPtr == '-') {
                first_num = pop(stack);
                second_num = pop(stack);
                solution = second_num - first_num;
                returnedStack = push(solution, stack);
            } else if (*strPtr == '*') {
                first_num = pop(stack);
                second_num = pop(stack);
                solution = second_num * first_num;
                returnedStack = push(solution, stack);
            } else if (*strPtr == '/') {
                first_num = pop(stack);
                second_num = pop(stack);
                solution = second_num / first_num;
                returnedStack = push(solution, stack);
            } else {
                stack = push(atof(strPtr), stack);
            }

            // so the tokenizer knows to continue
            strPtr = strtok(NULL, " \n\t");
        }
        
        // creates output string
        char output[size];
        
        // converts the dataType double from stack
        // to string
        sprintf(output, "%lf", peek(stack));
        strcat(output, "\n");
        fprintf(output_pointer, "%s", output);

    }  
    //frees all the memory 
    destroy(stack);
}