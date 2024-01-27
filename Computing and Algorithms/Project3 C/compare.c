// Parker Buszka
// CS 231 Project 3

// the include statements for the libraries that are being used
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

// this is for printing if it is a word
void print_is_there(char *lex, FILE *spell_input_pointer) {
    lex[strcspn(lex, "\n")] = 0; // this gets rid of the newline character
    fprintf(spell_input_pointer, "%s is a word\n", lex);
}

// this is for printing if it is not a word
void print_isnt_there(char *lex, FILE *spell_input_pointer) {
    lex[strcspn(lex, "\n")] = 0; // this gets rid of the newline character
    fprintf(spell_input_pointer, "                      %s is not a word\n", lex);
} // a lot of space in the fprintf "" so we can make another column for non words

// this is the main method, where we will compare the lex input with the dictionary input
// and then write out the correct output to the report.txt
int main(int argc, char *argv[]) {
    char *input = argv[1]; // reads in the dictionary text file
    char input_array[81]; //creates memory space for the string for lex
    char dic_array[81]; // creates memory space for dic string
    char *lex = input_array; // creates pointer for lex string
    char *dic = dic_array; // creates pointer for dic string
    size_t size = 81; // intializes the size for getline()
    int still_new_text; // this is used in the while loop to check if there is more stdin from lex

    // creates the pointers to the textfiles
    FILE *dic_input_pointer;
    FILE *spell_input_pointer;
    
    // access the text files and sets the pointer to them
    dic_input_pointer = fopen(argv[1], "r");
    spell_input_pointer = fopen(argv[2], "w");

    // these get the first instance of lex and dic, to load something
    // intially so the logic in the while loop has a starting point
    getline(&lex, &size, stdin);
    getline(&dic, &size, dic_input_pointer);

    // houses logic to run the strcasecmp logic, so the logic will continue while there
    // is still new text to process
    while (still_new_text != EOF) { // checks to see if there is still new text from lex to process
        if (strcasecmp(lex, dic) == 0) {
            /* they are the same word, so we print them out*/
            print_is_there(lex, spell_input_pointer);
            still_new_text = getline(&lex, &size, stdin);
        } else if (strcasecmp(lex, dic) > 0) {
            /* restart comparison with same lex and next dic */
            getline(&dic, &size, dic_input_pointer);
        } else if (strcasecmp(lex, dic) < 0) {
            /* restart comparison with same dic and next lex  */
            print_isnt_there(lex, spell_input_pointer);
            still_new_text = getline(&lex, &size, stdin);
        }
    } 
}