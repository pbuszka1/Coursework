// Parker Buszka
// CS 231 Project 3

// used libraries
#include <stdio.h>
#include <ctype.h>

// defines the 3 constants for the states for the state machine
#define START_STATE 0
#define A_AFTER 1
#define NON_A_AFTER 2

// in this main method we read in the text from the input file
// to then print each alphabetical character to stdout, skipping over
// all non alphabetical characters using a state machine
int main(int argc, char *argv[]) {
    // gets input files name
    char *input_str = argv[1];
    
    // initializes the state to START_STATE
    int state = START_STATE;

    // creates the pointers to the textfiles
    FILE *input_pointer;
    
    // access the text files and sets the pointer to them
    input_pointer = fopen(input_str, "r");
    
    // gets character from the input file
    char current_in = fgetc(input_pointer);

    // while loop holds the state machine, and reads each character in,
    // changes its state when needed, and ends when we reach the end of file
    while (current_in != EOF)  {
        switch (state) {
            // this is the state the program starts at
            case START_STATE:
            // if there is an alphabetical character after the previous
            case A_AFTER:
                if(isalpha(current_in)) {
                    putchar(tolower(current_in)); // prints the char to stdout
                    state = A_AFTER;
                } else {
                    state = NON_A_AFTER;
                }
                break;
            // if there is not alphabetical character after the previous
            case NON_A_AFTER:
                if(isalpha(current_in)) {
                    putchar('\n'); // to give the newline character to separate these words
                    putchar(tolower(current_in)); // prints the char to stdout
                    state = A_AFTER;
                } else {
                    state = NON_A_AFTER;
                }
                break;
        }
        // gets input from FILE pointer
        current_in = fgetc(input_pointer);
    }
}