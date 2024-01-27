// Parker Buszka CS 231
// Project 2 program 1

#include <stdio.h>

// main function that is the starting point for the execution
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

    // checks if the character is uppercase by placing it between the ASCII values
    // that represent all of the captial letters
    int is_char_upper(char character) {
        if (character >= 65 && character <= 90)
            return 1;
        else 
            return 0;
    }

    // checks if the character is lowercase by placing it between the ASCII values
    // that represent all of the lowercase letters
    int is_char_lower(char character) {
        if (character >= 97 && character <= 122)
            return 1;
        else 
            return 0;
    }

    // adds 32 to the character to change the case (32 is the distance from A to a 
    // on the ASCII table)
    char to_lower(char character) {
        return character+32;
    }

    // subtracts 32 to the character to change the case (32 is the distance from A to a 
    // on the ASCII table)
    char to_upper(char character) {
        return character-32;
    }

    // gets the length of the string by incrementing a counter until 
    // the null string ending character
    int string_length(char *string) {
        int count = 0;
        while (string[count] != '\0') {
            count++;
        }
        return count;
    }

    // while loop to read each line of the input file until there are no more
    while (fgets(line, size, input_pointer) != NULL) {
        // checks the case of the input character and reverses it
        for (int num = 0; num < string_length(line); num++) {
            if (is_char_upper(line[num])) {
                line[num] = to_lower(line[num]);
            } else if (is_char_lower(line[num])) {
                line[num] = to_upper(line[num]);
            }
        }
        //puts the inversed case string to the output file
        fputs(line, output_pointer);
    }    
}
