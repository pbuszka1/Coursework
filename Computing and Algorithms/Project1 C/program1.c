// Parker Buszka CS 231
// Project 1 program 1

#include <stdio.h> //for input & output
// string librarys
#include <ctype.h> 
#include <string.h> 

// main function that is the starting point for execution
// where this file reads in a text file,
// changes the case of the characters
// and outputs them to the output file
int main(int argc, char *argv[]) {
    // gets the first and second text file names
    char *input_str = argv[1];
    char *output_str = argv[2];

    // sets the size that will be used for the string to read the input file
    // adds one because it needs the terminating '\0' character at the end 
    int size = 101;

    // creates the pointers to the textfiles
    FILE *input_pointer;
    FILE *output_pointer;
    
    // access the text files and sets the pointer to them
    input_pointer = fopen(input_str, "r");
    output_pointer = fopen(output_str, "w");

    // initializes the memory space for the string
    char line[size];

    // while loop to read each line of the input file until there are no more
    while (fgets(line, size, input_pointer) != NULL) {
        // checks the case of the input character and reverses it
        for (int num = 0; num < strlen(line); num++) {
            if (isupper(line[num])) {
                line[num] = tolower(line[num]);
            } else if (islower(line[num])) {
                line[num] = toupper(line[num]);
            }
        }
        //puts the inversed case string to the output file
        fputs(line, output_pointer);
    }    
}
