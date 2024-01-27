/**************************************************************/
/* Parker Buszka */
/* CS-102, Winter 2022 */
/* Programming Assignment 3 */
/* Prog1 class: interacts with the user */
/**************************************************************/

import java.util.InputMismatchException;
import java.util.Scanner;

public class Prog3 {
    public static void main(String[] args) {
        //for importing Database class
        Database myDb = new Database();
        myDb.initializeLinkedList(args);

        int choice = -1; //so the while loop can close
        int EXIT = 0; //for exiting
        
        while(choice != EXIT) {
           //this takes the input from the user, so that input can go into a switch statement
           System.out.println('\n' + "Welcome to this Dictionary Program" + '\n' +
           "Available commands:"  + '\n' + "1 Define a term"
           + '\n' + "2 Search for matching terms" 
           + '\n' + "3 Print all records"
           + '\n' + "4 Add new definition"
           + '\n' + "5 delete a definition"
           + '\n' + "9 Exit"
           + '\n' + "Your choice?");
   
           /* this takes in the users value, may want to change one of the names
           userChoice or userInput, it could be confusing */
           Scanner userChoice = new Scanner(System.in); //the user choice
           String userInput = userChoice.nextLine(); //gets user input
        
            /**************************************************************/
            /* Method: switch case */
            /* Purpose: user interface for program, calls appropriate methods*/
            /* Parameters: inputed term*/
            /* String target:  appropriate for case*/
            /* Returns: the selected case */
            /**************************************************************/
            switch (userInput) {
                case "1": //this is for defining the term, have user input term and then return the definition
                    System.out.println("Enter a term to define");
                    Scanner inputForDef = new Scanner(System.in); //input for the definition
                    myDb.defWeLookingFor = inputForDef.nextLine(); 
                    myDb.Define();
                        break;
                case "2": //this is for searching for matching terms, have user input term and return a match
                //it also returns the term at top, but I dont think thats a big deal
                    System.out.println("Enter a prefix to search for");
                    Scanner searchForTerm = new Scanner(System.in); //the term you are looking for
                    myDb.termEntered = searchForTerm.nextLine();
                    myDb.Search();
                        break;
                case "3": //this is for Printing all records, print all records
                    myDb.Print();
                        break;
                case "4": //this is for the user creating new enteries, including, term, number, and definition.
                    System.out.println("Input new Term ");
                    Scanner newTermInput = new Scanner(System.in); //term that has been inputted
                    myDb.newTermInput = newTermInput.next();

                    try {
                        System.out.println("Input new number ");
                        Scanner newNumInput = new Scanner(System.in); //number that has been inputted
                        myDb.newNumInput = newNumInput.nextInt();
                    } catch (InputMismatchException exe) {
                        System.out.println("You have to put in a number for the number ");
                        break;
                    }
                    
                    System.out.println("Input new Definition ");
                    Scanner newDefinitionInput = new Scanner(System.in); //def input
                    myDb.newDefinitionInput = newDefinitionInput.nextLine();
                    
                    myDb.newEntery();
                        break;
                case "5": //allows the user to delete a definition from the menu
                    System.out.println("Enter a term");
                    Scanner termToRemove = new Scanner(System.in); //the term to remove
                    myDb.termToRemove = termToRemove.nextLine();

                    try {
                        System.out.println("Input number of definition ");
                        Scanner numToDelete = new Scanner(System.in); //gets the number to delete
                        myDb.numToDelete = numToDelete.nextInt();
                    } catch (InputMismatchException exe) {
                        System.out.println("You have to put in a number for the number ");
                        break;
                    }

                    myDb.ChadSearch();
                        break;
                case "9": //this is for exiting, exit program
                    System.out.println("Goodbye");
                    EXIT = -1;
                    System.exit(0);
                        break;
                default: //Invalid input
                    System.out.println("Invalid, select a vaild input");
                        break;

            }
        }
    }
}
