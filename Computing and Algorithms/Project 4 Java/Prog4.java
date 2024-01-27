/**************************************************************/
/* Parker Buszka */
/* CS-102, Winter 2022 */
/* Programming Assignment 4 */
/* Prog1 class: interacts with the user */
/**************************************************************/

import java.util.InputMismatchException;
import java.util.Scanner;

public class Prog4 {
    public static void main(String[] args) {
        //for importing Database class
        Database myDb = new Database(); //use the same name to overwrite it
        myDb.initializeLinkedList(args); //the args would be user input

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
           + '\n' + "6 print to text file"
           + '\n' + "7 re-initialize the database"
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
                    myDb.defWeLookingFor = inputForDef.nextLine(); //puting def into database
                    myDb.Define();
                        break;
                case "2": //this is for searching for matching terms, have user input term and return a match
                //it also returns the term at top, but I dont think thats a big deal
                    System.out.println("Enter a prefix to search for");
                    Scanner searchForTerm = new Scanner(System.in); //the term you are looking for
                    myDb.termEntered = searchForTerm.nextLine(); //puts term into database
                    myDb.Search();
                        break;
                case "3": //this is for Printing all records, print all records
                    myDb.TermsList.printTree();
                        break;
                case "4": //this is for the user creating new enteries, including, term, number, and definition.
                    System.out.println("Input new Term ");
                    Scanner newTermInput = new Scanner(System.in); //term that has been inputted
                    myDb.newTermInput = newTermInput.next(); //puts term into database

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
                    myDb.newDefinitionInput = newDefinitionInput.nextLine(); //puts def into database
                    
                    myDb.newEntery();
                        break;
                case "5": //allows the user to delete a definition from the menu
                    System.out.println("Enter a term");
                    Scanner termToRemove = new Scanner(System.in); //the term to remove
                    myDb.termToRemove = termToRemove.nextLine(); //puts term into database

                    try {
                        System.out.println("Input number of definition ");
                        Scanner numToDelete = new Scanner(System.in); //gets the number to delete
                        myDb.numToDelete = numToDelete.nextInt(); //puts numToDelete in database
                    } catch (InputMismatchException exe) {
                        System.out.println("You have to put in a number for the number ");
                        break;
                    }
                    myDb.ChadSearch();
                        break;
                case "6":
                    // A new option should be added to the main menu which allows the user to write the entire contents of the database to disk
                    System.out.println("Enter a text file name ");
                    Scanner textFileName = new Scanner(System.in); //the file name
                    myDb.textFileName = textFileName.nextLine(); //puts the file name in database
                    myDb.writeTime();
                    break;
                case "7":
                    // A new option should be added to the main menu which allows the user to re-initialize the database
                    System.out.println("Enter a text file name for the new input ");
                    Scanner newDbFileName = new Scanner(System.in); //the file name
                    String stuff = newDbFileName.nextLine(); //for holding the file name
                    myDb.newDbFileName = stuff;

                    if (myDb.isThere() == true) {
                        myDb = new Database();
                        myDb.newDbFileName = stuff;
                        System.out.println("New database has been created ");
                    }
                    myDb.startOver();
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
